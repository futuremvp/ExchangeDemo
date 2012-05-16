package au.com.suncorpbank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import au.com.suncorpbank.R;

import static au.com.suncorpbank.util.Settings.getProperty;

public class MainActivity extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button startBtn = (Button) findViewById(R.id.main_start_button);
        TextView urlValue = (TextView) findViewById(R.id.main_point_to_value);

        urlValue.setText(getProperty("exchange.server.url"));
        startBtn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startNewIntent();
            }
        });
    }

    private void startNewIntent() {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), ExchangeActivity.class.getName());
        startActivity(intent);
    }
}
