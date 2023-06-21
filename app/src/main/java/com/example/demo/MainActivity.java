package com.example.demo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestAccessibilityPermission();

        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager
                .newWakeLock(
                        PowerManager.FULL_WAKE_LOCK |
                                PowerManager.ACQUIRE_CAUSES_WAKEUP,
                        "myapp:mywakelocktag");

        findViewById(R.id.btn_set).setOnClickListener(v -> {
            long second = Long.parseLong(
                    ((EditText) findViewById(R.id.edt_second)).getText().toString()) * 1000;
            new Handler().postDelayed(() -> {
                wakeLock.acquire(100);
            }, second);
        });
    }

    private void requestAccessibilityPermission() {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        if (!accessibilityManager.isEnabled()) {
            // Ask the user to enable the accessibility service
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            startService(new Intent(this, BootService.class));
        }
    }
}