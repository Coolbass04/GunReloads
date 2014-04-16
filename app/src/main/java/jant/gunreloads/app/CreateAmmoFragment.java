package jant.gunreloads.app;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

import jant.gunreloads.app.sql.helper.DatabaseHelper;
import jant.gunreloads.app.sql.model.Bullet;
import jant.gunreloads.app.sql.model.Caliber;
import jant.gunreloads.app.sql.model.Manufacturer;
import jant.gunreloads.app.sql.model.Powder;
import jant.gunreloads.app.sql.model.Primer;

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
        setBulletCalibers(rootView);

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

    private void setBulletCalibers(View rootView) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_dropdown_item_1line, Caliber.getCalibers());
        final AutoCompleteTextView bulletCaliberTextView = (AutoCompleteTextView) rootView.findViewById(R.id.bullet_caliber);
        bulletCaliberTextView.setThreshold(0);
        bulletCaliberTextView.setAdapter(adapter);
        bulletCaliberTextView
                .setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean hasFocus) {
                        if (!hasFocus) {
                            String val = bulletCaliberTextView.getText() + "";
                            if (!Caliber.containsValue(val)) {
                                bulletCaliberTextView.setError("Invalid Caliber");
                            }
                        }
                    }
                });
    }

    private void parseTextInput(View v) {
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
        View parent = (View) v.getParent();
        boolean isValidInput = verifyBulletFields(db, parent);
        isValidInput &= verifyPowderFields(db, parent);
        isValidInput &= verifyPrimerFields(db, parent);

        if(isValidInput) {
            insertBullet(db, parent);
            insertPowder(db, parent);
            insertPrimer(db, parent);
        }
        else {
            Toast.makeText(getActivity().getApplicationContext(), "Enter in all required fields", Toast.LENGTH_LONG).show();
        }
    }

    private void insertBullet(DatabaseHelper db, View parent) {
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
        newBullet.setCaliber(bulletCaliber.getText().toString());
        newBullet.setManufacturerId(manufacturerId);
        newBullet.setStyle(bulletStyle.getText().toString());
        newBullet.setWeight(bulletWeight.getText().toString());
        long newBulletId = db.createBullet(newBullet);

        String newBulletMade = "New Bullet: " + newBulletId;

        Toast.makeText(getActivity().getApplicationContext(), newBulletMade, Toast.LENGTH_LONG).show();
    }

    private boolean verifyBulletFields(DatabaseHelper db, View parent) {
        boolean validInput = true;
        AutoCompleteTextView bulletManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.bullet_manufacturer);
        AutoCompleteTextView bulletStyle = (AutoCompleteTextView) parent.findViewById(R.id.bullet_style);
        AutoCompleteTextView bulletWeight = (AutoCompleteTextView) parent.findViewById(R.id.bullet_weight);
        AutoCompleteTextView bulletCaliber = (AutoCompleteTextView) parent.findViewById(R.id.bullet_caliber);

        if(bulletManufacturer.getText().toString().length() <= 0) {
            validInput = updateField(bulletManufacturer, R.string.manufacturer_hint);
        }
        if(bulletStyle.getText().toString().length() <= 0 ) {
            validInput = updateField(bulletStyle, R.string.style_hint);
        }
        if(bulletWeight.getText().toString().length() <= 0) {
            validInput = updateField(bulletWeight, R.string.weight_hint);
        }
        if(bulletCaliber.getText().toString().length() <= 0) {
            validInput = updateField(bulletCaliber, R.string.caliber_hint);
        }

        return validInput;
    }

    private void insertPowder(DatabaseHelper db, View parent) {
        AutoCompleteTextView powderManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.powder_manufacturer);
        AutoCompleteTextView powderType = (AutoCompleteTextView) parent.findViewById(R.id.powder_type);

        long manufacturerId = db.findManufacturerByName(powderManufacturer.getText().toString());
        if(manufacturerId == 0) {
            Manufacturer manufacturer = new Manufacturer(powderManufacturer.getText().toString());
            manufacturerId = db.createManufacturer(manufacturer);
        }
        Powder newPowder = new Powder();
        newPowder.setManufacturerId(manufacturerId);
        newPowder.setType(powderType.getText().toString());
        long newPowderId = db.createPowder(newPowder);

        String newPowderMade = "New Powder: " + newPowderId;

        Toast.makeText(getActivity().getApplicationContext(), newPowderMade, Toast.LENGTH_LONG).show();
    }

    private boolean verifyPowderFields(DatabaseHelper db, View parent) {
        boolean validInput = true;
        AutoCompleteTextView powderManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.powder_manufacturer);
        AutoCompleteTextView powderType = (AutoCompleteTextView) parent.findViewById(R.id.powder_type);

        if(powderManufacturer.getText().toString().length() <= 0) {
            validInput = updateField(powderManufacturer, R.string.manufacturer_hint);
        }
        if(powderType.getText().toString().length() <= 0 ) {
            validInput = updateField(powderType, R.string.type_hint);
        }

        return validInput;
    }

    private void insertPrimer(DatabaseHelper db, View parent) {
        AutoCompleteTextView primerManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.primer_manufacturer);
        AutoCompleteTextView primerType = (AutoCompleteTextView) parent.findViewById(R.id.primer_type);

        long manufacturerId = db.findManufacturerByName(primerManufacturer.getText().toString());
        if(manufacturerId == 0) {
            Manufacturer manufacturer = new Manufacturer(primerManufacturer.getText().toString());
            manufacturerId = db.createManufacturer(manufacturer);
        }
        Primer newPrimer = new Primer();
        newPrimer.setManufacturerId(manufacturerId);
        newPrimer.setType(primerType.getText().toString());
        long newPrimerId = db.createPrimer(newPrimer);

        String newPrimerMade = "New Primer: " + newPrimerId;

        Toast.makeText(getActivity().getApplicationContext(), newPrimerMade, Toast.LENGTH_LONG).show();
    }

    private boolean verifyPrimerFields(DatabaseHelper db, View parent) {
        boolean validInput = true;
        AutoCompleteTextView primerManufacturer = (AutoCompleteTextView) parent.findViewById(R.id.primer_manufacturer);
        AutoCompleteTextView primerType = (AutoCompleteTextView) parent.findViewById(R.id.primer_type);

        if(primerManufacturer.getText().toString().length() <= 0) {
            validInput = updateField(primerManufacturer, R.string.manufacturer_hint);
        }
        if(primerType.getText().toString().length() <= 0 ) {
            validInput = updateField(primerType, R.string.type_hint);
        }

        return validInput;
    }

    private boolean updateField(AutoCompleteTextView field, int hintString) {
        field.setHintTextColor(Color.RED);
        field.setHint(hintString);
        return false;
    }

}
