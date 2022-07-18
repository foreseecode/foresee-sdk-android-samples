package com.foresee.ReplaySample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.verint.xm.sdk.Replay;
import com.verint.xm.sdk.common.Logging;
import com.verint.xm.sdk.common.constants.LogTags;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startRecording(View view) {
        if (Replay.isRecording()) {
            Logging.alwaysLog(Logging.LogLevel.INFO, LogTags.REPLAY_SESSION, "Recording is already in progress");
        } else {
            Replay.requestBeginRecording();
        }
    }

    public void stopRecording(View view) {
        Replay.stopRecording();
    }

    public void masking(View view) {
        startActivity(new Intent(this, MaskingActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}