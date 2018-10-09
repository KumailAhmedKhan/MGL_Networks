package com.app.sample.social.MGLNetworks.ActivityClass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.CheckConnectionByBroadCastReceiverClass.CheckConnection;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

public class ActivityLogin extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    private TextInputLayout inputLayoutEmail, inputLayoutPassword;
    private Button btnLogin;
    private View parent_view;
    private TextView backtohome;
    private LoginAsyncTask loginAsyncTask;
    CheckConnection checkConnection=new CheckConnection();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        parent_view = findViewById(android.R.id.content);

        inputLayoutEmail = (TextInputLayout) findViewById(R.id.input_layout_email);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        backtohome = (TextView)findViewById(R.id.backtohomepage);
        context = getApplicationContext();
        backtohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent);
                finish();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                boolean result = Authenticate(email,password);
                if(result){
                    final int checkconnectionflag=checkConnection.connnectioncheck(context);
                    if(checkconnectionflag==1)
                    {
                        try {
                            StartTask(email,password);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (TimeoutException e) {
                            e.printStackTrace();
                        }
                    }
                    else{
                       Toast.makeText(context,"Please Check Internet Connection",Toast.LENGTH_LONG).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext()," Invalid Credential",Toast.LENGTH_LONG).show();
                }

                view.setOnClickListener(null);
            }
        });

    }
    public void StartTask(String email,String password)throws UnknownHostException,InterruptedException,TimeoutException {

        loginAsyncTask = (LoginAsyncTask) new LoginAsyncTask(email,password,getApplicationContext(),ActivityLogin.this).execute("","","");

    }
    public boolean Authenticate(String email,String password)
    {
        if(email != "" && password != ""){
            return true;
        }
        return false;

    }











    class LoginAsyncTask extends AsyncTask<String, String, String>{
        public Activity activity;
        private Context context;
        private String mEmail;
        private String mPassword;
        private ProgressDialog progressDialog;
        private TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;

        public LoginAsyncTask()
        {

        }

        public LoginAsyncTask(String Email,String Password,Context ctx, Activity act)
        {
            this.context = ctx.getApplicationContext();
            this.activity = act;
            this.mEmail = Email;
            this.mPassword = Password;
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progressDialog=new ProgressDialog(activity,ProgressDialog.THEME_TRADITIONAL);
            progressDialog.setTitle("Loading");
            progressDialog.setMessage("Wait while loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setProgress(0);
            progressDialog.setMax(20);
            progressDialog.setCancelable(false); // disable dismiss by tapping outside of the dialog
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings)
        {
            try
            {

                URL url=new URL("https://mglnetwork.com/api/Login");
                HttpURLConnection connection=(HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("email", mEmail);
                postDataParams.put("password", mPassword);
                Log.e("params",postDataParams.toString());

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
            return "";
        }

        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);
            if(s == "")
            {
                Intent intent = new Intent(context,AppCrashActivity.class);
                context.startActivity(intent);
            }
            else{
            String statuscode = "";
            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                 statuscode = jsonData.getString("status_code");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if(statuscode.equals("100")) {
                String token = null;
                try {
                    token = (jsonData.getString("token"));
                    Log.d("fullToken", token);

                    String[] token1 = token.split("\\$");
                    String Token = token1[0];
                    try {
                        temporaryStorageSharedPreferences.savePreferences(context, "Token", Token);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //Log.d("Token",token1[0]);

                    String UserID = token1[1];
                    try {
                        temporaryStorageSharedPreferences.savePreferences(context, "UserId", UserID);
                        temporaryStorageSharedPreferences.savePreferences(context,"Counter","1");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    //Log.d("TokenID",token1[1]);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                try {
                    temporaryStorageSharedPreferences.savePreferences(context, "Logout", "NotLogout");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent=new Intent(context,ActivityMain.class);
                startActivity(intent);
                finish();
            }
            if(statuscode.equals("500"))
            {
                progressDialog.dismiss();
                Toast.makeText(context,"Invalid Credentials",Toast.LENGTH_LONG).show();
            }





        }}
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

