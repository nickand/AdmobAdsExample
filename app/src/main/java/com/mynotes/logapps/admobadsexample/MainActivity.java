package com.mynotes.logapps.admobadsexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mynotes.logapps.admobadsexample.activities.ShowBannerAdActivity;
import com.mynotes.logapps.admobadsexample.activities.ShowInterstitialAdActivity;
import com.mynotes.logapps.admobadsexample.activities.ShowInterstitialBadActivity;
import com.mynotes.logapps.admobadsexample.activities.ShowNativeAdActivity;
import com.mynotes.logapps.admobadsexample.activities.ShowSmartBannerAdActivity;
import com.mynotes.logapps.admobadsexample.activities.ShowWaitInterstitialAdActivity;
import com.mynotes.logapps.admobadsexample.activities.SplashActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.showBannerButton)
    Button showBannerButton;
    @BindView(R.id.showSmartBannerButton)
    Button showSmartBannerButton;
    @BindView(R.id.showInterstitialButton)
    Button showInterstitialButton;
    @BindView(R.id.showWaitInterstitialButton)
    Button showWaitInterstitialButton;
    @BindView(R.id.showNativeAdButton)
    Button showNativeAdButton;
    @BindView(R.id.showInterstitialGoodPracticeButton)
    Button showInterstitialGoodPracticeButton;
    @BindView(R.id.showInterstitialBadPracticeButton)
    Button showInterstitialBadPracticeButton;

    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        initButtonsListener();

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    private void initButtonsListener() {
        showBannerButton.setOnClickListener(this);
        showSmartBannerButton.setOnClickListener(this);
        showInterstitialButton.setOnClickListener(this);
        showWaitInterstitialButton.setOnClickListener(this);
        showNativeAdButton.setOnClickListener(this);
        showInterstitialGoodPracticeButton.setOnClickListener(this);
        showInterstitialBadPracticeButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.showBannerButton:
                navigateTo(ShowBannerAdActivity.class);
            break;
            case R.id.showSmartBannerButton:
                navigateTo(ShowSmartBannerAdActivity.class);
            break;
            case R.id.showInterstitialButton:
                showInterstitial();
            break;
            case R.id.showWaitInterstitialButton:
                navigateTo(ShowWaitInterstitialAdActivity.class);
            break;
            case R.id.showNativeAdButton:
                navigateTo(ShowNativeAdActivity.class);
            break;
            case R.id.showInterstitialGoodPracticeButton:
                navigateTo(SplashActivity.class);
            break;
            case R.id.showInterstitialBadPracticeButton:
                showBadInterstitial();
            break;
        }
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            navigateTo(ShowInterstitialAdActivity.class);
            mInterstitialAd.show();
        } else {
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            loadInterstitial();
        }
    }

    public void showBadInterstitial() {
        MobileAds.initialize(this, getString(R.string.admobs_ad_unit_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("CDEA62E38BE1EFDAB07712A5BE460DD9").build();
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                startActivity(new Intent(MainActivity.this, ShowInterstitialBadActivity.class));
                finish();
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                startActivity(new Intent(MainActivity.this, ShowInterstitialBadActivity.class));
            }
        });
        mInterstitialAd.loadAd(adRequest);
    }

    private void loadInterstitial() {
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
    }

    public void navigateTo(Class activityClass) {
        Intent intent = new Intent(MainActivity.this, activityClass);
        startActivity(intent);
    }
}
