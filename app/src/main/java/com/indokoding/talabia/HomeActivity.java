package com.indokoding.talabia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.indokoding.adapeter.HomePageAdapter;
import com.indokoding.apiholder.utilsApi;
import com.indokoding.fragment.FavoritFragment;
import com.indokoding.fragment.FeedFragment;
import com.indokoding.fragment.HomeFragment;
import com.indokoding.fragment.HotListFragment;
import com.indokoding.model.FeedUser;
import com.indokoding.session.SessionManager;


import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.viewPagerHome)
    ViewPager vpHome;

    @BindView(R.id.tabsHome)
    TabLayout tabHome;

    private TextView tvName;
    private TextView tvEmail;
    private ImageView imageViewProfil;
    private SessionManager sessionManager;
    private Dialog dialogAbout;
    private com.indokoding.apiholder.baseApiService baseApiService;
    private HomePageAdapter homePageAdapter;
    private static final String TAG = "HomeActivity";
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(HomeActivity.this);
        sessionManager = new SessionManager(this);
        baseApiService = utilsApi.getApiServices();
        dialogAbout = new Dialog(this);
        setSupportActionBar(toolbar);
        getProfil();
        homePageAdapter = new HomePageAdapter(getSupportFragmentManager());
        setupViewPager(vpHome);

        tabHome.setupWithViewPager(vpHome);

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        HomePageAdapter homePageAdapter = new HomePageAdapter(getSupportFragmentManager());
        homePageAdapter.addFragment(new HomeFragment(), "Home");
        homePageAdapter.addFragment(new FeedFragment(), "Feed");
        homePageAdapter.addFragment(new FavoritFragment(), "Favorite");
        homePageAdapter.addFragment(new HotListFragment(), "Hot List");
        viewPager.setAdapter(homePageAdapter);

    }

    //    for function get data user
    private void getProfil() {
        Call<FeedUser> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAccept(), sessionManager.getSpAuthorization());
        call.enqueue(new Callback<FeedUser>() {
            @Override
            public void onResponse(Call<FeedUser> call, Response<FeedUser> response) {
                if (response.isSuccessful()) {
                    try {
                        name = response.body().getDataProfil().getName().toString();
                        email = response.body().getDataProfil().getEmail();
                        initComponetNavHeader();
                    } catch (Exception e) {
                        Log.d(TAG, " error :" + e);
                    }

                } else {
                    Toast.makeText(HomeActivity.this, "Response not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedUser> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Cek Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.shop_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            case R.id.action_cart:
                startActivity(new Intent(HomeActivity.this, ShopCartActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_category) {
            startActivity(new Intent(HomeActivity.this, CategoryActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_wishlist) {

        } else if (id == R.id.nav_inbox) {
            startActivity(new Intent(HomeActivity.this, InboxActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_notif) {
            startActivity(new Intent(HomeActivity.this, NotificationActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_purchase) {
            startActivity(new Intent(HomeActivity.this, ShopCartActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {
            startActivity(new Intent(HomeActivity.this, SettingActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_contactUs) {
            startActivity(new Intent(HomeActivity.this, ContactUsActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(HomeActivity.this, AboutActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_exit) {
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(HomeActivity.this, LoginActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //    for function header navihation
    public void initComponetNavHeader() {

        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);


        imageViewProfil = headerView.findViewById(R.id.imageViewProfil);
        tvName = headerView.findViewById(R.id.tvName);
        tvEmail = headerView.findViewById(R.id.tvEmail);

        tvName.setText(name);
        tvEmail.setText(email);

        imageViewProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfilActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

    }


}
