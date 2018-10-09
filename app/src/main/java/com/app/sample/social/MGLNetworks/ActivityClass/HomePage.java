package com.app.sample.social.MGLNetworks.ActivityClass;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.CheckConnectionByBroadCastReceiverClass.CheckConnection;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;


public class HomePage extends AppCompatActivity {
    TextView Members;
    TextView MembersCount;
    TextView Country;
    TextView CountryCount;
    TextView Office;
    TextView OfficeCount;
    TextView Team;
    TextView TeamCount;
    TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;
    CheckConnection checkConnection=new CheckConnection();
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(MessageReceiver, new IntentFilter("MessageFromApi"));
        LinearLayout member=(LinearLayout)findViewById(R.id.memberslinearlayout);
        RelativeLayout country=(RelativeLayout)findViewById(R.id.countrylayout);
        RelativeLayout office=(RelativeLayout)findViewById(R.id.office);
        RelativeLayout team=(RelativeLayout)findViewById(R.id.team);
        Members = (TextView)findViewById(R.id.members) ;
        MembersCount = (TextView)findViewById(R.id.count) ;
        Country = (TextView)findViewById(R.id.countries) ;
        CountryCount = (TextView)findViewById(R.id.countrycount) ;
        Office = (TextView)findViewById(R.id.countries1) ;
        OfficeCount = (TextView)findViewById(R.id.countrycount1) ;
        Team = (TextView)findViewById(R.id.offices) ;
        TeamCount = (TextView)findViewById(R.id.officesno) ;
        context = getApplicationContext();
        final int checkconnectionflag=checkConnection.connnectioncheck(context);
        if(checkconnectionflag==1)
        {
            try {
                Starttask();
            } catch (UnknownHostException e) {

                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }

        }
        else
        {
           Toast.makeText(context,"Please Check Internet Connection",Toast.LENGTH_LONG).show();
        }




        final String USERID ;
        final String USERTOKEN;
        final String USERLOGOUT;
        final String COUNTER;
        USERID = temporaryStorageSharedPreferences.getPreferences(getApplicationContext(),"UserId");
        USERLOGOUT = temporaryStorageSharedPreferences.getPreferences(getApplicationContext(),"Logout");
        USERTOKEN = temporaryStorageSharedPreferences.getPreferences(getApplicationContext(),"Token");
        COUNTER = temporaryStorageSharedPreferences.getPreferences(getApplicationContext(),"Counter");
        country.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((COUNTER.equals("1"))&&(USERLOGOUT.equals("NotLogout")))
                {
                    Intent intent=new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
                else{

                    Intent intent=new Intent(getApplicationContext(),ActivityLogin.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }

            }
        });
        member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((COUNTER.equals("1"))&&(USERLOGOUT.equals("NotLogout")))
                {
                    Intent intent=new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
                else{

                    Intent intent=new Intent(getApplicationContext(),ActivityLogin.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
            }
        });
        office.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if ((COUNTER.equals("1"))&&(USERLOGOUT.equals("NotLogout")))
                {
                    Intent intent=new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
                else{

                    Intent intent=new Intent(getApplicationContext(),ActivityLogin.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
            }
        });
        team.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if ((COUNTER.equals("1"))&&(USERLOGOUT.equals("NotLogout")))
                {
                    Intent intent=new Intent(getApplicationContext(),ActivityMain.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
                else{

                    Intent intent=new Intent(getApplicationContext(),ActivityLogin.class);
                    startActivity(intent);
                    finish();
                    view.setOnClickListener(null);
                }
            }
        });

       // TextView textoverview=(TextView)findViewById(R.id.textpara);
       // textoverview.setText("Majestic Global Logistics Network (MGLN) is a non-exclusive network of independent freight forwarders and international logistics companies. MGLN agents are carefully recruited to ensure all members benefits from each other business and services");
       // textoverview.setTextColor(Color.parseColor("#ffffff"));
    }
    public void Starttask() throws UnknownHostException,InterruptedException,TimeoutException
    {
        HomePageAsyncTask homePageAsyncTask = new HomePageAsyncTask(getApplicationContext(),HomePage.this);
        homePageAsyncTask.execute() ;
    }
    public void InterruptedException()
    {
        Intent intent = new Intent(context,HomePage.class);
        startActivity(intent);
    }
    public void UnknownHostException(){
       Intent intent = new Intent(context,HomePage.class);
       startActivity(intent);
    }
    public void setData(String a,String b,String c,String d,String e,String f,String g,String h){

        Members.setText(a);
        MembersCount.setText(b);
        Country.setText(c);
        CountryCount.setText(d);
        Office.setText(e);
        OfficeCount.setText(f);
        Team.setText(g);
        TeamCount.setText(h);
    }
    BroadcastReceiver MessageReceiver=new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            String Section1_name=intent.getStringExtra("Section1_name");
            String Section1_count=intent.getStringExtra("Section1_count");
            String Section2_name=intent.getStringExtra("Section2_name");
            String Section2_count=intent.getStringExtra("Section2_count");
            String Section3_name=intent.getStringExtra("Section3_name");
            String Section3_count=intent.getStringExtra("Section3_count");
            String Section4_name=intent.getStringExtra("Section4_name");
            String Section4_count=intent.getStringExtra("Section4_count");
            Members.setText(Section1_name);
            MembersCount.setText(Section1_count);
            Country.setText(Section2_name);
            CountryCount.setText(Section2_count);
            Office.setText(Section3_name);
            OfficeCount.setText(Section3_count);
            Team.setText(Section4_name);
            TeamCount.setText(Section4_count);

        }
    };


}
class HomePageAsyncTask extends AsyncTask<String,String,String>
{
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;
    String singleparsed="";
    String data="";
    public Context context;
    public Activity activity;
    private ProgressDialog progressDialog;

