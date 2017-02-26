package com.quantityandconversion.hackernews.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class QacActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateFunctionality();
    }

    protected abstract void onCreateFunctionality();
}
