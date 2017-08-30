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
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import tiregdev.sipepikeling.activity.MainActivity;
import tiregdev.sipepikeling.model.SAB;
import tiregdev.sipepikeling.utils.SessionString;

public class jenis_sab extends AppCompatActivity {

    private double lat;
    private double lng;
    SimpleDateFormat sdf;
    String txtAlamat, txtKategori;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_sab);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setAlamat();
        setSharedPreferences();
        setAuthInstance();
        setDatabaseInstance();

    }

    private void setInit(){
        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        final RippleView rippleViewJ1 = (RippleView) findViewById(R.id.jennisSAB1);
        rippleViewJ1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_pompa.class);
                w.putExtra("idKK", getIntent().getExtras().getString("idKK"));
                w.putExtra("idSAB", getIntent().getExtras().getString("idSAB"));
                w.putExtra("kategori", "A");
                startActivity(w);
            }
        });

        final RippleView rippleViewJ2 = (RippleView) findViewById(R.id.jennisSAB2);
        rippleViewJ2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_sumur_gali.class);
                w.putExtra("idKK", getIntent().getExtras().getString("idKK"));
                w.putExtra("idSAB", getIntent().getExtras().getString("idSAB"));
                w.putExtra("kategori", "B");
                startActivity(w);
            }
        });

        final RippleView rippleViewJ3 = (RippleView) findViewById(R.id.jennisSAB3);
        rippleViewJ3.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_sumur_gali_plus.class);
                w.putExtra("idKK", getIntent().getExtras().getString("idKK"));
                w.putExtra("idSAB", getIntent().getExtras().getString("idSAB"));
                w.putExtra("kategori", "C");
                startActivity(w);
            }
        });

        final RippleView rippleViewJ4 = (RippleView) findViewById(R.id.jennisSAB4);
        rippleViewJ4.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                txtKategori = "D";
                onSubmit();
            }
        });

        final RippleView rippleViewJ5 = (RippleView) findViewById(R.id.jennisSAB5);
        rippleViewJ5.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                txtKategori = "E";
                onSubmit();
            }
        });
    }
    private void onSubmit(){
        String kategori = txtKategori;
        String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
        String alamat = txtAlamat;
        String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
        String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
        String idKK = getIntent().getExtras().getString("idKK");
        SAB setSAB = new SAB(kategori, waktu, alamat, koordinat, idPetugas, idKK);
        mDatabase.child("sab").child(getIntent().getExtras().getString("idSAB")).child("data").setValue(setSAB);
        Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }
    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                jenis_sab.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
                    txtAlamat = listAddresses.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
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
