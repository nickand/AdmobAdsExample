package com.mynotes.logapps.admobadsexample.utils;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAngleAnimation extends Animation {

    private CircleTextView circleTextView;

    private float oldAngle;
    private float newAngle;

    public CircleAngleAnimation(CircleTextView circleTextView, int newAngle) {
        this.oldAngle = circleTextView.getAngle();
        this.newAngle = newAngle;
        this.circleTextView = circleTextView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        circleTextView.setAngle(angle);
        circleTextView.requestLayout();
    }
}