package com.foresee.ReplaySample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.verint.xm.sdk.DBA;

public class MaskingActivity extends AppCompatActivity {

    private ToggleButton maskEditTextToggleButton;
    private ToggleButton maskImageToggleButton;
    private EditText editText;
    private ImageView imageView;
    private TextView maskingTextLabel;
    private TextView maskingImageLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masking);

        maskEditTextToggleButton = (ToggleButton) findViewById(R.id.toggleButtonText);
        maskImageToggleButton = (ToggleButton) findViewById(R.id.toggleButtonImage);

        editText = (EditText) findViewById(R.id.maskingText);
        imageView = (ImageView) findViewById(R.id.maskingImage);

        maskingTextLabel = (TextView) findViewById(R.id.maskingTextLabel);
        maskingImageLabel = (TextView) findViewById(R.id.maskingImageLabel);

        maskEditTextToggleButton.setChecked(true);
        maskImageToggleButton.setChecked(true);

        maskEditTextToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (!maskEditTextToggleButton.isChecked()) {
                    DBA.unmaskView(editText);
                } else {
                    DBA.maskView(editText);
                }
            }
        });

        DBA.maskView(imageView);

        maskImageToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean imageToggleChecked) {
                if (!imageToggleChecked) {
                    DBA.unmaskView(imageView);
                } else {
                    DBA.maskView(imageView);
                }
            }
        });
    }
}