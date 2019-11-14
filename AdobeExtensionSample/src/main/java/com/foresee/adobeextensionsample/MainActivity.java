package com.foresee.adobeextensionsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.foresee.sdk.ForeSee;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void resetCounters(View view) {
        // Reset the SDK state
        ForeSee.resetState();
    }
}
