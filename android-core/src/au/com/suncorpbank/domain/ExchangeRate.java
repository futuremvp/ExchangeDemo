package au.com.suncorpbank.domain;

public class ExchangeRate {
    
    private String country;
    private String currencyName;
    private String currencyCode;
    private double buyChqAtRate;
    private double sellNotesAtRate;

    public ExchangeRate(String country, String currencyName, String currencyCode, double buyChqAtRate, double sellNotesAtRate) {
        this.country = country;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.buyChqAtRate = buyChqAtRate;
        this.sellNotesAtRate = sellNotesAtRate;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getSellNotesAtRate() {
        return sellNotesAtRate;
    }
}
