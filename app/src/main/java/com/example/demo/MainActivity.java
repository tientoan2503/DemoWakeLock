package com.example.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.PowerManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wakeLock = powerManager
                .newWakeLock(
                        PowerManager.FULL_WAKE_LOCK |
                                PowerManager.ACQUIRE_CAUSES_WAKEUP,
                        "myapp:mywakelocktag");

        findViewById(R.id.btn_set).setOnClickListener(v -> {
            long second = Long.parseLong(((EditText) findViewById(R.id.edt_second)).getText().toString()) * 1000;
            new Handler().postDelayed(() -> wakeLock.acquire(), second);
        });
    }
}