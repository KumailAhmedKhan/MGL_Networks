package com.app.sample.social.MGLNetworks.ActivityClass;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.Fragment.HomeFragment;
import com.app.sample.social.MGLNetworks.Fragment.MembershipFragment;
import com.app.sample.social.MGLNetworks.Fragment.PageFragmentAdapter;
import com.app.sample.social.MGLNetworks.TemporaryStorage.TemporaryStorageSharedPreferences;
import com.app.sample.social.R;




public class ActivityMain extends AppCompatActivity {

    private Toolbar toolbar;
    private ActionBar actionbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    private View parent_view;

    private PageFragmentAdapter adapter;
    public TemporaryStorageSharedPreferences temporaryStorageSharedPreferences;
    private HomeFragment f_feed;
   // private AboutUsFragment f_friend;
    private MembershipFragment f_message;
    //private MeetingFragment f_notif;
    //private ContactUsFragment f_profile;
    private static int[] imageResId = {
            R.drawable.icon,
            //R.drawable.about,
            R.drawable.whiteuser,
           // R.drawable.meeting
           // R.drawable.contact
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parent_view = findViewById(android.R.id.content);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionbar = getSupportActionBar();
        //actionbar.setDisplayHomeAsUpEnabled(false);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        setupTabClick();


        // for system bar in lollipop
        //Tools.systemBarLolipop(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new PageFragmentAdapter(getSupportFragmentManager());
        if (f_feed == null) { f_feed = new HomeFragment(); }
       // if (f_friend == null) { //f_friend = new AboutUsFragment(); }
        if (f_message == null) { f_message = new MembershipFragment(); }
        //if (f_notif == null) { f_notif = new MeetingFragment(); }
        //if (f_profile == null) { f_profile = new ContactUsFragment(); }
        adapter.addFragment(f_feed,"Membership");
        //adapter.addFragment(f_friend, "About Us");
        adapter.addFragment(f_message, "Home");
        //adapter.addFragment(f_notif, "MGLN Meeting");
        //adapter.addFragment(f_profile, "Contact MGLN");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //viewPager.setCurrentItem(1);
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(imageResId[0]);
        tabLayout.getTabAt(1).setIcon(imageResId[1]);
//        tabLayout.getTabAt(2).setIcon(imageResId[2]);
        //tabLayout.getTabAt(3).setIcon(imageResId[3]);
        //tabLayout.getTabAt(4).setIcon(imageResId[4]);
    }

    private void setupTabClick() {
        //tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                viewPager.setCurrentItem(position);
                actionbar.setTitle(adapter.getTitle(position));

            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
          /*  case R.id.action_about: {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("About");
                builder.setMessage(getString(R.string.about_text));
                builder.setNeutralButton("OK", null);
                builder.show();
                return true;
            }*/
            case R.id.action_logout: {
                String UserID = "ID";
                String UserToken = "Token";
                try {
                    temporaryStorageSharedPreferences.savePreferences(getApplicationContext(),"Logout","Logout");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(getApplicationContext(),"UserId",UserID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(getApplicationContext(),"Token",UserToken);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    temporaryStorageSharedPreferences.savePreferences(getApplicationContext(),"Counter","2");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(getApplicationContext(), HomePage.class);
                startActivity(i);
                finish();
                return true;
            }
           /* case R.id.action_settings: {
                Snackbar.make(parent_view, "Setting Clicked", Snackbar.LENGTH_SHORT).show();
                return true;
            }
            case R.id.action_regist: {
                Intent i = new Intent(getApplicationContext(), ActivityRegistration.class);
                startActivity(i);
                return true;
            }*/
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private long exitTime = 0;

    public void doExitApp() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, R.string.press_again_exit_app, Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        doExitApp();
    }


    // handle click profile page
   // public void actionClick(View view){
     //   f_profile.actionClick(view);
    //}
}
