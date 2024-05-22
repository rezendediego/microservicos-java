package br.diego.conversionapp.conversionservice.quote;


import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class QuoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteService.class);
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;

    @Value("${quote-service.api.url}")
    private String baseUrl;

    @Autowired
    public QuoteService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    private void init() {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }
    @Transactional
    public Mono<Quote> computeQuote(QuoteRequest quoteRequest) {
        LOGGER.info("Executando QuoteService CLIENT do ConversionService (QUOTE REQUEST): " + quoteRequest);

        Mono<Quote> quoteResponseMono = webClient.post()
                .uri("/quotes")
                .header("caller-header", "CONVERSION-SERVICE")
                .header("method-header", "ComputeQuote-Conversion-Service")
                .bodyValue(quoteRequest)
                .retrieve()
                // Handle unsuccessful responses (non-2xx) with a custom exception
                .onStatus(HttpStatusCode::is4xxClientError, response -> Mono.error(new ClientErrorException(response.statusCode().value())))
                // Handle server errors (5xx) with a different exception (optional)
                .onStatus(HttpStatusCode::is5xxServerError, response -> Mono.error(new ServerErrorException(Response.Status.valueOf("Server Error"))))
                .bodyToMono(Quote.class)
                .doOnSubscribe(subscription -> System.out.println("Requesting quote..."))
                .doOnError(error -> System.err.println("Error fetching quote: " + error.getMessage()))
                .doOnSuccess(response -> System.out.println("Received quote response: " + response));

        LOGGER.info("Executando QuoteService CLIENT do ConversionService (QUOTE RESPONSE): " + quoteResponseMono.subscribe(quote -> LOGGER.info(quote.toString())));

        /*

        Mono<Quote> quoteMono = quoteResponseMono
                .filter(QuoteResponse::isValid)
                .flatMap(quoteResponse -> ConvertQuoteResponseToQuote.convert(quoteRequest, quoteResponse));

        LOGGER.info("QUOTE RECEIVED INSIDE CONVERSION-SERVICE FROM QUOTE-SERVICE" + quoteMono.toString());

        * */

        LOGGER.info("quote-service - QUOTE RECEIVED INSIDE CONVERSION-SERVICE FROM QUOTE-SERVICE" + quoteResponseMono.subscribe(quote -> LOGGER.info("QUOTE IS: " + quote.toString())));
        return quoteResponseMono;

    }
}



