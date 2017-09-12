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
import java.util.List;
import java.util.Locale;

import tiregdev.sipepikeling.activity.MainActivity;
import tiregdev.sipepikeling.model.PKL;
import tiregdev.sipepikeling.utils.SessionString;

public class pkl extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    EditText nama, umur, alamat, kasus, namaTempat;
    RadioGroup jenisKelamin, jenis;
    Button btnSend;
    private double lat;
    private double lng;
    SimpleDateFormat sdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pkl);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setSharedPreferences();
        setDatabaseInstance();
        setAuthInstance();
    }

    private void setInit(){
        nama = (EditText)findViewById(R.id.nama);
        umur = (EditText)findViewById(R.id.umur);
        alamat = (EditText)findViewById(R.id.alamat);
        kasus = (EditText)findViewById(R.id.kasus);
        namaTempat = (EditText)findViewById(R.id.tempat);

        jenisKelamin = (RadioGroup)findViewById(R.id.jk);
        jenis = (RadioGroup)findViewById(R.id.jenis);
        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        btnSend = (Button)findViewById(R.id.send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmit();
            }
        });
        alamat.setOnClickListener(new View.OnClickListener() {
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
                alamat.setText(addressPlace.trim());
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

        if(jenisKelamin.getCheckedRadioButtonId() != -1 && jenis.getCheckedRadioButtonId() != -1
                && !nama.getText().toString().trim().equals("") && !umur.getText().toString().trim().equals("")
                && !alamat.getText().toString().trim().equals("") && !kasus.getText().toString().trim().equals("")
                && !namaTempat.getText().toString().trim().equals("")){

            String txtNama = nama.getText().toString().trim();
            String txtUmur = umur.getText().toString().trim();
            String txtAlamat = alamat.getText().toString().trim();
            String txtKasus = kasus.getText().toString().trim();
            String txtNamaTempat = namaTempat.getText().toString().trim();
            String txtJenisKelamin = ((RadioButton)findViewById(jenisKelamin.getCheckedRadioButtonId())).getText().toString();
            String txtJenis = ((RadioButton)findViewById(jenis.getCheckedRadioButtonId())).getText().toString();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);

            PKL setPKL = new PKL(txtNama, txtUmur, txtJenisKelamin, txtAlamat, txtKasus, txtJenis, txtNamaTempat, idPetugas, koordinat, waktu);
            mDatabase.child("pkl").push().setValue(setPKL);
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getBaseContext(), MainActivity.class));
            finish();

        }else{
            Toast.makeText(this, "Error harap check semua opsi!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                pkl.this.finish();
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
