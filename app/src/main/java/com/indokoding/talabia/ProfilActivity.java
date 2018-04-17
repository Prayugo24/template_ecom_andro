package com.indokoding.talabia;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.indokoding.apiholder.utilsApi;
import com.indokoding.model.FeedUser;
import com.indokoding.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {
    @BindView(R.id.tvName)
    TextView tvName;

    @BindView(R.id.tvEmail)
    TextView tvEmail;

    @BindView(R.id.tvPhone)
    TextView tvPhone;

    @BindView(R.id.tvAddress)
    TextView tvAddress;


    private SessionManager sessionManager;
    private com.indokoding.apiholder.baseApiService baseApiService;

    private String name;
    private String email;
    private String phone;
    private String address;
    private static final String TAG = "ProfilActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(this);
//        button back toolbar
        ActionBar menuBack = getSupportActionBar();
        menuBack.setDisplayShowHomeEnabled(true);
        menuBack.setDisplayHomeAsUpEnabled(true);

        getProfil();

    }

    @OnClick(R.id.btSetting)
    void settingProfil() {
        Intent intent = new Intent(ProfilActivity.this, SettingProfilActivity.class);
        startActivity(intent);
    }

    private void getProfil() {
        Call<FeedUser> call = baseApiService.getAllProfile(sessionManager.getSpContenttype(),
                sessionManager.getSpAccept(), sessionManager.getSpAuthorization());
        call.enqueue(new Callback<FeedUser>() {
            @Override
            public void onResponse(Call<FeedUser> call, Response<FeedUser> response) {
                if (response.isSuccessful()) {
                    try {
                        name = response.body().getDataProfil().getName();
                        email = response.body().getDataProfil().getEmail();
                        phone = response.body().getDataProfil().getMobileNumber();
                        address = "";
                        setProfil();
                    } catch (Exception e) {
                        Log.d(TAG, " error :" + e);
                    }
                } else {
                    Toast.makeText(ProfilActivity.this, "Response not success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedUser> call, Throwable t) {
                Toast.makeText(ProfilActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProfil() {
        tvName.setText(name);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvAddress.setText(address);
    }
}
