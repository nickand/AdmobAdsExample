package com.mynotes.logapps.admobadsexample.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mynotes.logapps.admobadsexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowSmartBannerAdActivity extends AppCompatActivity {

    @BindView(R.id.adViewBanner)
    public AdView adViewBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_smart_banner_ad);

        ButterKnife.bind(this);

        loadAdBanner();
    }

    public void loadAdBanner(){
        AdRequest adRequest = new AdRequest.Builder().build();
        adViewBanner.loadAd(adRequest);
    }
}
