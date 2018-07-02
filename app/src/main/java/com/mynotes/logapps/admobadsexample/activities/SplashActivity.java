package com.mynotes.logapps.admobadsexample.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mynotes.logapps.admobadsexample.R;

public class SplashActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();

        if (!intent.getBooleanExtra("badInterstitial", false)) {
            showAd();
        }
    }

    public void showAd() {
        MobileAds.initialize(this, getString(R.string.admobs_ad_unit_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("CDEA62E38BE1EFDAB07712A5BE460DD9").build();
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                startActivity(new Intent(SplashActivity.this, ShowInterstitialGoodActivity.class));
                finish();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                startActivity(new Intent(SplashActivity.this, ShowInterstitialGoodActivity.class));
                finish();
            }
        });
        mInterstitialAd.loadAd(adRequest);
    }

}
