package com.kks.random;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import java.util.Random;

public class MyService extends Service {


  private static final String TAG = "kks";
    final Handler handler = new Handler();
    Runnable r;
    Intent i = new Intent("random1");
    Random rn = new Random();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Runnable r = new Runnable(){
            public void run(){

                int random = (rn.nextInt(100) + 1);
                i.setAction(MainActivity.mBroadcastAction);
                i.putExtra("RandomNumber",String.valueOf(random));

                sendBroadcast(i);
                Log.i(TAG,"broadcast send");
                handler.postDelayed(this, 7000);
            }
        };
          Thread kks =new Thread(r);
           kks.start();
        return Service.START_STICKY;
    }



    @Override
    public void onDestroy() {
        handler.removeCallbacksAndMessages(r);
        super.onDestroy();
    }
}