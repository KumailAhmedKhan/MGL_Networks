package com.app.sample.social.MGLNetworks.AsyncTaskClasses;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.ActivityClass.ActivityLogin;
import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.app.sample.social.MGLNetworks.OnTaskCompleted;

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

public class CompanyAsyncTask extends AsyncTask<String,String,String>
{
    private String token;
    private Context context;
    private Activity activity;
    private OnTaskCompleted task;
    private ProgressDialog progressDialog;
    public CompanyAsyncTask(String token, Context context,Activity active ,OnTaskCompleted list)
    {
        this.token = token;
        this.context = context.getApplicationContext();
        this.activity = active;
        this.task = list;
    }

    public CompanyAsyncTask() {
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        super.onPreExecute();
        progressDialog=new ProgressDialog(activity);
        progressDialog.show();
    }

    @Override
    protected void onPostExecute(String s) {

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
                data.setCompanyCountry(jsonObj.getString("country"));
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

            dataList.add(data);


        }
        task.onTaskCompleted(dataList);

        Intent intent = new Intent("MessageFromApi");

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        progressDialog.dismiss();
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings)
    {
        try
        {

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
                return sb.toString();

            }
            else {
                return new String("false : "+responseCode);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
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
