package com.indokoding.talabia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.indokoding.apiholder.baseApiService;
import com.indokoding.apiholder.utilsApi;
import com.indokoding.model.FeedLogin;
import com.indokoding.model.LoginUser;
import com.indokoding.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.tvRegister)
    TextView tvRegister;

    @BindView(R.id.edEmail)
    EditText edEmail;

    @BindView(R.id.edPassword)
    EditText edPassword;
    
    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;

    @BindView(R.id.tiPassword)
    TextInputLayout tiPassword;

    private baseApiService baseApiService;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    private static String token;
    private static String ContentType = "application/x-www-form-urlencoded";
    private static String Accept = "application/json";
    private static final String TAG = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        baseApiService = utilsApi.getApiServices();
        sessionManager = new SessionManager(this);
//        if (sessionManager.getSpSessionLogin()) {
//            startActivity(new Intent(LoginActivity.this, HomeActivity.class).
//                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//            finish();
//        }

    }


    @OnClick(R.id.btSignIn)
    void signIn() {
//        loginRequest();
        startActivity(new Intent(LoginActivity.this, HomeActivity.class).
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
    }

    @OnClick(R.id.tvRegister)
    void register() {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }


    public void loginRequest() {
        String email = edEmail.getText().toString();
        String password = edPassword.getText().toString();
        if (!validEmail())
            return;
        if (!validPassword())
            return;

        progressDialog = ProgressDialog.show(LoginActivity.this, null
                , "Loading...", true, false);


        LoginUser loginUser = new LoginUser(email, password);
        Call<FeedLogin> call = baseApiService.loginRequest(loginUser);

        call.enqueue(new Callback<FeedLogin>() {
            @Override
            public void onResponse(Call<FeedLogin> call, Response<FeedLogin> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    token = "Bearer " + response.body().getData().getToken();
                    try {
                        sessionManager.saveSPString(sessionManager.SP_CONTENTTYPE, ContentType);
                        sessionManager.saveSPString(sessionManager.SP_ACCEPT, Accept);
                        sessionManager.saveSPString(sessionManager.SP_AUTHORIZATION, token);
                        sessionManager.saveSPBoolean(sessionManager.SP_SESIONLOGIN, true);
                        Toast.makeText(LoginActivity.this, "Login Succes", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class).
                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                        finish();
                    } catch (Exception e) {
                        Log.d(TAG, "error : " + e);
                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, "Login not correct it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<FeedLogin> call, Throwable t) {
                progressDialog.dismiss();
                Log.w("error", t);
                Toast.makeText(LoginActivity.this, "Cek Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private boolean validEmail() {
        String email = edEmail.getText().toString();
        if (email.isEmpty() || !isValidEmail(email)) {
            tiEmail.setError("input your Email correctly");
            reQuestFocus(edEmail);
            return false;
        } else {
            tiEmail.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validPassword() {
        String password = edPassword.getText().toString();
        if (password.isEmpty()) {
            tiPassword.setError("input your Password correctly");
            reQuestFocus(edPassword);
            return false;
        } else {
            tiPassword.setErrorEnabled(false);
        }
        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void reQuestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


}
