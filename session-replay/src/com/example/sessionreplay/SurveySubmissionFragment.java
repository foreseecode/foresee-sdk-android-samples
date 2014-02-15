package com.example.sessionreplay;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.foresee.sdk.ForeSee;

/**
 * Created by mhan on 2014-02-14.
 */
public class SurveySubmissionFragment extends Fragment {
    private Button mShowInviteButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.survey_submission_fragment, container, false);

        mShowInviteButton = (Button)root.findViewById(R.id.inviteButton);
        mShowInviteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForeSee.showInviteForSurveyID("app_test_1");
            }
        });

        return root;
    }
}