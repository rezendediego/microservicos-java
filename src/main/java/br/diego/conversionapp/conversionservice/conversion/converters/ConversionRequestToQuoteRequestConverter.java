package br.diego.conversionapp.conversionservice.conversion.converters;

import br.diego.conversionapp.conversionservice.conversion.dto.ConversionRequest;
import br.diego.conversionapp.conversionservice.quote.QuoteRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConversionRequestToQuoteRequestConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConversionRequestToQuoteRequestConverter.class);
    public static QuoteRequest convert(ConversionRequest conversionRequest) {
        QuoteRequest converted = new QuoteRequest();
        converted.setSource(conversionRequest.getAmountSender().getCurrency());
        converted.setCurrencies(conversionRequest.getTargetCurrency());
        LOGGER.debug("ConversionRequestToQuoteRequestConverter " + converted);
        return converted;
    }

}
