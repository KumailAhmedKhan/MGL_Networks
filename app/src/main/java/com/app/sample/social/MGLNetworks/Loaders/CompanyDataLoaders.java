package com.app.sample.social.MGLNetworks.Loaders;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.app.sample.social.MGLNetworks.Model.CompanyDetailModel;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompanyDataLoaders extends android.support.v4.content.AsyncTaskLoader<List<CompanyDataModel>> {

    private Context context;
    private TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;

    public CompanyDataLoaders(Context context) {
        super(context);
        this.context=context.getApplicationContext();
    }

    @Override
    public List<CompanyDataModel> loadInBackground() {
        try
        {
            String token = temporaryStorageSharedPreferences.getPreferences(getContext(),"Token");

            URL url=new URL("https://mglnetwork.com/api/AllMembers"+"/"+token);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            JSONObject postDataParams = new JSONObject();
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            String postString=getPostDataString(postDataParams);
            writer.write(postString);
            writer.flush();
            writer.close();
            os.close();

            int responseCode=connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK)
            {

                BufferedReader in=new BufferedReader(
                        new InputStreamReader(
                                connection.getInputStream()));
                StringBuffer sb = new StringBuffer("");
                String line="";

                while((line = in.readLine()) != null)
                {
                    sb.append(line);
                    break;
                }

                in.close();
                String s =sb.toString();
                String statuscode = "";
                // JSONObject Allmembers = null;
                JSONObject jsonData = null;
                try {
                    jsonData = new JSONObject(s);
                    Log.d("JSONDATA", String.valueOf(jsonData));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    statuscode = jsonData.getString("status_code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // try {
                // Allmembers = jsonData.getJSONObject("all_members");
                //} //catch (JSONException e) {
                // e.printStackTrace();
                // }
                JSONArray jsonArr = null;
                try {
                    jsonArr = jsonData.getJSONArray("all_members");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                List<CompanyDataModel> dataList = new ArrayList<>();

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = jsonArr.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    CompanyDataModel data = new CompanyDataModel();
                        data.setCompanyLogo(jsonObj.getString("company_logo"));
                    try {
                        data.setCompanyId(jsonObj.getString("id"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyName(jsonObj.getString("name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyAddress(jsonObj.getString("address"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyCity(jsonObj.getString("city"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyCountry(jsonObj.getString("country_name"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyEmail(jsonObj.getString("email"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyWebsite(jsonObj.getString("website"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyPhoneNo(jsonObj.getString("contact_no"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyFaxNumber(jsonObj.getString("fax_number"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        data.setCompanyOfficeNumber(jsonObj.getString("office_number"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    String url1 = "http://mglnetwork.com/assets/members/"+data.getCompanyName()+"-"+data.getCompanyId()+"/"+data.getCompanyLogo();
                    data.setCompanyImageURL(url1);

                    dataList.add(data);


                }
                return dataList;
               // task.onTaskCompleted(dataList);

               // Intent intent = new Intent("MessageFromApi");

                //LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                //progressDialog.dismiss();
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return  null;

    }
    public String getPostDataString(JSONObject params) throws Exception
    {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while(itr.hasNext()){

            String key= itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }


}
