package com.mynotes.logapps.admobadsexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mynotes.logapps.admobadsexample.R;

public class ShowInterstitialBadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_interstitial_bad);

        Intent intent = new Intent(ShowInterstitialBadActivity.this, SplashActivity.class);
        intent.putExtra("badInterstitial", true);
        startActivity(intent);
    }
}
