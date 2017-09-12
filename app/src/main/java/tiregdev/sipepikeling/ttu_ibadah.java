package tiregdev.sipepikeling;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import tiregdev.sipepikeling.model.TTU;
import tiregdev.sipepikeling.utils.SessionString;

public class ttu_ibadah extends AppCompatActivity {

    EditText frmNamaTempat, frmNamaPengurus, frmAlamat, frmJmlJamaah;
    Button btnSend;
    RadioGroup[] rg = new RadioGroup[37];
    RadioButton[] rb;
    double[] nTPM = new double[] {5, 5,
            4, 3, 3,
            4, 3, 3,
            5, 3, 2,
            6, 4,
            3, 3, 2, 2,
            5, 3, 2,
            6, 4,
            5, 5,
            10, 10,
            5, 3, 2,
            5, 5,
            3, 3, 2, 2,
            6, 4,
            5, 5,
            4, 3, 3 };

    double[] bTPM = new double[] {4, 4,
            4, 4, 4,
            5, 5, 5,
            5, 5, 5,
            5, 5,
            2, 2, 2, 2,
            5, 5, 5,
            4, 4,
            8, 8,
            5, 5,
            8, 8, 8,
            12, 12,
            7, 7, 7, 7,
            8, 8,
            8, 8,
            10, 10, 10 };
    private double lat;
    private double lng;
    SimpleDateFormat sdf;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttu_ibadah);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setAuthInstance();
        setSharedPreferences();
        setDatabaseInstance();
    }

    private void setInit(){
        frmNamaTempat = (EditText)findViewById(R.id.namaTempat);
        frmNamaPengurus = (EditText)findViewById(R.id.namaPengurus);
        frmAlamat = (EditText)findViewById(R.id.alamat);
        frmJmlJamaah = (EditText)findViewById(R.id.jmlJamaah);
        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        for(int i = 0; i<rg.length; i++)
        {
            int rID = getResources().getIdentifier("rg" + i, "id", this.getBaseContext().getPackageName());
            rg[i] = (RadioGroup)findViewById(rID);
            rg[i].removeAllViews();
            String[] yesNo = getResources().getStringArray(R.array.choice_array);
            rb = new RadioButton[2];
            for(int j = 0; j<rb.length; j++){
                rb[j] = new RadioButton(this);
                rb[j].setId(100 + j);
                rb[j].setText(yesNo[j]);
                rg[i].addView(rb[j]);
            }
        }
        btnSend = (Button)findViewById(R.id.send);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });
        frmAlamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAutocompleteActivity();
            }
        });
    }
    private void openAutocompleteActivity() {
        try {
            Intent intent = new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                    .build(this);
            startActivityForResult(intent, REQUEST_CODE_AUTOCOMPLETE);
        } catch (GooglePlayServicesRepairableException e) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, e.getConnectionStatusCode(),
                    0 /* requestCode */).show();
        } catch (GooglePlayServicesNotAvailableException e) {
            String message = "Google Play Services is not available: " +
                    GoogleApiAvailability.getInstance().getErrorString(e.errorCode);

            Log.e("ERROR", message);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_AUTOCOMPLETE) {
            if (resultCode == RESULT_OK) {
                Place place = PlaceAutocomplete.getPlace(this, data);
                Log.i("TAG", "Place Selected: " + place.getName());
                String addressPlace = String.valueOf(place.getAddress());
                frmAlamat.setText(addressPlace.trim());
                LatLng latLng = place.getLatLng();
                lat = latLng.latitude;
                lng = latLng.longitude;
            } else if (resultCode == PlaceAutocomplete.RESULT_ERROR) {
                Status status = PlaceAutocomplete.getStatus(this, data);
                Log.e("TAG", "Error: Status = " + status.toString());
            }
        }
    }
    private void onSubmit(){
        if(!frmNamaTempat.getText().toString().trim().equals("") && !frmNamaPengurus.getText().toString().trim().equals("")
                && !frmAlamat.getText().toString().trim().equals("") && !frmJmlJamaah.getText().toString().trim().equals("")){

            String txtNamaTempat = frmNamaTempat.getText().toString().trim();
            String txtNamaPengurus = frmNamaPengurus.getText().toString().trim();
            String txtAlamat = frmAlamat.getText().toString().trim();
            String txtJmlJamaah = frmJmlJamaah.getText().toString().trim();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            double totalNilai = 0;
            String status;
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
            boolean hasValue = true;
            String[] txtRB = new String[37];
            String[] nilaiRB = new String[37];
            HashMap<String, String> nilaiTTU = new HashMap<>();
            for(int i = 0; i<rg.length; i++)
            {
                if(rg[i].getCheckedRadioButtonId() != -1){
                    txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString();
                    if(txtRB[i].equals("YA")){
                        nilaiRB[i] = String.valueOf(nTPM[i] * bTPM[i]);
                    }else {
                        nilaiRB[i] = "0";
                    }
                    nilaiTTU.put("nilai_" + i , nilaiRB[i]);
                    totalNilai = totalNilai + Double.valueOf(nilaiRB[i]);
                }else{
                    hasValue = false;
                }
            }

            if(hasValue){
                if(totalNilai >= 700){
                    status = "Laik Hygiene Sanitasi";
                }else {
                    status = "Tidak Laik Hygiene Sanitasi";
                }
                submitTTU(txtNamaTempat, txtNamaPengurus, txtJmlJamaah, idPetugas, waktu, txtAlamat, koordinat, totalNilai, status, nilaiTTU);
            }else{
                Toast.makeText(this, "Error harap check semua opsi!", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this, "Error harap diisi!", Toast.LENGTH_SHORT).show();
        }


    }

    private void submitTTU(String txtNamaTempat, String txtNamaPengurus, String txtJmlJamaah,
                           String idPetugas, String waktu, String txtAlamat, String koordinat,
                           double totalNilai, String status, HashMap<String, String> nilaiTTU){
        TTU setTTU = new TTU(txtNamaTempat, txtNamaPengurus, txtJmlJamaah, idPetugas, waktu, txtAlamat, koordinat, totalNilai, status);
        String pushID = mDatabase.child("ttu").child("tempatIbadah").push().getKey();
        mDatabase.child("ttu").child("tempatIbadah").child(pushID).child("data").setValue(setTTU);
        mDatabase.child("ttu").child("tempatIbadah").child(pushID).child("nilai").setValue(nilaiTTU);
        Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                ttu_ibadah.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(SessionString.EXTRA_DATABASE_SESSION, MODE_PRIVATE);
        editor = pref.edit();
    }

    private void setAuthInstance() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
    }

    private void setDatabaseInstance() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }
}
