package com.foresee.demo.custominvite;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

    }


    public void launchDefaultInviteActivity(View view) {
        startActivity(new Intent(this, DefaultInviteActivity.class));
    }

    public void launchCustomInvite1Activity(View view) {
        startActivity(new Intent(this, CustomInvite1Activity.class));
    }

    public void launchCustomInvite2Activity(View view) {
        startActivity(new Intent(this, CustomInvite2Activity.class));
    }

}
