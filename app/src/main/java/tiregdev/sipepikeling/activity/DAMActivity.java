package tiregdev.sipepikeling.activity;

import android.Manifest;
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
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.model.DAM;
import tiregdev.sipepikeling.utils.SessionString;

public class DAMActivity extends AppCompatActivity {

    EditText frmNamaD, frmNamaP, frmAlamat;
    Button btnSend;
    RadioGroup[] rg = new RadioGroup[31];
    int[] bobotDAM = new int[] {4, 5, 3, 4, 2, 2, 3, 4, 4, 4, 4, 4, 5, 4, 3, 4, 5, 5, 3, 4, 3, 3, 3, 4, 3, 2, 1, 1, 1, 1, 2};
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
        setContentView(R.layout.activity_dam);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setAuthInstance();
        setDatabaseInstance();
        setSharedPreferences();
    }

    private void setInit(){
        frmAlamat = (EditText)findViewById(R.id.frmAlamat);
        frmNamaD = (EditText)findViewById(R.id.frmNamaD);
        frmNamaP = (EditText)findViewById(R.id.frmNamaP);
        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        for(int i = 1; i<=31; i++)
        {
            int rID = getResources().getIdentifier("rg" + i, "id", this.getBaseContext().getPackageName());
            rg[i-1] = (RadioGroup)findViewById(rID);
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

    private void setSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(SessionString.EXTRA_DATABASE_SESSION, MODE_PRIVATE);
        editor = pref.edit();
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
        if(!frmNamaD.getText().toString().trim().equals("") && !frmNamaP.getText().toString().trim().equals("")
                && !frmAlamat.getText().toString().trim().equals("")){
            String namaDepot = frmNamaD.getText().toString().trim();
            String namaPemilik = frmNamaP.getText().toString().trim();
            String alamat = frmAlamat.getText().toString().trim();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
            int totalNilai = 0;
            boolean hasValue = true;
            String status;
            String[] txtRB = new String[31];
            String[] nilaiRB = new String[31];
            HashMap<String, String> nilaiDAM = new HashMap<>();
            for(int i = 0; i<=30; i++)
            {
                if(rg[i].getCheckedRadioButtonId() != -1){
                    txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString();
                    if(txtRB[i].equals("Tidak Menyimpang")){
                        nilaiRB[i] = String.valueOf(bobotDAM[i]);
                    }else {
                        nilaiRB[i] = "0";
                    }
                    nilaiDAM.put("nilai_" + i , nilaiRB[i]);
                    totalNilai = totalNilai + Integer.valueOf(nilaiRB[i]);
                }else{
                    hasValue = false;
                }

            }
            if(hasValue){
                if(totalNilai >= 70){
                    status = "Memenuhi Persyaratan";
                }else {
                    status = "Belum Memenuhi Persyaratan";
                }
                submitDAM(namaDepot, namaPemilik, alamat, idPetugas, koordinat, waktu, totalNilai, status, nilaiDAM);
            }else{
                Toast.makeText(this, "Error harap check semua opsi!", Toast.LENGTH_SHORT).show();

            }
        }
        else{
            Toast.makeText(this, "Error harap isi semua opsi!", Toast.LENGTH_SHORT).show();
        }


    }

    private void submitDAM(String namaPemilik, String namaDepot, String alamat, String idPetugas, String koordinat,
                           String waktu, int totalNilai, String status, HashMap<String, String> nilaiDAM){
        DAM setDAM = new DAM(namaPemilik, namaDepot, alamat, idPetugas, koordinat, waktu, totalNilai, status);
        String pushDAM = mDatabase.child("tpm").child("dam").push().getKey();
        mDatabase.child("tpm").child("dam").child(pushDAM).child("data").setValue(setDAM);
        mDatabase.child("tpm").child("dam").child(pushDAM).child("nilai").setValue(nilaiDAM);
        Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
        finish();
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


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                DAMActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
