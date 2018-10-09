package com.app.sample.social.MGLNetworks.TemporaryStorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;


import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TemporaryStorageSharedPreferences {
    protected final static int DEFAULT = 0;
        int temp = 0;


           public static void savePreferences(Context mContext, String key, String value)throws Exception
    {
        try{
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value).apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void savePreferencesObjectList(Context mContext, String key, List<CompanyDataModel> obj) throws Exception
    {
        try{
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            SharedPreferences.Editor editor = sharedPreferences.edit();

          //  Gson gson =new Gson();
           // JsonElement element =
               //     gson.toJsonTree(obj , new TypeToken<List<CompanyDataModel>>() {}.getType());

           // JsonArray jsonArray = element.getAsJsonArray();
            //JSONObject jsonObject = new JSONObject(jsonArray);
            String value = TextUtils.join( "," , obj);

            editor.putString(key, value).apply();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }



    public static List<String> getPreferencesforJsonArray(Context context, String keyValue)
        {

           // try{}catch (Exception e){e.printStackTrace();}
            String onlineUserString=getPreferences(context,keyValue);
            onlineUserString=onlineUserString.substring(1,onlineUserString.toString().length()-1);
            onlineUserString=onlineUserString.replaceAll(" ","");

            List<String> onlineUsers = new ArrayList<String>(Arrays.asList( onlineUserString.split(",")));
            //onlineUsers=onlineUsers.re

            return onlineUsers;
        }


        /**
         * @param context
         * @param keyValue
         * @return
         */
        public static String getPreferences(Context context, String keyValue) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            try{
                String temp=sharedPreferences.getString(keyValue, "");
            }catch (Exception e){
                e.printStackTrace();
            }

            return sharedPreferences.getString(keyValue, "");
        }
        public static List<CompanyDataModel> getPreferencesObjectList(Context context, String keyValue) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
           ArrayList<CompanyDataModel> lstArrayList = null;
        try{
            Gson gson = new Gson();
            String temp=sharedPreferences.getString(keyValue, "");
             lstArrayList = gson.fromJson(temp,
                    new TypeToken<List<CompanyDataModel>>(){}.getType());
        }catch (Exception e){
            e.printStackTrace();
        }

        return lstArrayList;
    }

        /**
         * @param mContext
         */
        public static void removeAllSharedPreferences(Context mContext) throws Exception{
            try{
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear().apply();
            }catch (Exception e)
            {
                e.printStackTrace();
            }

        }

}
