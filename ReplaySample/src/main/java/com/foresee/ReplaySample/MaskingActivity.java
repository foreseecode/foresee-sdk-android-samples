package com.foresee.ReplaySample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.verint.xm.sdk.Replay;

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

        maskEditTextToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (maskEditTextToggleButton.isChecked()) {
                    maskingTextLabel.setText("Text field masking: OFF");
                    Replay.unmaskView(editText);
                } else {
                    maskingTextLabel.setText("Text field masking: ON");
                    Replay.maskView(editText);
                }
            }
        });

       Replay.maskView(imageView);

        maskImageToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean imageToggleChecked) {
                if (imageToggleChecked) {
                    maskingImageLabel.setText("ImageView masking: OFF");
                    Replay.unmaskView(imageView);
                } else {
                    maskingImageLabel.setText("ImageView masking: ON");
                    Replay.maskView(imageView);
                }
            }
        });
    }
}