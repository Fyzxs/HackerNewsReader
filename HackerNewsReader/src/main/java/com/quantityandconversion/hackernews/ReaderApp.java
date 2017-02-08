package com.quantityandconversion.hackernews;

import android.app.Application;
import android.os.StrictMode;

public class ReaderApp extends Application {
    @Override
    public void onCreate() {
        if (BuildConfig.DEBUG) {
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
        super.onCreate();
    }
}