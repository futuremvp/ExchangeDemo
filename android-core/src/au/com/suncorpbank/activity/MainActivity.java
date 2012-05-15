package au.com.suncorpbank.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import au.com.suncorpbank.ExchangeActivity;
import au.com.suncorpbank.R;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button startBtn = (Button) findViewById(R.id.main_start_button);

        startBtn.setOnClickListener(new View.OnClickListener(){

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
