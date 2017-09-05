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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
        setAlamat();
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
    }

    private void setAlamat() {
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getBaseContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getBaseContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return  ;
        }
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), true);
        Location locations = locationManager.getLastKnownLocation(provider);
        List<String> providerList = locationManager.getAllProviders();
        if(null!=locations && null!=providerList && providerList.size()>0){
            lng = locations.getLongitude();
            lat = locations.getLatitude();
            Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> listAddresses = geocoder.getFromLocation(lat, lng, 1);
                if(null!=listAddresses&&listAddresses.size()>0){
                    //txtAlamat = listAddresses.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void onSubmit(){
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
