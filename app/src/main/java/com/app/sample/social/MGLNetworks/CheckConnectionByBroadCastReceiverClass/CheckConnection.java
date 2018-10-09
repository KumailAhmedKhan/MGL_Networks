package com.app.sample.social.MGLNetworks.CheckConnectionByBroadCastReceiverClass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

//import com.app.sample.chatting.SharedPreferenceClass.TemporaryStorageSharedPreferences;
//import com.app.sample.chatting.SignalR.Services.SignalRConnectionService;

public class CheckConnection extends BroadcastReceiver {

   // TemporaryStorageSharedPreferences temp=new TemporaryStorageSharedPreferences();
   // SignalRConnectionService service=new SignalRConnectionService();
    @Override
    public void onReceive(Context context, Intent intent) {
        try
        {
            if (isOnline(context))
            {

               /* activitySplash.startService();
               // Intent i = new Intent(context, SignalRConnectionService.class);
               // context.startService(i);
                //context.startService(this,SignalRConnectionService.class);

                Log.d("BroadcastReceiver","BroadcastReceiverConnected");
                //Toast.makeText(context,"Connection Established",Toast.LENGTH_LONG).show();
                String connectionkey=temp.getPreferences(context,"ConnectionKey");
                String ServiceSignal=temp.getPreferences(context,"ServiceSignal");
                if(!connectionkey.equals("lycoscommunication") &&(ServiceSignal.equals("Stopped")))
                {
                    //Intent i = new Intent(context.getApplicationContext(), SignalRConnectionService.class);
                     //context.getApplicationContext().startService(i);
                    Intent gpsIntent = new Intent(context,SignalRConnectionService.class);


                    // Add putExtras() or look at parceables to see if there is anything we need to do here

                    //Toast.makeText(context, "Our broadcast receiver was hit!", Toast.LENGTH_LONG).show();

                    // Start the activity (by utilizing the passed context)
                    gpsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().startService(gpsIntent);
                    // activitySplash.startService();
                    Log.d("BroadcastReceiver","BroadcastReceiverConnected");
                }
                if(!connectionkey.equals("lycoscommunication") &&(!ServiceSignal.equals("Waiting")))
                {
                    Toast.makeText(context,"Please Wait till Connection Established",Toast.LENGTH_LONG).show();
                }
                if(!connectionkey.equals("lycoscommunication") &&(!ServiceSignal.equals("Started")))
                {
                    Toast.makeText(context,"Please Wait till Connection Established",Toast.LENGTH_LONG).show();
                }*/
            }
            else {
                try {
                    /*
                    temp.savePreferences(context,"ConnectionKey","nokey");
                    temp.savePreferences(context,"ServiceSignal","Stopped");
                    //Intent i = new Intent(context.getApplicationContext(), SignalRConnectionService.class);
                    //context.getApplicationContext().stopService(i);
                    Intent gpsIntent = new Intent(context,SignalRConnectionService.class);


                    // Add putExtras() or look at parceables to see if there is anything we need to do here

                    //Toast.makeText(context, "Our broadcast receiver was hit!", Toast.LENGTH_LONG).show();

                    // Start the activity (by utilizing the passed context)
                    gpsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.getApplicationContext().stopService(gpsIntent);
                    //context.stop
                    //activitySplash.stopService();
                    Log.d("BroadcastReceiver","BroadcastReceiverNotConnected");*/
                   // Toast.makeText(context,"Internet Not Connected",Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
              // activitySplash.stopService();


            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
    public boolean isOnline(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
            //should check null because in airplane mode it will be null
        if(info==null || !info.isConnected())
            return false; //not connected
        if(info.getType() == ConnectivityManager.TYPE_WIFI)
            return true;
        if(info.getType() == ConnectivityManager.TYPE_MOBILE){
            int networkType = info.getSubtype();
            switch (networkType) {
                //for 2G//////////////////
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                    return false;
                //for 3G//////////////////
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                case TelephonyManager.NETWORK_TYPE_TD_SCDMA:  //api<25 : replace by 17
                    return true;
                //for 4G//////////////////
                case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                case TelephonyManager.NETWORK_TYPE_IWLAN:  //api<25 : replace by 18
                case 19:  //LTE_CA
                    return true;
                default:
                    return false;
            }
        }
        return false;


    }
    public int connnectioncheck(Context context )
    {
        if(isOnline(context))
        {
                 return 1;
        }
        else {
                 return 0;
        }
    }


}