    public HomePageAsyncTask(){}
    public HomePageAsyncTask(Context ctx, Activity act){
        this.context = ctx.getApplicationContext();
        this.activity =act;

    }
    @Override
    protected String doInBackground(String... strings) {
        String Data = "";
        try {

            URL myUrl = new URL("https://mglnetwork.com/api/MembersCount");
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection) myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);
            //Connect to our url
            connection.connect();
            int responseCode=connection.getResponseCode();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();

            if(responseCode == 200) {
                //Check if the line we are reading is not null
                String inputLine = "";
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);
                }
                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();

                Data = stringBuilder.toString();
                JSONObject jsonData = new JSONObject(Data);
                Log.d("Hello Got data ", Data);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            Log.d("Exception",e.toString());

           // return  null;
          //  Intent intent = new Intent(context,AppCrashActivity.class);
           // context.startActivity(intent);


        }
        return Data;
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
       // pd = new ProgressDialog(getActivity(),R.style.MyTheme);
       // pd.setCancelable(false);
       // pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
       // pd.show();
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
    protected void onPostExecute(String s)
    {
        super.onPostExecute(s);

        if(s == ""){
            Intent intent = new Intent(context,AppCrashActivity.class);
            context.startActivity(intent);

        }
        else {
            JSONObject jsonData = null;
            try {
                jsonData = new JSONObject(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String statuscode = null;

            try {
                statuscode = jsonData.getString("status_code");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String Section1_name = null;
            try {
                Section1_name = (jsonData.getString("Section1_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section1_count = null;
            try {
                Section1_count = (jsonData.getString("Section1_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section2_name = null;
            try {
                Section2_name = (jsonData.getString("Section2_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section2_count = null;
            try {
                Section2_count = (jsonData.getString("Section2_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section3_name = null;
            try {
                Section3_name = (jsonData.getString("Section3_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section3_count = null;
            try {
                Section3_count = (jsonData.getString("Section3_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section4_name = null;
            try {
                Section4_name = (jsonData.getString("Section4_name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            String Section4_count = null;
            try {
                Section4_count = (jsonData.getString("Section4_count"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
            Intent intent = new Intent("MessageFromApi");
            intent.putExtra("Section1_name", Section1_name);
            intent.putExtra("Section1_count", Section1_count);
            intent.putExtra("Section2_name", Section2_name);
            intent.putExtra("Section2_count", Section2_count);
            intent.putExtra("Section3_name", Section3_name);
            intent.putExtra("Section3_count", Section3_count);
            intent.putExtra("Section4_name", Section4_name);
            intent.putExtra("Section4_count", Section4_count);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

        }

    }
}
