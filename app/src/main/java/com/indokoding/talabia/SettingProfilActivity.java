package com.indokoding.talabia;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




public class SettingProfilActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profil);
//        button back toolbar
        ActionBar menuBack = getSupportActionBar();
        menuBack.setDisplayShowHomeEnabled(true);
        menuBack.setDisplayHomeAsUpEnabled(true);
    }
}
