package br.diego.conversionapp.conversionservice.quote;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class QuoteResponse {
    @JsonProperty("success")
    private String success;
    @JsonProperty("terms")
    private String terms;
    @JsonProperty("privacy")
    private String privacy;
    @JsonProperty("timestamp")
    private Long timestamp;
    @JsonProperty("source")
    private String source;
    @JsonProperty("quotes")
    private Map<String, String> quotes;

    public QuoteResponse() {}
    public QuoteResponse(String success, String terms, String privacy, Long timestamp, String source, Map<String, String> quotes) {
        this.success = success;
        this.terms = terms;
        this.privacy = privacy;
        this.timestamp = timestamp;
        this.source = source;
        this.quotes = quotes;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Map<String, String> getQuotes() {
        return quotes;
    }

    public void setQuotes(Map<String, String> quotes) {
        this.quotes = quotes;
    }

    @Override
    public String toString() {
        return "QuoteResponse{" +
                "success='" + success + '\'' +
                ", terms='" + terms + '\'' +
                ", privacy='" + privacy + '\'' +
                ", timestamp=" + timestamp +
                ", source='" + source + '\'' +
                ", quotes=" + quotes +
                '}';
    }


    public boolean isValid() {
        return success != null && timestamp != null && quotes != null;
    }

}