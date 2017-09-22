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
import tiregdev.sipepikeling.model.PASAR;
import tiregdev.sipepikeling.utils.SessionString;

public class ttu_pasar extends AppCompatActivity {

    EditText frmNamaPasar, frmNamaPengelola, frmAlamat, frmJmlKios, frmJmlPedagang, frmJmlAsosiasi;
    Button btnSend;
    CheckBox[] cb = new CheckBox[172];
    double[] bobot = new double[] {5, 5, 5, 5, 5,
            0.5,
            4, 4, 4, 4, 4,
            0.5, 0.5, 0.5,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3,
            1, 1, 1, 1, 1, 1, 1, 1, 1,
            0.5, 0.5, 0.5, 0.5, 0.5,
            0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
            0.5, 0.5, 0.5, 0.5, 0.5, 0.5,
            0.5, 0.5, 0.5, 0.5,
            1,
            0.5,
            0.5,
            4, 4, 4, 4,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
            4, 4, 4, 4, 4,
            4, 4, 4,
            3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4, 4, 4,
            3, 3,
            15, 15, 15, 15,
            10, 10,
            5,
            3, 3, 3, 3, 3, 3,
            2, 2,
            2, 2, 2,
            5, 5, 5, 5, 5, 5, 5, 5,
            3};
    double[] nilai = new double[] {20, 20, 20, 20, 20,
            100,
            25, 5, 15, 0, 15,
            40, 40, 20,
            4, 2, 2, 2, 4, 15, 15, 8, 6, 8, 5, 5, 4, 3, 3, 10,
            10, 5, 5, 20, 0, 5, 5, 0, 14, 20,
            4, 3, 3, 3, 3, 6, 14, 6, 4, 4, 6, 10, 5, 5, 0, 2, 2, 10,
            15, 10, 10, 0, 10, 15, 10, 10, 10,
            20, 10, 10, 40, 20,
            15, 15, 10, 20, 20, 0,
            15, 15, 15, 10, 15, 30,
            40, 20, 20, 20,
            100,
            100,
            0,
            40, 30, 20, 10,
            5, 5, 10, 10, 0, 10, 10, 10, 10, 10, 10,
            0, 5, 5, 4, 3, 3, 8, 7, 4, 4, 4, 3, 10, 10, 10,
            30, 30, 10, 10, 10,
            40, 0, 40,
            15, 20, 20, 20, 20,
            20, 10, 10, 5, 10, 10, 10, 10, 10,
            50, 50,
            5, 20, 0, 40,
            50, 0,
            50,
            20, 10, 10, 30, 20, 10,
            50, 50,
            40, 40, 20,
            20, 10, 10, 10, 10, 10, 10, 20,
            50};


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
        setContentView(R.layout.activity_ttu_pasar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setSharedPreferences();
        setAuthInstance();
        setDatabaseInstance();
    }

    private void setInit(){
        frmNamaPasar = (EditText)findViewById(R.id.namaPasar);
        frmNamaPengelola = (EditText)findViewById(R.id.namaPJ);
        frmAlamat = (EditText)findViewById(R.id.alamatPasar);
        frmJmlKios = (EditText)findViewById(R.id.jumlahKios);
        frmJmlPedagang = (EditText)findViewById(R.id.jumlahPedagang);
        frmJmlAsosiasi = (EditText)findViewById(R.id.jumlahAsosiasi);
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
        if(!frmNamaPasar.getText().toString().trim().equals("") && !frmNamaPengelola.getText().toString().trim().equals("")
                && !frmAlamat.getText().toString().trim().equals("") && !frmJmlKios.getText().toString().trim().equals("")
                && !frmJmlPedagang.getText().toString().trim().equals("") && !frmJmlAsosiasi.getText().toString().trim().equals("")){

            String namaPasar = frmNamaPasar.getText().toString().trim();
            String namaPengelola = frmNamaPengelola.getText().toString().trim();
            String alamat = frmAlamat.getText().toString().trim();
            String jumlahKios = frmJmlKios.getText().toString().trim();
            String jumlahPedagang = frmJmlPedagang.getText().toString().trim();
            String jumlahAsosiasi = frmJmlAsosiasi.getText().toString().trim();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            double totalNilai = 0;
            String status = "";
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
            String[] nilaiCB = new String[cb.length];
            HashMap<String, String> nilaiTPM = new HashMap<>();
            for(int i=0;i<cb.length;i++){
                if(cb[i].isChecked()){
                    nilaiCB[i] = String.valueOf(nilai[i] * bobot[i]);
                }else{
                    nilaiCB[i] = "0";
                }
                nilaiTPM.put("nilai_" + i , nilaiCB[i]);
                totalNilai = totalNilai + Double.valueOf(nilaiCB[i]);
            }
            if(totalNilai < 6000){
                status = "Tidak Sehat";
            }else if(totalNilai >= 6000 && totalNilai < 7500){
                status = "Kurang Sehat";
            }else if(totalNilai >= 7500 && totalNilai <= 10000){
                status = "Sehat";
            }
            submitPASAR(namaPasar, namaPengelola, jumlahKios, jumlahPedagang, jumlahAsosiasi, idPetugas,
                    waktu, alamat, koordinat, totalNilai, status, nilaiTPM);
        }
        else{
            Toast.makeText(this, "Error semua harap diisi!", Toast.LENGTH_SHORT).show();
        }
    }
    private void submitPASAR(String namaPasar, String namaPengelola, String jumlahKios, String jumlahPedagang,
                             String jumlahAsosiasi, String idPetugas, String waktu, String alamat, String koordinat,
                             double totalNilai, String status, HashMap<String, String> nilaiTPM){

        PASAR setPasar = new PASAR(namaPasar, namaPengelola, jumlahKios, jumlahPedagang, jumlahAsosiasi, idPetugas,
                waktu, alamat, koordinat, totalNilai, status);
        String pushID = mDatabase.child("ttu").child("pasar").push().getKey();
        mDatabase.child("ttu").child("pasar").child(pushID).child("data").setValue(setPasar);
        mDatabase.child("ttu").child("pasar").child(pushID).child("nilai").setValue(nilaiTPM);
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
                ttu_pasar.this.finish();
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
