package com.indokoding.talabia;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
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

import com.indokoding.apiholder.utilsApi;
import com.indokoding.model.FeedUser;
import com.indokoding.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.nav_view)
    NavigationView navigationView;


    private TextView tvName;
    private TextView tvEmail;
    private ImageView imageViewProfil;
    private SessionManager sessionManager;
    private Dialog dialogAbout;
    private com.indokoding.apiholder.baseApiService baseApiService;

    private static final String TAG = "HomeActivity";
    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        baseApiService = utilsApi.getApiServices();
        dialogAbout = new Dialog(this);
        setSupportActionBar(toolbar);
        getProfil();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeActivity/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            startActivity(new Intent(SettingActivity.this, HomeActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        } else if (id == R.id.nav_category) {
            startActivity(new Intent(SettingActivity.this, CategoryActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

        } else if (id == R.id.nav_wishlist) {

        } else if (id == R.id.nav_inbox) {
            startActivity(new Intent(SettingActivity.this, InboxActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_notif) {
            startActivity(new Intent(SettingActivity.this, NotificationActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_purchase) {
            startActivity(new Intent(SettingActivity.this, ShopCartActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_setting) {

        } else if (id == R.id.nav_contactUs) {
            startActivity(new Intent(SettingActivity.this, ContactUsActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(SettingActivity.this, AboutActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));

        } else if (id == R.id.nav_exit) {
            sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, false);
            startActivity(new Intent(SettingActivity.this, LoginActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();

        }


//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //for function haeder navigation
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
                startActivity(new Intent(SettingActivity.this, ProfilActivity.class).
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            }
        });

    }


    //for function get data user
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
                    Toast.makeText(SettingActivity.this, "Response not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedUser> call, Throwable t) {
                Toast.makeText(SettingActivity.this, "Cek Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
