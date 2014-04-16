package jant.gunreloads.app.utility;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by anikolic on 4/15/14.
 */
public class DatePickerDialogFragment extends DialogFragment {
    private Fragment mFragment;

    public DatePickerDialogFragment(Fragment callback) {
        mFragment = callback;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) mFragment, 1980, 7, 16);
    }
}