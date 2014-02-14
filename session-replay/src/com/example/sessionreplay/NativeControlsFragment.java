package com.example.sessionreplay;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.foresee.sdk.ForeSee;

/**
 * Created by mhan on 2014-02-14.
 */
public class NativeControlsFragment extends Fragment {
    private TextView mLabel1;
    private TextView mLabel2;

    private Button mMaskButton;
    private Button mUnmaskButton;

    private EditText mTextField;

    private boolean mIsLabelMasked;
    private boolean mIsTextFieldMasked;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.native_controls_fragment, container, false);

        mIsLabelMasked = false;
        mIsTextFieldMasked = true;

        mLabel1 = (TextView)root.findViewById(R.id.label1);
        mLabel2 = (TextView)root.findViewById(R.id.label2);
        mTextField = (EditText)root.findViewById(R.id.textField);
        mMaskButton = (Button)root.findViewById(R.id.maskButton);
        mUnmaskButton = (Button)root.findViewById(R.id.unmaskButton);

        mMaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NATIVE", "mask");
                if (mIsLabelMasked) {
                    ForeSee.unmaskView(view);
                    mMaskButton.setText("Mask Label");
                }
                else {
                    ForeSee.maskView(view);
                    mMaskButton.setText("Unmask Label");
                }
                mIsLabelMasked = !mIsLabelMasked;
            }
        });

        mUnmaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("NATIVE", "unmask");

                if (mIsTextFieldMasked) {
                    ForeSee.unmaskView(view);
                    mUnmaskButton.setText("Mask Text Views");
                }
                else {
                    ForeSee.maskView(view);
                    mUnmaskButton.setText("Unmask Text Views");
                }

                mIsTextFieldMasked = !mIsTextFieldMasked;
            }
        });

        return root;
    }
}