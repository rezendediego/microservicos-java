package br.diego.conversionapp.quoteservice.quote;

import br.diego.conversionapp.quoteservice.quote.dto.ConvertQuoteResponseToQuote;
import br.diego.conversionapp.quoteservice.quote.dto.QuoteRequest;
import br.diego.conversionapp.quoteservice.quote.dto.QuoteResponse;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.Response;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class QuoteService {
    private final WebClient.Builder webClientBuilder;
    private WebClient webClient;
    @Value("${currency-layer.api.access-key}")
    private String accessKey;

    @Value("${currency-layer.api.format}")
    private int format;
    @Value("${currency-layer.api.url}")
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
        quoteRequest.setAccessKey(accessKey);
        quoteRequest.setFormat(format);

        Mono<QuoteResponse> quoteResponseMono = webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/live")
                        .queryParam("access_key", quoteRequest.getAccessKey())
                        .queryParam("source", quoteRequest.getSource())
                        .queryParam("currencies", quoteRequest.getCurrencies())
                        .queryParam("format", quoteRequest.getFormat())
                        .build())
                .retrieve()
                // Handle successful responses (2xx) and let them pass through
                .onStatus(response -> response.is2xxSuccessful(), response -> Mono.empty())
                // Handle unsuccessful responses (non-2xx) with a custom exception
                .onStatus(response -> response.is4xxClientError(), response -> Mono.error(new ClientErrorException(response.statusCode().value())))
                // Handle server errors (5xx) with a different exception (optional)
                .onStatus(response -> response.is5xxServerError(), response -> Mono.error(new ServerErrorException(Response.Status.valueOf("Server Error"))))
                .bodyToMono(QuoteResponse.class)
                .doOnSubscribe(subscription -> System.out.println("Requesting quote..."))
                .doOnError(error -> System.err.println("Error fetching quote: " + error.getMessage()))
                .doOnSuccess(response -> System.out.println("Received quote response: " + response));

        return quoteResponseMono
                .filter(QuoteResponse::isValid)
                .flatMap(quoteResponse -> ConvertQuoteResponseToQuote.convert(quoteRequest, quoteResponse));
    }


}
