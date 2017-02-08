package com.quantityandconversion.hackernews.app;

import android.app.Application;
import android.os.StrictMode;

import com.quantityandconversion.hackernews.BuildConfig;

public class ReaderApp extends Application {
    @Override
    public void onCreate() {
        configureStrictMode();
        super.onCreate();
    }

    private void configureStrictMode() {
        if (!BuildConfig.DEBUG) {
            return;
        }
        StrictMode.setThreadPolicy(
                new StrictMode.ThreadPolicy.Builder()
                        .detectAll()
                        .penaltyDeath()
                        .penaltyLog()
                        .build());

        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder()
                        .detectAll()
                        .penaltyDeath()
                        .penaltyLog()
                        .build());
    }
}