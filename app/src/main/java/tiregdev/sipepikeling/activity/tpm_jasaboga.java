package tiregdev.sipepikeling.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.model.JB;
import tiregdev.sipepikeling.utils.SessionString;

public class tpm_jasaboga extends AppCompatActivity {

    EditText frmNamaTempat, frmNamaPengusaha, frmNoTelp, frmAlamat;
    Button btnSend;
    CheckBox[] cb = new CheckBox[44];

    double[] bobot = new double[]{5, 1, 5, 4, 1,
    2, 4, 2, 2, 1,
    5, 5, 1, 3, 1,
    2, 1, 1, 2, 1,
    2, 4, 1, 2, 1,
    1, 3, 4, 1, 5,
    4, 2, 2, 1, 4,
    2, 1, 1, 1, 1,
    5, 1, 1, 1};

    private double lat;
    private double lng;
    SimpleDateFormat sdf;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tpm_jasaboga);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setSharedPreferences();
        setAuthInstance();
        setDatabaseInstance();
    }

    private void setInit(){
        frmNamaTempat = (EditText)findViewById(R.id.namaTempat);
        frmNamaPengusaha = (EditText)findViewById(R.id.namaPengusaha);
        frmAlamat = (EditText)findViewById(R.id.namaAlamat);
        frmNoTelp = (EditText)findViewById(R.id.tlp);
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
        for(int i=0;i<cb.length;i++){
            int rID = getResources().getIdentifier("cb" + (i + 1), "id", this.getBaseContext().getPackageName());
            cb[i] = (CheckBox) findViewById(rID);
        }
        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
    }
    private void onSubmit(){
        if(!frmNamaTempat.getText().toString().trim().equals("") && !frmNamaPengusaha.getText().toString().trim().equals("")
                && !frmAlamat.getText().toString().trim().equals("") && !frmNoTelp.getText().toString().trim().equals("")){
            String txtNamaTempat = frmNamaTempat.getText().toString().trim();
            String txtNamaPengusaha = frmNamaPengusaha.getText().toString().trim();
            String txtAlamat = frmAlamat.getText().toString().trim();
            String txtNoTelp = frmNoTelp.getText().toString().trim();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            double totalNilai = 0;
            String status;
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
            String[] nilaiCB = new String[cb.length];
            HashMap<String, String> nilaiTPM = new HashMap<>();
            for(int i=0;i<cb.length;i++){
                if(cb[i].isChecked()){
                    nilaiCB[i] = String.valueOf(bobot[i]);
                }else{
                    nilaiCB[i] = "0";
                }
                nilaiTPM.put("nilai_" + i , nilaiCB[i]);
                totalNilai = totalNilai + Double.valueOf(nilaiCB[i]);
            }
            double presentase = (totalNilai/100) * 100;
            status = "Presentase Penyimpangan Sebesar " + presentase + "%";
            submitJB(txtNamaTempat, txtNoTelp, txtNamaPengusaha, idPetugas, waktu, txtAlamat, koordinat, totalNilai, status, nilaiTPM);
        }
        else{
            Toast.makeText(this, "Error semua harap diisi!", Toast.LENGTH_SHORT).show();
        }
    }
    private void submitJB(String namaTempat, String noTelp, String namaPengusaha, String idPetugas,
                          String waktu, String alamat, String koordinat, double totalNilai, String status,
                          HashMap<String, String> nilaiTPM){

        JB setJB = new JB(namaTempat, noTelp, namaPengusaha, idPetugas, waktu, alamat, koordinat, totalNilai, status);
        String pushID = mDatabase.child("tpm").child("jasaboga").push().getKey();
        mDatabase.child("tpm").child("jasaboga").child(pushID).child("data").setValue(setJB);
        mDatabase.child("tpm").child("jasaboga").child(pushID).child("nilai").setValue(nilaiTPM);
        Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
        finish();
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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                tpm_jasaboga.this.finish();
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
