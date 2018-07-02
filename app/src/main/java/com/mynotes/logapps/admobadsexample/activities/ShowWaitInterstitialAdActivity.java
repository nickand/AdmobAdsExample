package com.mynotes.logapps.admobadsexample.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.mynotes.logapps.admobadsexample.R;
import com.mynotes.logapps.admobadsexample.utils.CircleAngleAnimation;
import com.mynotes.logapps.admobadsexample.utils.CircleTextView;

import java.util.Timer;

public class ShowWaitInterstitialAdActivity extends AppCompatActivity {

    private int time = 4;
    private Timer T;
    private CountDownTimer countDownTimer;
    private int millisInFuture = 4000;
    private CircleTextView circleTextView;
    private CircleAngleAnimation animation;
    private InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_wait_interstitial_ad);

        circleTextView = (CircleTextView) findViewById(R.id.circle);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.container_timer_ads);

        // initialize as invisible (could also do in xml)
        slideUp(this, linearLayout);

        MobileAds.initialize(this, getString(R.string.admobs_ad_unit_id));
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
        final AdRequest adRequest = new AdRequest.Builder().addTestDevice("CDEA62E38BE1EFDAB07712A5BE460DD9").build();

        countDownTimer = new CountDownTimer(millisInFuture, 800) {
            @Override
            public void onTick(long millisUntilFinished) {
                animation = new CircleAngleAnimation(circleTextView, 360);
                animation.setDuration(millisUntilFinished);
                circleTextView.startAnimation(animation);
                circleTextView.setText(String.valueOf(time));
                time--;
            }

            @Override
            public void onFinish() {
                animation.reset();
                slideDown(getApplicationContext(), linearLayout);
                showAd(adRequest);
            }
        }.start();
    }

    public static void slideUp(Context context, View view) {
        Animation bottomUp = AnimationUtils.loadAnimation(context, R.anim.bottom_up);
        view.startAnimation(bottomUp);
        view.setVisibility(View.VISIBLE);
    }

    public static void slideDown(final Context context, final View view) {
        Animation bottomDown = AnimationUtils.loadAnimation(context, R.anim.bottom_down);
        view.startAnimation(bottomDown);
        bottomDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void showAd(AdRequest adRequest) {
        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                startActivity(new Intent(ShowWaitInterstitialAdActivity.this, ShowInterstitialGoodActivity.class));
                finish();
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                startActivity(new Intent(ShowWaitInterstitialAdActivity.this, ShowInterstitialGoodActivity.class));
                finish();
            }
        });
        mInterstitialAd.loadAd(adRequest);
    }
}
