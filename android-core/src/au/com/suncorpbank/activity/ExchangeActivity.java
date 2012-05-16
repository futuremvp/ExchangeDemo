package au.com.suncorpbank.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import au.com.suncorpbank.R;
import au.com.suncorpbank.domain.ExchangeRate;
import au.com.suncorpbank.domain.ExchangeRepo;
import au.com.suncorpbank.parser.XmlParser;
import au.com.suncorpbank.widget.ExchangeRateItem;

import static au.com.suncorpbank.util.Settings.getProperty;

public class ExchangeActivity extends Activity {

    private String url;
    private ListView rateExchangeList;
    private ExchangeRepo exchangeRepo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exchange_screen);

        initData();
        init();
    }

    private void init() {
        rateExchangeList = (ListView) findViewById(R.id.exchange_screen_exchange_rates);
        if (exchangeRepo == null || exchangeRepo.getExchanges() == null) return;

        rateExchangeList.setAdapter(new ExchangeListViewAdaptor());
    }

    private void initData() {
        url = getProperty("exchange.server.url");
        System.out.println("url = " + url);
        XmlParser parser = new XmlParser();
        exchangeRepo = parser.getExchangeRepo(url);
    }

    class ExchangeListViewAdaptor extends BaseAdapter {

        ExchangeListViewAdaptor() {
        }

        public int getCount() {
            return exchangeRepo.getExchanges().size();
        }

        public Object getItem(int position) {
            return exchangeRepo.getExchange(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("position = " + position + " | " + exchangeRepo.getExchanges().size());

            ExchangeRate rate = exchangeRepo.getExchange(position);
            String label = rate.getCountry() + " (" + rate.getCurrencyCode() + ") ";

            String value = String.valueOf(rate.getBuyChqAtRate());

            if (convertView == null) {
                convertView = new ExchangeRateItem(getApplicationContext(), label, value);
            } else {
                ((ExchangeRateItem) convertView).setData(label, value);
            }
            return convertView;
        }
    }
}
