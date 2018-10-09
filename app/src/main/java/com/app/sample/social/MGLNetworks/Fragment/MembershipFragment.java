package com.app.sample.social.MGLNetworks.Fragment;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.Loader;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.ActivityClass.DetailCompanyActivity;
import com.app.sample.social.MGLNetworks.Adapter.CompanyDataAdapter;
import com.app.sample.social.MGLNetworks.AsyncTaskClasses.CompanyAsyncTask;
import com.app.sample.social.MGLNetworks.CheckConnectionByBroadCastReceiverClass.CheckConnection;
import com.app.sample.social.MGLNetworks.Interface.ItemClickListener;
import com.app.sample.social.MGLNetworks.Loaders.CompanyDataLoaders;
import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.app.sample.social.MGLNetworks.OnTaskCompleted;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static android.graphics.drawable.ClipDrawable.HORIZONTAL;
import static android.graphics.drawable.ClipDrawable.VERTICAL;


public class MembershipFragment extends Fragment implements android.support.v4.app.LoaderManager.LoaderCallbacks<List<CompanyDataModel>>, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private  List<CompanyDataModel> companyDataModelList = new ArrayList<CompanyDataModel>() {
    };
    private  List<CompanyDataModel> companyDataModelList1 = new ArrayList<CompanyDataModel>() {
    };
    private ArrayList<CompanyDataModel> arrayList ;
   // static CompanyDataModel companyDataModel = new CompanyDataModel();
    private CompanyDataAdapter mAdapter;
    private View view;
    private SearchView search;
    private TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;
    private ProgressDialog progressDialog;
    private Context context;
    //SwipeRefreshLayout swipeRefreshLayout;
    static int count;
    SwipeRefreshLayout mSwipeRefreshLayout;
    List<CompanyDataModel> list = companyDataModelList;
    List<CompanyDataModel> result_list = new ArrayList<>(list.size());
    CheckConnection checkConnection=new CheckConnection();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ONCREATE","ONCREATE");
        count=0;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        count++;
       final View view = getView() != null ? getView() :
                inflater.inflate(R.layout.membership_page, container, false);
        context = inflater.getContext();
        // activate fragment menu
        setHasOptionsMenu(true);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        mSwipeRefreshLayout.setProgressViewOffset (true,500,600);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        Log.d("ONCREATEVIEW","ONCREATEVIEW");
        mAdapter = new CompanyDataAdapter(getActivity(), companyDataModelList);
        DividerItemDecoration itemDecor = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        final int checkconnectionflag=checkConnection.connnectioncheck(context);
        if(checkconnectionflag==1)
        {
            getLoaderManager().initLoader(3,null,this).forceLoad();
        }
        else{
            Toast.makeText(context,"Please Check Internet Connection",Toast.LENGTH_LONG).show();
        }


        recyclerView.setAdapter(mAdapter);
        return view;
    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_message, menu);
        search = (SearchView) menu.findItem(R.id.action_search).getActionView();
        search.setIconified(false);
        search.setQueryHint("Search CompanyName & Country ");
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("HELLO","HELLO");
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                try {
                    String counter = temporaryStorageSharedPreferences.getPreferences(context,"Counter");
                    if(counter.equals("1"))
                    {
                        companyDataModelList1 = null;
                    }
                    if(companyDataModelList1 == null)
                    {
                        Gson gson = new Gson();
                        String CDM1 = temporaryStorageSharedPreferences.getPreferences(context,"MembersData");
                        Type type1 = new TypeToken<List<CompanyDataModel>>(){}.getType();
                        List<CompanyDataModel> CDM = gson.fromJson(CDM1, type1);
                        companyDataModelList1 = CDM;
                    }

                    companyDataModelList = companyDataModelList1;
                    list = companyDataModelList;
                     result_list = new ArrayList<>(list.size());

                        if(s == null){
                            mAdapter.notifyDataSetChanged();
                        }
                    for (int i = 0; i < list.size(); i++) {
                        String str_title = list.get(i).getCompanyName();
                        String str_snippet = list.get(i).getCompanyCountry();
                        if (str_title.toLowerCase().contains(s.toLowerCase()) )
                        {
                            result_list.add(list.get(i));
                            //Log.d("word Search","result_list");
                        }
                        else if (str_snippet.toLowerCase().contains(s.toLowerCase()))
                        {
                            result_list.add(list.get(i));
                        }

                    }

                        if(s.length() == 0)
                        {

                            mAdapter.setfchat(companyDataModelList1);
                            mAdapter.notifyDataSetChanged();
                        }
                        else if(result_list.size() == 0){

                            mAdapter.setfchat(result_list);
                            mAdapter.notifyDataSetChanged();
                        }
                        else{
                            try {
                                mAdapter.setfchat(result_list);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            mAdapter.notifyDataSetChanged();
                        }


                    Log.d("Searching...........",s);

                } catch (Exception e) {
                    Log.d("Exception",e.toString());
                }

                return true;
            }

        });
        search.onActionViewCollapsed();
        super.onCreateOptionsMenu(menu, inflater);
    }








    @Override
    public android.support.v4.content.Loader<List<CompanyDataModel>> onCreateLoader(int id, Bundle args) {

        //progressDialog = new ProgressDialog(context,ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        //progressDialog.show();
        this.mSwipeRefreshLayout.setRefreshing(false);
        return  new CompanyDataLoaders(context);
    }

    @Override
    public void onLoadFinished(Loader<List<CompanyDataModel>> loader, List<CompanyDataModel> data)
    {

        try {
            if(data == null)
            {
                Gson gson = new Gson();
                String CDM1 = temporaryStorageSharedPreferences.getPreferences(context,"MembersData");
                Type type1 = new TypeToken<List<CompanyDataModel>>(){}.getType();
                List<CompanyDataModel> CDM = gson.fromJson(CDM1, type1);
                mAdapter.setfchat(CDM);
                mAdapter.notifyDataSetChanged();
                this.mSwipeRefreshLayout.setRefreshing(false);
                //getting data from SharedPreferences when user open the application 2nd time
                //checking the counter from shared preferences
            }
            else{
                this.mSwipeRefreshLayout.setRefreshing(false);
                mAdapter.setfchat(data);
                mAdapter.notifyDataSetChanged();
                companyDataModelList1 = data;
                //arrayList.add(data);
                arrayList =new ArrayList<CompanyDataModel>(data);
                Gson gson = new Gson();
                //Wrapper wrapper = new Wrapper();
//populate list
               // wrapper.setDataList(companyDataModelList1);

                Type type = new TypeToken<List<CompanyDataModel>>() {}.getType();
                String jsonobjlist = gson.toJson(arrayList,type);
                temporaryStorageSharedPreferences.savePreferences(context,"MembersData",jsonobjlist);
                //String CDM = temporaryStorageSharedPreferences.getPreferences(context,"MembersData");
                //Type type1 = new TypeToken<List<CompanyDataModel>>(){}.getType();
                //List<CompanyDataModel> CDM = gson.fromJson(obj, type1);
                //List<CompanyDataModel> CDM = gson.fromJson(obj,List<CompanyDataModel>.class);
                // Log.d("DATA",CDM.toString() );
                // progressDialog.dismiss();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(Loader<List<CompanyDataModel>> loader) {

    }






    @Override
    public void onRefresh()
    {
        //FragmentTransaction ft = getFragmentManager().beginTransaction();
        //ft.detach(this).attach(this).commit();
        getLoaderManager().initLoader(3,null,this).forceLoad();
        mAdapter.notifyDataSetChanged();

    }

}

