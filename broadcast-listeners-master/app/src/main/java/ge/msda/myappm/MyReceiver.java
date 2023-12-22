package ge.msda.myappm;

import static ge.msda.myappm.MainActivity.TAG;

import android.app.ApplicationErrorReport;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    public static ConnectivityListener connectivityListener;
    public static BatteryListener batteryListener;

    public MyReceiver() {

    }

    @Override
    public void onReceive(Context context, Intent intent) {



        if(intent.getAction().equals("com.lazo.TEXT_CLICKED")) {
            Log.d(TAG, intent.getExtras().getString("key"));
        }

        Log.d(TAG, intent.getAction());

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
            if(connectivityListener != null) {
                connectivityListener.onConnectionChanged(true);
            }
        } else {
            if(connectivityListener != null) {
                connectivityListener.onConnectionChanged(false);
            }
        }


        BatteryManager bm = (BatteryManager) context.getSystemService(Context.BATTERY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            batteryListener.batteryTimeRemaining(bm.computeChargeTimeRemaining());
        }


        if (intent.getAction().equals(Intent.ACTION_TIME_TICK)) {
            Log.d(TAG, intent.getAction());
        }

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            Bundle bundle = intent.getExtras();
            SmsMessage[] msgs;
            String msg_from;
            if (bundle != null){
                try{
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    msgs = new SmsMessage[pdus.length];
                    for(int i=0; i<msgs.length; i++){
                        msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from = msgs[i].getOriginatingAddress();
                        String msgBody = msgs[i].getMessageBody();

                        Log.d(TAG, msg_from + msgBody);

                    }
                }catch(Exception e){
                            Log.d("Exception caught",e.getMessage());
                }
            }
        }

    }

}