package com.app.sample.social.MGLNetworks.ActivityClass;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.v4.content.Loader;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.CheckConnectionByBroadCastReceiverClass.CheckConnection;
import com.app.sample.social.MGLNetworks.Loaders.CompanyDetailLoaders;
import com.app.sample.social.MGLNetworks.Model.CompanyDetailModel;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

public class DetailCompanyActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<CompanyDetailModel>>, SwipeRefreshLayout.OnRefreshListener {

        static String certifications = null ;
        static String currentAddress = null ;
        Context context;
        public TemporaryStorageSharedPreferences temporaryStorageSharedPreferences = new TemporaryStorageSharedPreferences();
        public  CompanyDetailModel companyDetailModel;
        String ID;
        TextView CIurrentAddress ;
        TextView CICountry ;
        TextView CITotalEmployees ;
        TextView CIRegistrationNumber ;
        TextView CIEstablishmentYear ;
        TextView CIAnnualTurnover ;
        TextView CIAddress;
        TextView CIServices;
        TextView CIOfficeNumber ;
        TextView CIIATACode ;
        TextView CIFaxnumber;
        TextView CIBranches ;
        TextView CICertifications ;
        TextView LogoutButton;
        TextView KCD1Name ;
        TextView KCD1Designation ;
        TextView KCD1OfficeNumber ;
        TextView KCD1Mobilenumber ;
        TextView KCD1Email ;
        TextView KCD1Skype ;
        CheckConnection checkConnection=new CheckConnection();
        TextView KCD2Name ;
        TextView KCD2Designation ;
        TextView KCD2OfficeNumber ;
        TextView KCD2MobileNumber ;
        TextView KCD2Email ;
        TextView KCD2Skype ;
        TextView RCD1CompanyName ;
        TextView RCD1Contact ;
        TextView RCD1TypeOfBusiness ;
        TextView RCD1Designation ;
        TextView RCD1Telephone ;
        TextView RCD1FaxNumber ;
        TextView RCD1Email ;
        TextView RCD2CompanyName ;
        TextView RCD2Contact ;
        TextView RCD2TypeOfBusiness;
        TextView RCD2Designation ;
        TextView RCD2Telephone ;
        TextView RCD2FaxNumber ;
        TextView RCD2Email ;
        ScrollView scrollView ;
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onRefresh() {
        final int checkconnectionflag=checkConnection.connnectioncheck(context);
        if(checkconnectionflag==1)
        {
            getLoaderManager().initLoader(3,null,this).forceLoad();
        }
        else{
            this.mSwipeRefreshLayout.setRefreshing(false);
            Toast.makeText(context,"Please Check Internet Connection",Toast.LENGTH_LONG).show();
        }
    }

