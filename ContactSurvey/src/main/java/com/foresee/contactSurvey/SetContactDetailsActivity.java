package com.foresee.contactSurvey;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.foresee.sdk.ForeSee;
import com.foresee.sdk.common.configuration.ContactType;

/**
 * Created by alan.wang on 11/20/18.
 */

public class SetContactDetailsActivity extends AppCompatActivity {
    // Variables
    private EditText contactInfoEmail;
    private EditText contactInfoPhone;
    private RadioGroup preferredContactType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Do normal UI setup
        setContentView(R.layout.contact_details);

        // Back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Find UI components
        contactInfoEmail = (EditText)findViewById(R.id.contactInfoEmail);
        contactInfoPhone = (EditText)findViewById(R.id.contactInfoPhoneNumber);
        preferredContactType = (RadioGroup)findViewById(R.id.preferredContactType);
    }

    /**
     * Dispatch onResume() to fragments.  Note that for better inter-operation
     * with older versions of the platform, at the point of this call the
     * fragments attached to the activity are <em>not</em> resumed.  This means
     * that in some cases the previous state may still be saved, not allowing
     * fragment transactions that modify the state.  To correctly interact
     * with fragments in their proper state, you should instead override
     * {@link #onResumeFragments()}.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Setup UI components
        contactInfoEmail.setText(ForeSee.getContactDetails(ContactType.Email));
        contactInfoPhone.setText(ForeSee.getContactDetails(ContactType.PhoneNumber));
        ContactType type = ForeSee.getPreferredContactType();
        if (type != null) {
            switch (type) {
                case Email:
                    preferredContactType.check(R.id.preferredContactTypeEmail);
                    break;
                case PhoneNumber:
                    preferredContactType.check(R.id.preferredContactTypePhoneNumber);
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        ForeSee.setContactDetails(ContactType.Email,
                contactInfoEmail.getText().toString());
        ForeSee.setContactDetails(ContactType.PhoneNumber,
                contactInfoPhone.getText().toString());

        switch (preferredContactType.getCheckedRadioButtonId()) {
            case R.id.preferredContactTypeEmail:
                ForeSee.setPreferredContactType(ContactType.Email);
                break;
            case R.id.preferredContactTypePhoneNumber:
                ForeSee.setPreferredContactType(ContactType.PhoneNumber);
                break;
        }

    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
