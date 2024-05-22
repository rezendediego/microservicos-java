package br.diego.conversionapp.conversionservice.quote;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QuoteRequest {
    @JsonProperty("access_key")
    String accessKey;
    @JsonProperty("source")
    public String source;
    @JsonProperty("currencies")
    public String currencies;
    @JsonProperty("format")
    int format;

    public QuoteRequest() {
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

    public String getCurrencyPair(){return this.source + this.currencies;}

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
