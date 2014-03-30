package jant.gunreloads.app.utility;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jant.gunreloads.app.R;

/**
 * Created by Adam on 3/29/2014.
 */
public class CreateAmmoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_ammo, container, false);
        return view;
    }

    public void setText(String item) {
        TextView view = (TextView) getView().findViewById(R.id.section_label);
        view.setText(item);
    }
}
