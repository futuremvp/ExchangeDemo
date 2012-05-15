package au.com.suncorpbank.domain;

public class ExchangeRate {
    
    private String country;
    private String currencyName;
    private String currencyCode;
    private double buyChqAtRate;
    private double sellAtRate;

    public ExchangeRate(String country, String currencyName, String currencyCode, double buyChqAtRate, double sellAtRate) {
        this.country = country;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.buyChqAtRate = buyChqAtRate;
        this.sellAtRate = sellAtRate;
    }

    public String getCountry() {
        return country;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public double getBuyChqAtRate() {
        return buyChqAtRate;
    }
}
