package br.diego.conversionapp.conversionservice.conversion.utilities;

import br.diego.conversionapp.conversionservice.conversion.commons.MoneyAmountWithCurrency;
import br.diego.conversionapp.conversionservice.conversion.dto.ConversionRequest;
import br.diego.conversionapp.conversionservice.conversion.dto.ConversionResponse;
import br.diego.conversionapp.conversionservice.quote.Quote;
import br.diego.conversionapp.conversionservice.quote.QuoteResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyConversionCalculator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyConversionCalculator.class);

    public static Mono<ConversionResponse> compute(ConversionRequest conversionRequest, Quote quote) {
        LOGGER.info("QUOTE RESPONSE RETRIEVED: " + quote);

        ConversionResponse created = getCurrencyConversionResponse(conversionRequest);
        created.setRetrievedAt(Long.valueOf(quote.getTimestamp()));
        created.setSuccess(quote.getSuccess());
        created.setQuote(Double.valueOf(quote.getQuote()));

        BigDecimal quoteForReceiver = BigDecimal.valueOf(created.getQuote());
        created.getAmountReceiver().setAmount(
                created.getAmountSender().getAmount().multiply(quoteForReceiver).setScale(2, RoundingMode.CEILING)
        );

        return Mono.just(created);
    }

    private static ConversionResponse getCurrencyConversionResponse(ConversionRequest conversionRequest) {
        ConversionResponse createdConversionResponse = new ConversionResponse();
        createdConversionResponse.setAmountSender(conversionRequest.getAmountSender());
        createdConversionResponse.setAmountReceiver(new MoneyAmountWithCurrency());
        createdConversionResponse.getAmountReceiver().setCurrency(conversionRequest.getTargetCurrency());
        createdConversionResponse.setCurrencyPair(
                createdConversionResponse.getAmountSender().getCurrency() + createdConversionResponse.getAmountReceiver().getCurrency()
        );
        return createdConversionResponse;
    }
}
