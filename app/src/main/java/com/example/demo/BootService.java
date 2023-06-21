package com.example.demo;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by @rikka on: 21/06/2023
 */
public class BootService extends AccessibilityService {
    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.example.demo");
        startActivity(launchIntent);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

    }

    @Override
    public void onInterrupt() {

    }
}