    public class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());

            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, size, size);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(size, size, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap,
                    BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        this.context = getApplicationContext();
        Intent intent = getIntent();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setProgressViewOffset (true,700,800);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        /* intent.putExtra("CompanyID",ID);
                intent.putExtra("CompanyName",Name);
                intent.putExtra("CompanyLogoURL",imageurl);*/
        ID = intent.getStringExtra("CompanyID");
        String Name = intent.getStringExtra("CompanyName");
        final String ImageUrl = intent.getStringExtra("CompanyLogoURL");
        Log.d("CompanyName",Name);
        Log.d("CompanyURL",ImageUrl);
        Log.d("CompanyID",ID);
        final ImageView companylogo=(ImageView)findViewById(R.id.CompanyLogo);
        TextView companyname = (TextView) findViewById(R.id.CompanyName);
        companyname.setText(Name);
        ImageView BackButton=(ImageView)findViewById(R.id.BackButton);
        BackButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                        finish();
                        onBackPressed();

            }
        });
        Picasso.get().load(ImageUrl.replace(" ", "%20")).resize(198,198).transform(new CircleTransform()).into(companylogo);
         CIurrentAddress = (TextView) findViewById(R.id.CurrentAddress);
         CICountry = (TextView) findViewById(R.id.Country);
         CITotalEmployees = (TextView) findViewById(R.id.TotalEmployees);
         CIRegistrationNumber = (TextView) findViewById(R.id.RegistrationNumber);
         CIEstablishmentYear = (TextView) findViewById(R.id.EstablishmentYear);
         CIAnnualTurnover = (TextView) findViewById(R.id.AnnualTurnover);
         CIAddress = (TextView) findViewById(R.id.Address);
        // scrollView = (ScrollView) findViewById(R.id.parentscrollview);
         CIServices = (TextView) findViewById(R.id.Services);

         CIOfficeNumber = (TextView) findViewById(R.id.OfficeNumber);
         CIIATACode = (TextView) findViewById(R.id.IATACode);
         CIFaxnumber = (TextView) findViewById(R.id.FaxNumber);
         CIBranches = (TextView) findViewById(R.id.Branches);

         CICertifications = (TextView) findViewById(R.id.Certifications);

         LogoutButton =(TextView) findViewById(R.id.LogoutButton);

         KCD1Name = (TextView) findViewById(R.id.KCD1Name);
         KCD1Designation = (TextView) findViewById(R.id.KCD1Designation);
         KCD1OfficeNumber = (TextView) findViewById(R.id.KCD1OfficeNumbr);
         KCD1Mobilenumber = (TextView) findViewById(R.id.KCD1MobileNumber);
         KCD1Email = (TextView) findViewById(R.id.KCD1Email);
         KCD1Skype = (TextView) findViewById(R.id.KCD1Skype);


         KCD2Name = (TextView) findViewById(R.id.KCD2Name);
         KCD2Designation = (TextView) findViewById(R.id.KCD2Designation);
         KCD2OfficeNumber = (TextView) findViewById(R.id.KCD2OfficeNumber);
         KCD2MobileNumber = (TextView) findViewById(R.id.KCD2MobileNumber);
         KCD2Email = (TextView) findViewById(R.id.KCD2Email1);
         KCD2Skype = (TextView) findViewById(R.id.KCD2Skype);


         RCD1CompanyName = (TextView) findViewById(R.id.RCD1CompanyName);
         RCD1Contact = (TextView) findViewById(R.id.textView87);
         RCD1TypeOfBusiness = (TextView) findViewById(R.id.RCD1TypeOfBusiness);
         RCD1Designation = (TextView) findViewById(R.id.RCD1Designation);
         RCD1Telephone = (TextView) findViewById(R.id.RCD1Telephone);
         RCD1FaxNumber = (TextView) findViewById(R.id.RCD1FaxNumber);
         RCD1Email = (TextView) findViewById(R.id.RCD1Email);
         RCD2CompanyName = (TextView) findViewById(R.id.RCD2CompanyName);
         RCD2Contact = (TextView) findViewById(R.id.RCD2Contact);
         RCD2TypeOfBusiness = (TextView) findViewById(R.id.RCD2TypeOfBusiness);
         RCD2Designation = (TextView) findViewById(R.id.RCD2Designation);
         RCD2Telephone = (TextView) findViewById(R.id.RCD2Telephone);
         RCD2FaxNumber = (TextView) findViewById(R.id.RCD2FaxNumber);
         RCD2Email = (TextView) findViewById(R.id.RCD2Email);
        final int checkconnectionflag=checkConnection.connnectioncheck(context);
        if(checkconnectionflag==1)
        {
            getLoaderManager().initLoader(3,null,this).forceLoad();
        }
        else{
            Toast.makeText(context,"Please Check Internet Connection",Toast.LENGTH_LONG).show();
        }
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String UserID = "ID";
                String UserToken = "Token";

                try {
                    temporaryStorageSharedPreferences.savePreferences(context,"UserId",UserID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(context,"Logout","Logout");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(context,"Token",UserToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(context,"Counter","2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent in = new Intent(DetailCompanyActivity.this,HomePage.class);
                startActivity(in);
                finish();
            }
        });

    }

    public void setData(CompanyDetailModel companyDetailModel)
    {


        if(companyDetailModel.getCI_AEO_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "AEO";
            }
            else {
                certifications = "AEO";
            }

        }
        if(companyDetailModel.getCI_COC_Certifications().equals("1") )
        {
             if(certifications == null)
            {
                certifications = "Chamber of Commerce";
            } else {
                certifications = certifications  + "," + "Chamber of Commerce";
            }
        }
        if(companyDetailModel.getCI_Custom_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "Customs Brokerage";
            }
            else{
                certifications = certifications + "," + "Customs Brokerage";
            }

        }
        if(companyDetailModel.getCI_DG_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "DG (Dangerous Goods)";
            }else{
                certifications = certifications + "," + "DG (Dangerous Goods)";
            }

        }
        if(companyDetailModel.getCI_FIATA_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "FIATA";
            }else{

                certifications = certifications + "," + "FIATA";
            }

        }
        if(companyDetailModel.getCI_IATA_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "IATA";
            }else{

                certifications = certifications  + "," + "IATA";
            }

        }
        if(companyDetailModel.getCI_ISO_Certifications() == "1")
        {
            if(certifications == null)
            {
                certifications = "ISO 9001 & others";
            }else{
                certifications = certifications  + "," + "ISO 9001 & others";

            }

        }
        if(companyDetailModel.getCI_NAFL_Certifications().equals("1"))
        {
            if(certifications == null)
            {
                certifications = "NAFL";
            }else{
                certifications = certifications + "," + "NAFL";

            }

        }
        if(companyDetailModel.getCI_OtherNet_Certifications().equals("1"))
        {
            if(certifications == null )
            {
                certifications = "Member of other forwarding Network";
            }else{
                certifications = certifications + "," + "Member of other forwarding Network";

            }

        }
        if(companyDetailModel.getCI_MTO_Certifications().equals("1"))
        {
            if(certifications == null )
            {
                certifications = "MTO";
            }else{
                certifications = certifications + "," + "MTO";

            }

        }
        if(certifications == null){
            certifications = "N/A";
            CICertifications.setText(certifications);
        }
        if(certifications != null)
        {
            CICertifications.setText(certifications);

        }


        CICertifications.setMovementMethod(new ScrollingMovementMethod());
        CICertifications.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (CICertifications.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        CIAddress.setText(companyDetailModel.getCIAddress()+","+companyDetailModel.getCICity()+","+companyDetailModel.getCICountry());
        CIAddress.setMovementMethod(new ScrollingMovementMethod());
        CIAddress.setScrollbarFadingEnabled(false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            CIAddress.setScrollBarFadeDuration(60000);
        }
        CIAddress.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (CIAddress.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK){
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);

                            return true;

                    }
                }

                return false;
            }
        });
        CIAnnualTurnover.setText(companyDetailModel.getCIAnnualTurnover());
        CIBranches.setText(companyDetailModel.getCIBranches());
        CIBranches.setMovementMethod(new ScrollingMovementMethod());
        CIBranches.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (CIAddress.hasFocus())
                {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK)
                    {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        CICountry.setText(companyDetailModel.getCICountry());
        CIEstablishmentYear.setText(companyDetailModel.getCIEstablishmentYear());
        CIFaxnumber.setText(companyDetailModel.getCIFaxNumber());
        CIIATACode.setText(companyDetailModel.getCIIATACode());
        CIOfficeNumber.setText(companyDetailModel.getCIOfficeNumber());
        CIRegistrationNumber.setText(companyDetailModel.getCIRegistrationNumber());
        CIServices.setText(companyDetailModel.getCIServices());
        CIServices.setMovementMethod(new ScrollingMovementMethod());
        CIServices.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if (CIAddress.hasFocus())
                {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK)
                    {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        CITotalEmployees.setText(companyDetailModel.getCITotalEmployees());

        if(companyDetailModel.getCICurrentAddress().equals("address_head_office") )
        {
            currentAddress = " Head Office ";
        }
        if(companyDetailModel.getCICurrentAddress().equals("address_branch"))
        {
            currentAddress = " Branch Office ";
        }
        CIurrentAddress.setText(currentAddress);
        ///////////////////RCD2 Setting value////////////////////////
        RCD2FaxNumber.setText(companyDetailModel.getRCD2FaxNumber());
        RCD2Email.setText(companyDetailModel.getRCD2Email());
        RCD2Telephone.setText(companyDetailModel.getRCD2Telephone());
        RCD2CompanyName.setText(companyDetailModel.getRCD2CompanyName());
        RCD2Contact.setText(companyDetailModel.getRCD2Contact());
        RCD2TypeOfBusiness.setText(companyDetailModel.getRCD2TypeOfBusiness());
        RCD2Designation.setText(companyDetailModel.getRCD2Designation());
        ///////////////////RCD1 Setting value////////////////////////
        RCD1FaxNumber.setText(companyDetailModel.getRCD1FaxNumber());
        RCD1Email.setText(companyDetailModel.getRCD1Email());
        RCD1Telephone.setText(companyDetailModel.getRCD1Telephone());
        RCD1CompanyName.setText(companyDetailModel.getRCD1CompanyName());
        RCD1Contact.setText(companyDetailModel.getRCD1Contact());
        RCD1TypeOfBusiness.setText(companyDetailModel.getRCD1TypeOfBusiness());
        RCD1Designation.setText(companyDetailModel.getRCD1Designation());
        //////////////////////////KCD2 Setting value /////////////////
        KCD2Name.setText(companyDetailModel.getKCD2Name());
        KCD2Designation.setText(companyDetailModel.getKCD2Designation());
        KCD2OfficeNumber.setText(companyDetailModel.getKCD2OfficeNumber());
        KCD2MobileNumber.setText(companyDetailModel.getKCD2MobileNumber());
        KCD2Email.setText(companyDetailModel.getKCD2Email());
        KCD2Skype.setText(companyDetailModel.getKCD2Skype());
        //////////////////////////KCD1 Setting value /////////////////
        KCD1Name.setText(companyDetailModel.getKCD1Name());
        KCD1Designation.setText(companyDetailModel.getKCD1Designation());
        KCD1OfficeNumber.setText(companyDetailModel.getKCD1OfficeNumber());
        KCD1Mobilenumber.setText(companyDetailModel.getKCD1MobileNumber());
        KCD1Email.setText(companyDetailModel.getKCD1Email());
        KCD1Skype.setText(companyDetailModel.getKCD1Skype());
    }
    @Override
    public android.content.Loader<List<CompanyDetailModel>> onCreateLoader(int id, Bundle args) {
        this.mSwipeRefreshLayout.setRefreshing(true);
        return new CompanyDetailLoaders(context,ID);
    }

    @Override
    public void onLoadFinished(android.content.Loader<List<CompanyDetailModel>> loader, List<CompanyDetailModel> data) {
        this.mSwipeRefreshLayout.setRefreshing(false);
        companyDetailModel = data.get(0);
        setData(companyDetailModel);
    }

    @Override
    public void onLoaderReset(android.content.Loader<List<CompanyDetailModel>> loader) {

    }


    @Override
    protected void onResume() {
        super.onResume();
        certifications =null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        certifications =null;
    }
}
