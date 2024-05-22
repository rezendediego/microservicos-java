package br.diego.conversionapp.conversionservice.conversion;

import br.diego.conversionapp.conversionservice.conversion.converters.ConversionRequestToQuoteRequestConverter;
import br.diego.conversionapp.conversionservice.conversion.dto.ConversionRequest;
import br.diego.conversionapp.conversionservice.conversion.dto.ConversionResponse;
import br.diego.conversionapp.conversionservice.conversion.utilities.CurrencyConversionCalculator;
import br.diego.conversionapp.conversionservice.quote.QuoteRequest;
import br.diego.conversionapp.conversionservice.quote.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

@Service
public class ConversionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionService.class);

    private final QuoteService quoteService;

    @Autowired
    public ConversionService(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    public Mono<ConversionResponse> computeCurrencyConversion(ConversionRequest conversionRequest) {
        QuoteRequest request = ConversionRequestToQuoteRequestConverter.convert(conversionRequest);
        request.setAccessKey("0");
        request.setFormat(1);
        LOGGER.info("Executando Conversion Service: "+ conversionRequest.toString());
        LOGGER.info("ConversionRequest convertido para QuoteRequest: "+ request.toString());

        Mono<ConversionResponse> conversionResponseMono = quoteService.computeQuote(request)
                .doOnNext(quote -> LOGGER.info("QUOTE RESPONSE CURRENCY CONVERSION: " + quote.toString()))
                .flatMap(quote -> CurrencyConversionCalculator.compute(conversionRequest, quote));
        LOGGER.info("conversion-service - QUOTE RECEIVED INSIDE CONVERSION-SERVICE TRANSFORMED CONVERSION RESPONSE: " + conversionResponseMono.toString());

        return conversionResponseMono;
    }
}
