package au.com.suncorpbank.domain;

import java.util.HashMap;

public class ExchangeRepo {

    HashMap<String, Double> exchanges;
    
    public void addNewExchange(String currency, double rate){
        exchanges.put(currency, new Double(rate));
    }

    public HashMap getExchange(){
        return exchanges;
    }

}
