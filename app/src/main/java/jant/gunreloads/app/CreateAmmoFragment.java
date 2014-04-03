package jant.gunreloads.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import jant.gunreloads.app.sql.helper.DatabaseHelper;
import jant.gunreloads.app.sql.model.Bullet;
import jant.gunreloads.app.sql.model.Enums;
import jant.gunreloads.app.sql.model.Manufacturer;

/**
 * Created by Adam on 3/30/2014.
 */
public class CreateAmmoFragment extends Fragment {
    private Button createAmmoButton;
    private TextView createdAmmoId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(container != null) {
            container.removeAllViews();
        }
        View rootView = inflater.inflate(R.layout.create_ammo, container, false);

        createdAmmoId = (TextView) rootView.findViewById(R.id.createdAmmoId);
        createAmmoButton = (Button) rootView.findViewById(R.id.create_ammo_button);
        createAmmoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parseTextInput(view);
//                Toast.makeText(getActivity().getApplicationContext(), "Scanning Ammo", Toast.LENGTH_SHORT).show();
//                IntentIntegrator qrReadIntent = new IntentIntegrator(CreateAmmoFragment.this);
//                qrReadIntent.initiateScan();
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case IntentIntegrator.REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    IntentResult qrResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                    if(qrResult != null) {
                        String contents = qrResult.getContents();
                        createdAmmoId.setText(contents);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void parseTextInput(View v) {
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
        Toast.makeText(getActivity().getApplicationContext(), "Parsing Input", Toast.LENGTH_SHORT).show();
        View parent = (View) v.getParent();
        AutoCompleteTextView bulletManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.bullet_manufacturer);
        AutoCompleteTextView bulletStyle = (AutoCompleteTextView) parent.findViewById(R.id.bullet_style);
        AutoCompleteTextView bulletWeight = (AutoCompleteTextView) parent.findViewById(R.id.bullet_weight);
        AutoCompleteTextView bulletCaliber = (AutoCompleteTextView) parent.findViewById(R.id.bullet_caliber);

        long manufacturerId = db.findManufacturerByName(bulletManufacturer.getText().toString());
        if(manufacturerId == 0) {
            Manufacturer manufacturer = new Manufacturer(bulletManufacturer.getText().toString());
            manufacturerId = db.createManufacturer(manufacturer);
        }

        Bullet newBullet = new Bullet();
        newBullet.setCaliber(Enums.Caliber.valueOf(bulletCaliber.getText().toString()));
        newBullet.setManufacturerId(manufacturerId);
        newBullet.setStyle(bulletStyle.getText().toString());
        newBullet.setWeight(bulletWeight.getText().toString());
        long newBulletId = db.createBullet(newBullet);

        String newBulletMade = "New Bullet: " + newBulletId;

        Toast.makeText(getActivity().getApplicationContext(), newBulletMade, Toast.LENGTH_LONG).show();
    }

}
