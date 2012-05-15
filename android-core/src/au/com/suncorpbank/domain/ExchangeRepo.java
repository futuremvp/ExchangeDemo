package au.com.suncorpbank.domain;

import java.util.LinkedList;

public class ExchangeRepo {

    LinkedList<ExchangeRate> exchanges = new LinkedList<ExchangeRate>();
    
    public void addNewExchange(ExchangeRate rate){
        exchanges.add(rate);
    }

    public LinkedList<ExchangeRate> getExchanges(){
        return exchanges;
    }

    public ExchangeRate getExchange(int index) {
        return exchanges.get(index);
    }

}
