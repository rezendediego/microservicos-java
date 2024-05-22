package br.diego.conversionapp.quoteservice.quote;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "quote")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonProperty("success")
    private Boolean success;
    @JsonProperty("timestamp")
    private String timestamp;
    @JsonProperty("currencyPair")
    private String currencyPair;
    @JsonProperty("quote")
    private String quote;

    public Quote() {}

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public Boolean isValid() {
        return timestamp != null && !timestamp.isEmpty() && quote != null && !quote.isEmpty();
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Quote{" +
                "success=" + success +
                ", timestamp='" + timestamp + '\'' +
                ", currencyPair='" + currencyPair + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }

}