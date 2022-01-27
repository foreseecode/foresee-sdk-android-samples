package com.foresee.demo.custominvite;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.foresee.sdk.ForeSee;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void launchCustomInvite1Activity(View view) {
        startActivity(new Intent(this, CustomInvite1Activity.class));
    }

    public void launchCustomInvite2Activity(View view) {
        startActivity(new Intent(this, CustomInvite2Activity.class));
    }

    public void resetState(View view) {
        // Reset the state of the ForeSee SDK. So that we may be eligible for a new invite
        // based on the criteria in foresee_configuration.json
        ForeSee.resetState();
    }
}
