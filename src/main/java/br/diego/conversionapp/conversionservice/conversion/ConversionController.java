package br.diego.conversionapp.conversionservice.conversion;


import br.diego.conversionapp.conversionservice.conversion.dto.ConversionRequest;
import br.diego.conversionapp.conversionservice.conversion.dto.ConversionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/conversions")
public class ConversionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionController.class);
    private final ConversionService conversionService;

    public ConversionController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    @PostMapping(consumes = "application/json", produces = "application/json")
    public Mono<ConversionResponse> computeCurrencyConversion(
            @RequestBody ConversionRequest conversionRequest) {
        LOGGER.info("Executando computeCurrencyConversion no ConversionController");
        LOGGER.info(conversionRequest.toString());

        Mono<ConversionResponse> conversionResponseMono = conversionService.computeCurrencyConversion(conversionRequest);

        return conversionResponseMono;

    }


    @GetMapping("/teste")
    public String teste() {
        LOGGER.info("Conversion Service : Executando teste no ConversionController");
        return "Conversion Service Testado!";
    }
}
