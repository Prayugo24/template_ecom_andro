package com.indokoding.talabia;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
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

import com.hbb20.CountryCodePicker;
import com.indokoding.apiholder.baseApiService;
import com.indokoding.apiholder.utilsApi;
import com.indokoding.model.DataRegister;
import com.indokoding.model.FeedLogin;
import com.indokoding.model.registerUser;
import com.indokoding.session.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.tvSignIn)
    TextView tvSignIn;

    @BindView(R.id.tiName)
    TextInputLayout tiName;

    @BindView(R.id.tiEmail)
    TextInputLayout tiEmail;

    @BindView(R.id.tiPassword1)
    TextInputLayout tiPassword1;

    @BindView(R.id.tiPassword2)
    TextInputLayout tiPassword2;

    @BindView(R.id.tiPhone)
    TextInputLayout tiPhone;

    @BindView(R.id.edName)
    EditText edName;

    @BindView(R.id.edEmail)
    EditText edEmail;

    @BindView(R.id.edPassword1)
    EditText edPassword1;

    @BindView(R.id.edPassword2)
    EditText edPassword2;

    @BindView(R.id.edPhone)
    EditText edPhone;

    @BindView(R.id.ccp)
    CountryCodePicker countryCodePicker;

    private baseApiService baseApiService;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private static String dataMessage;
    private String numberCountryCode = "965";

    private static String ContentType = "application/x-www-form-urlencoded";
    private static String Accept = "application/json";
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
//        function class  session
        sessionManager = new SessionManager(this);
//        function class baseApi
        baseApiService = utilsApi.getApiServices();
//                button back toolbar
        ActionBar menuBack = getSupportActionBar();
        menuBack.setDisplayShowHomeEnabled(true);
        menuBack.setDisplayHomeAsUpEnabled(true);

//        for numbering country code
        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                numberCountryCode = countryCodePicker.getSelectedCountryCode();
                Log.d("Country Code : ", numberCountryCode);
            }
        });

    }

    //    function click textview signIn
    @OnClick(R.id.tvSignIn)
    void signIn() {
        startActivity(new Intent(RegisterActivity.this, HomeActivity.class).
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }

    //    function clic button register
    @OnClick(R.id.btRegister)
    void register() {
        registerRequest();
    }

    //    register function
    public void registerRequest() {
        String name = edName.getText().toString();
        String email = edEmail.getText().toString();
        String password1 = edPassword1.getText().toString();
        String password2 = edPassword2.getText().toString();
        String phoneNumber = edPhone.getText().toString();

        if (!validName()) return;
        if (!validEmail()) return;
        if (!validNumberPhone()) return;
        if (!validPassword1()) return;
        if (!validPAssword2()) return;


        progressDialog = ProgressDialog.show(RegisterActivity.this, null, "Loading...", true, false);
        registerUser registerUser = new registerUser(name, email, password1, password2, phoneNumber, numberCountryCode);
        Call<DataRegister> call = baseApiService.registerReques(registerUser);

        call.enqueue(new Callback<DataRegister>() {
            @Override
            public void onResponse(Call<DataRegister> call, Response<DataRegister> response) {
                if (response.isSuccessful()) {
                    progressDialog.dismiss();
                    dataMessage = response.body().getDataRegister().toString();
                    Toast.makeText(RegisterActivity.this, " " + dataMessage, Toast.LENGTH_SHORT).show();


//                    try {
//
//                        Toast.makeText(RegisterActivity.this, "RegisterActivity Succes", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class).
//                                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
//                        finish();
//                    } catch (Exception e) {
//                        Log.d(TAG, "error : " + e);
//                    }
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, "RegisterActivity not correct it", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataRegister> call, Throwable t) {
                progressDialog.dismiss();
                Log.w("error", t);
                Toast.makeText(RegisterActivity.this, "Cek Connection", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public boolean validName() {
        String name = edName.getText().toString();
        if (name.isEmpty()) {
            tiName.setError("Input your Name correctly");
            reQuestFocus(edName);
            return false;
        } else {
            tiName.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validEmail() {
        String email = edEmail.getText().toString();
        if (email.isEmpty() || !isValidEmail(email)) {
            tiEmail.setError("Input your Email correctly");
            reQuestFocus(edEmail);
            return false;
        } else {
            tiEmail.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validPassword1() {
        String password1 = edPassword1.getText().toString();
        if (password1.isEmpty()) {
            tiPassword1.setError("Input your Password correctly");
            reQuestFocus(edPassword1);
            return false;
        } else if (password1.length() < 6) {
            tiPassword1.setError("Input your Password must be 6 caracter");
            reQuestFocus(edPassword1);
            return false;
        } else {
            tiPassword1.setErrorEnabled(false);

        }
        return true;
    }


    public boolean validPAssword2() {
        String password2 = edPassword2.getText().toString();
        String password1 = edPassword1.getText().toString();
        if (password2.isEmpty()) {
            tiPassword2.setError("Input your Password correctly");
            reQuestFocus(edPassword2);
            return false;
        } else if (password2.length() < 6) {
            tiPassword2.setError("Input your Password must be 6 caracter");
            reQuestFocus(edPassword2);
            return false;
        } else if (!password2.equals(password1)) {
            tiPassword1.setError("Input your Password must be same");
            tiPassword2.setError("Input your Password must be same");
            reQuestFocus(edPassword1);
            return false;
        } else if (password2.equals(password1)) {
            tiPassword2.setErrorEnabled(false);
            tiPassword1.setErrorEnabled(false);
        } else {
            tiPassword2.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validNumberPhone() {
        String numberPhone = edPhone.getText().toString();
        if (numberPhone.equals("")) {
            tiPhone.setError("Input your Phone Number\ncorrectly");
            reQuestFocus(edPhone);
            return false;
        } else if (numberPhone.length() < 6) {
            tiPhone.setError("Input your Phone number\nmust be 6 caracter");
            reQuestFocus(edPhone);
            return false;
        } else {
            tiPhone.setErrorEnabled(false);
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
