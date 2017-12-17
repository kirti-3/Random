package com.kks.random;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  {
    public static final String mBroadcastAction = "com.kks.random";
    private IntentFilter mIntentFilter;

    Button startbutton ;
    Button stopbutton ;
    Intent it;
    private static final String TAG = "kks";
    TextView randomtxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntentFilter = new IntentFilter("random1");
        mIntentFilter.addAction(mBroadcastAction);
        startbutton= (Button)findViewById(R.id.startbutton);
        stopbutton= (Button)findViewById(R.id.stopbutton);
        it =new Intent(this,MyService.class);
        randomtxt = (TextView)findViewById(R.id.randomtxt);

    }

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(kksReceive, mIntentFilter);
    }


    BroadcastReceiver kksReceive = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(TAG,"random number");
            if (intent.getAction().equals(mBroadcastAction)) {
                String random = intent.getStringExtra("RandomNumber");
                randomtxt.setText(random);
            }
        }
    };

    protected void onPause() {
        unregisterReceiver(kksReceive);
        super.onPause();
    }




    public void start(View view)
    {
        startService(it);
        Log.i(TAG,"started");
       startbutton.setEnabled(false);
        stopbutton.setEnabled(true);
    }


    public void stop(View view)
    {
          stopService(it);
        stopbutton.setEnabled(false);
        startbutton.setEnabled(true);
    }
}
