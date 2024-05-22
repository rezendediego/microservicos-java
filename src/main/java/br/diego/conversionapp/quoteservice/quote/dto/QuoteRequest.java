package br.diego.conversionapp.quoteservice.quote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteRequest {
    @JsonProperty("access_key")
    private String accessKey;
    @JsonProperty("source")
    private String source;
    @JsonProperty("currencies")
    private String currencies;
    @JsonProperty("format")
    private int format;

    public QuoteRequest() {}

    public QuoteRequest(String accessKey, String source, String currencies, int format) {
        this.accessKey = accessKey;
        this.source = source;
        this.currencies = currencies;
        this.format = format;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getCurrencyPair() {
        return source + currencies;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return "QuoteRequest{" +
                "accessKey='" + accessKey + '\'' +
                ", source='" + source + '\'' +
                ", currencies='" + currencies + '\'' +
                ", format=" + format +
                '}';
    }
}