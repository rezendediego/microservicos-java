package br.diego.conversionapp.quoteservice.quote.dto;
import br.diego.conversionapp.quoteservice.quote.Quote;
import reactor.core.publisher.Mono;

public class ConvertQuoteResponseToQuote {
    public static Mono<Quote> convert(QuoteRequest quoteRequest, QuoteResponse quoteResponse) {
        Quote quote = new Quote();
        quote.setSuccess(Boolean.valueOf(quoteResponse.getSuccess()));
        quote.setTimestamp(String.valueOf(quoteResponse.getTimestamp()));
        quote.setCurrencyPair(quoteResponse.getSource() + quoteRequest.getCurrencies());
        quote.setQuote(quoteResponse.getQuotes().get(quote.getCurrencyPair()));
        return Mono.just(quote);
    }
}
