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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import tiregdev.sipepikeling.activity.LoginActivity;
import tiregdev.sipepikeling.activity.MainActivity;
import tiregdev.sipepikeling.model.SAB;
import tiregdev.sipepikeling.utils.SessionString;

public class sab_pompa extends AppCompatActivity {

    EditText frmNamaPuskesmas, frmKodeSarana, frmKodeSampel, frmGolongan, frmPemilikSarana;
    RadioGroup rgSampelAir;
    RadioGroup[] rg = new RadioGroup[12];
    RadioButton[] rb;
    Button btnSend;
    private double lat;
    private double lng;
    SimpleDateFormat sdf;
    String txtAlamat;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sab_pompa);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setAuthInstance();
        setDatabaseInstance();
        setSharedPreferences();
        setAlamat();
    }

    private void setInit(){
        frmNamaPuskesmas = (EditText)findViewById(R.id.namaPuskesmas);
        frmKodeSarana = (EditText)findViewById(R.id.kodeSarana);
        frmPemilikSarana = (EditText)findViewById(R.id.pemilikSarana);
        frmKodeSampel = (EditText)findViewById(R.id.noKode);
        frmGolongan = (EditText)findViewById(R.id.golongan);
        rgSampelAir = (RadioGroup)findViewById(R.id.sampelAir);
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
    private void onSubmit(){
        String txtNamaPuskesmas = frmNamaPuskesmas.getText().toString().trim();
        String txtKodeSarana = frmKodeSarana.getText().toString().trim();
        String txtPemilikSarana = frmPemilikSarana.getText().toString().trim();
        String txtKodeSampel = frmKodeSampel.getText().toString().trim();
        String txtGolongan = frmGolongan.getText().toString().trim();
        String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
        String txtSudahDiambil = ((RadioButton)findViewById(rgSampelAir.getCheckedRadioButtonId())).getText().toString();
        String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
        String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
        int totalNilai = 0;
        String status = "";
        boolean hasValue = true;
        String[] txtRB = new String[rg.length];
        String[] nilaiRB = new String[rg.length];
        HashMap<String, String> nilaiSAB = new HashMap<>();
        for(int i = 0; i<rg.length; i++)
        {
            if(rg[i].getCheckedRadioButtonId() != -1){
                txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString();
                if(txtRB[i].equals("YA")){
                    nilaiRB[i] = "1";
                }else {
                    nilaiRB[i] = "0";
                }
                nilaiSAB.put("nilai_" + i , nilaiRB[i]);
                totalNilai = totalNilai + Integer.valueOf(nilaiRB[i]);
            }else{
                hasValue = false;
            }
        }

        if(hasValue){
            if(totalNilai >= 8){
                status = "Amat Tinggi (AT)";
            }else if(totalNilai >= 6 && totalNilai < 8 ) {
                status = "Tinggi (T)";
            }else if(totalNilai >= 3 && totalNilai < 6 ) {
                status = "Sedang (S)";
            }else if(totalNilai >= 0 && totalNilai < 3 ) {
                status = "Rendah (R)";
            }
            submitSAB(txtNamaPuskesmas, txtKodeSarana, txtPemilikSarana, txtSudahDiambil, txtKodeSampel, txtGolongan, getIntent().getExtras().getString("kategori"), waktu, txtAlamat, koordinat, idPetugas, totalNilai, status, nilaiSAB, getIntent().getExtras().getString("idKK"));
        }else{
            Toast.makeText(this, "Error harap check semua opsi!", Toast.LENGTH_SHORT).show();
        }
    }

    private void submitSAB(String txtNamaPuskesmas, String txtKodeSarana, String txtPemilikSarana, String txtSudahDiambil,
                           String txtKodeSampel, String txtGolongan, String kategori, String waktu, String alamat, String koordinat,
                           String idPetugas, int totalNilai, String status, HashMap<String, String> nilaiSAB, String idKK){
        SAB setSAB = new SAB(txtNamaPuskesmas, txtKodeSarana, txtPemilikSarana, txtSudahDiambil, txtKodeSampel, txtGolongan, kategori, waktu, alamat, koordinat, idPetugas, totalNilai, status, idKK);
        mDatabase.child("sab").child(getIntent().getExtras().getString("idSAB")).child("data").setValue(setSAB);
        mDatabase.child("sab").child(getIntent().getExtras().getString("idSAB")).child("nilai").setValue(nilaiSAB);
        Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getBaseContext(), MainActivity.class));
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                sab_pompa.this.finish();
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
