package com.app.sample.social.MGLNetworks.Loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;

import android.util.Log;

import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.app.sample.social.MGLNetworks.Model.CompanyDetailModel;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;

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

public class CompanyDetailLoaders extends AsyncTaskLoader<List<CompanyDetailModel>> {

    private Context context;
    private TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;
    private String CompanyID;

    public CompanyDetailLoaders(Context context,String Id) {
        super(context);
        this.context= context.getApplicationContext();
        this.CompanyID = Id;


    }

    public CompanyDetailLoaders(Context context) {
        super(context);
    }

    @Override
    public List<CompanyDetailModel> loadInBackground() {
        try
        {
            String token = temporaryStorageSharedPreferences.getPreferences(getContext(),"Token");
            String ID = temporaryStorageSharedPreferences.getPreferences(getContext(),"UserId");
            URL url=new URL("https://mglnetwork.com/api/MemberProfile"+"/"+CompanyID+"/"+token);
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
                     JSONObject jsonObj = null;
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
                     try {
                         jsonObj = jsonData.getJSONObject("member");
                    } catch (JSONException e) {
                     e.printStackTrace();
                     }
                   // JSONArray jsonArr = null;
                   // try {
                       // jsonArr = jsonData.getJSONArray("member");
                   // } catch (JSONException e) {
                     //   e.printStackTrace();
                   // }

                    List<CompanyDetailModel> dataList = new ArrayList<>();

                   // for (int i = 0; i < jsonArr.length(); i++)
                    {
                        //JSONObject jsonObj = null;
                       // try {
                            //jsonObj = jsonArr.getJSONObject(i);
                        //} catch (JSONException e) {
                         //   e.printStackTrace();
                        //}
                        CompanyDetailModel data = new CompanyDetailModel();

                        try {
                            data.setCIAddress(jsonObj.getString("address"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCICity(jsonObj.getString("city"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIAnnualTurnover(jsonObj.getString("annual_turnover"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIBranches(jsonObj.getString("branches"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCI_AEO_Certifications(jsonObj.getString("AEO_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCICurrentAddress(jsonObj.getString("address_question"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIEstablishmentYear(jsonObj.getString("establishment_year"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIFaxNumber(jsonObj.getString("fax_number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIIATACode(jsonObj.getString("iata_code"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIOfficeNumber(jsonObj.getString("contact_no"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIRegistrationNumber(jsonObj.getString("company_registration"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCIServices(jsonObj.getString("services"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCITotalEmployees(jsonObj.getString("total_employees"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setCICountry(jsonObj.getString("country_name"));
                         } catch (JSONException e) {
                             e.printStackTrace();
                        }try {
                            data.setCI_COC_Certifications(jsonObj.getString("COC_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_Custom_Certifications(jsonObj.getString("custom_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_DG_Certifications(jsonObj.getString("DG_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_FIATA_Certifications(jsonObj.getString("FIATA_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_IATA_Certifications(jsonObj.getString("IATA_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_ISO_Certifications(jsonObj.getString("ISO_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_MTO_Certifications(jsonObj.getString("MTO_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_NAFL_Certifications(jsonObj.getString("NAFL_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }try {
                            data.setCI_OtherNet_Certifications(jsonObj.getString("OtherNet_certifications"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //////////////////// Key Contact Detail 1 ////////////////////////
                        try {
                            data.setKCD1Designation(jsonObj.getString("keycontact1_designation"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                        data.setKCD1Email(jsonObj.getString("keycontact1_email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD1MobileNumber(jsonObj.getString("keycontact1_mobile_number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD1Name(jsonObj.getString("keycontact1_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD1OfficeNumber(jsonObj.getString("keycontact1_office_number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD1Skype(jsonObj.getString("keycontact1_skype"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //////////////////key Contact Detail 2/////////////////////
                        try {
                            data.setKCD2Designation(jsonObj.getString("keycontact2_designation"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD2Email(jsonObj.getString("keycontact2_email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD2MobileNumber(jsonObj.getString("keycontact2_mobile_number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD2Name(jsonObj.getString("keycontact2_name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD2OfficeNumber(jsonObj.getString("keycontact2_office_number"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setKCD2Skype(jsonObj.getString("keycontact2_skype"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ////////////////////////Reference Contact Detail 1////////////////////////////
                        try {
                            data.setRCD1CompanyName(jsonObj.getString("refcontact1_company"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1Contact(jsonObj.getString("refcontact1_contact"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1Designation(jsonObj.getString("refcontact1_designation"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1Email(jsonObj.getString("refcontact1_email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1FaxNumber(jsonObj.getString("refcontact1_fax"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1Telephone(jsonObj.getString("refcontact1_telephone"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD1TypeOfBusiness(jsonObj.getString("refcontact1_business"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        ////////////////////////Reference Contact Detail 2////////////////////////////
                        try {
                            data.setRCD2CompanyName(jsonObj.getString("refcontact2_company"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2Contact(jsonObj.getString("refcontact2_contact"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2Designation(jsonObj.getString("refcontact2_designation"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2Email(jsonObj.getString("refcontact2_email"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2FaxNumber(jsonObj.getString("refcontact2_fax"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2Telephone(jsonObj.getString("refcontact2_telephone"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            data.setRCD2TypeOfBusiness(jsonObj.getString("refcontact2_business"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                        dataList.add(data);


                    }
                    return dataList;
                    // task.onTaskCompleted(dataList);

                    // Intent intent = new Intent("MessageFromApi");

                    //LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    //progressDialog.dismiss();*/


            }


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    /*
        @Override
        public List<CompanyDetailModel> loadInBackground()
        {
            try
            {
                String token = temporaryStorageSharedPreferences.getPreferences(getContext(),"Token");
                //String ID = temporaryStorageSharedPreferences.getPreferences(getContext(),"UserId");
                URL url=new URL("https://mglnetwork.com/api/MemberProfile"+"/"+1003+"/"+token);
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
                   /* String statuscode = "";
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

                        dataList.add(data);


                    }
                    return dataList;
                    // task.onTaskCompleted(dataList);

                    // Intent intent = new Intent("MessageFromApi");

                    //LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    //progressDialog.dismiss();
    *//*

            }


        }
        catch (Exception e)
        {
            e.printStackTrace();

        }

        return null;
    }
    */
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
