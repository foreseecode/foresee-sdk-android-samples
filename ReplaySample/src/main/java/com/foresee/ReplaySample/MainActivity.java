package com.foresee.ReplaySample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.verint.xm.sdk.DBA;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startRecording(View view) {
        if (DBA.isRecording()) {
            Log.i("DBA", "Recording is already in progress");
        } else {
            DBA.requestBeginRecording();
        }
    }

    public void stopRecording(View view) {
        DBA.stopRecording();
    }

    public void masking(View view) {
        startActivity(new Intent(this, MaskingActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}