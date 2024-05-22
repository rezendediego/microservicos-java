package br.diego.conversionapp.quoteservice.quote;


import br.diego.conversionapp.quoteservice.quote.dto.QuoteRequest;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/quotes")
public class QuoteController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuoteController.class);
    private final QuoteService quoteService;


    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Mono<Quote> computeQuote(@RequestBody QuoteRequest quoteRequest, @Context HttpHeaders headers) {
        LOGGER.info("Executando computeQuote no QuoteController");
        LOGGER.info(quoteRequest.toString());
        LOGGER.info("Leitura de Headers no computeQuote no QuoteController" + headers);
        Mono<Quote> quoteMono = quoteService.computeQuote(quoteRequest);
        LOGGER.info("Leitura de quote mono: " + quoteMono.subscribe(quote -> LOGGER.info("QUOTE IS: " + quote.toString())));
        return quoteMono;
    }
    @GetMapping("/teste")
    public String teste() {
        LOGGER.info("Executando teste no QuoteController");
        return "Testado!";
    }
}