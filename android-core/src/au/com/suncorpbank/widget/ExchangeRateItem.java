package au.com.suncorpbank.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import au.com.suncorpbank.R;

public class ExchangeRateItem extends RelativeLayout{

    private String label;
    private String value;
    private View content;
    private TextView labelView;
    private TextView valueView;

    public ExchangeRateItem(Context context, String label, String value) {
        super(context);
        this.label = label;
        this.value = value;
        init();
    }

    private void init() {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        content = inflater.inflate(R.layout.exchange_rate_item, this, false);
        this.addView(content);

        labelView = (TextView) content.findViewById(R.id.exchange_rate_item_label);
        valueView = (TextView) content.findViewById(R.id.exchange_rate_item_value);

        labelView.setText(label);
        valueView.setText(value);
    }

    public void setData(String label, String value) {
        labelView.setText(label);
        valueView.setText(value);
    }
}
