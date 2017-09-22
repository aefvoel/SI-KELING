package tiregdev.sipepikeling.activity;

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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.jenis_sab;
import tiregdev.sipepikeling.model.KK;
import tiregdev.sipepikeling.model.RS;
import tiregdev.sipepikeling.model.SAB;
import tiregdev.sipepikeling.sab_pompa;
import tiregdev.sipepikeling.sab_sumur_gali;
import tiregdev.sipepikeling.sab_sumur_gali_plus;
import tiregdev.sipepikeling.utils.SessionString;

public class RSActivity extends AppCompatActivity {

    EditText frmNamaKK, frmAlamat, frmJmlAnggota, frmNoRumah, frmNIK, frmNoKIS;
    Spinner spinnerRT, spinnerRW, spinnerSAB;
    Button btnSend;
    SimpleDateFormat sdf;
    RadioGroup[] rg = new RadioGroup[17];
    RadioGroup rg22, rg21, rg20, rg19, rgStatus;
    private double lat;
    private double lng;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private static final int REQUEST_CODE_AUTOCOMPLETE = 1;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rumah_sehat);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setInit();
        setAuthInstance();
        setDatabaseInstance();
        setSharedPreferences();
    }

    private void setInit(){
        frmNamaKK = (EditText)findViewById(R.id.namaKK);
        frmAlamat = (EditText)findViewById(R.id.alamatKK);
        frmNIK = (EditText)findViewById(R.id.nik);
        frmJmlAnggota = (EditText)findViewById(R.id.jmlAnggota);
        frmNoRumah = (EditText)findViewById(R.id.noRumah);
//        frmNoKIS = (EditText)findViewById(R.id.noKIS);
        spinnerRT = (Spinner)findViewById(R.id.spinner_rt);
        spinnerRW = (Spinner)findViewById(R.id.spinner_rw);
        spinnerSAB = (Spinner)findViewById(R.id.spinner_sab);
        rg22 = (RadioGroup)findViewById(R.id.rg22);
        rg21 = (RadioGroup)findViewById(R.id.rg21);
        rg20 = (RadioGroup)findViewById(R.id.rg20);
        rg19 = (RadioGroup)findViewById(R.id.rg19);
        rgStatus = (RadioGroup)findViewById(R.id.statusRumah);
        sdf = new  SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
        for(int i = 1; i<=17; i++)
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
        if(!frmJmlAnggota.getText().toString().trim().equals("") && !frmNoRumah.getText().toString().trim().equals("")
                && !frmAlamat.getText().toString().trim().equals("")
                && !frmNIK.getText().toString().trim().equals("") && rg19.getCheckedRadioButtonId() != -1
                && rgStatus.getCheckedRadioButtonId() != -1 && rg20.getCheckedRadioButtonId() != -1 && rg21.getCheckedRadioButtonId() != -1
                && rg22.getCheckedRadioButtonId() != -1 ){

            String txtNoRumah = frmNoRumah.getText().toString().trim();
            String txtRT = spinnerRT.getSelectedItem().toString().trim();
            String txtRW = spinnerRW.getSelectedItem().toString().trim();
            String idPetugas = pref.getString(SessionString.EXTRA_KEY_ID_USER, null);
            String waktu = sdf.format(Calendar.getInstance().getTime().getTime());
            String koordinat = String.valueOf(lat) + ", " + String.valueOf(lng);
            String alamat = frmAlamat.getText().toString().trim();
            String namaKK = frmNamaKK.getText().toString().trim();
            String statusRumah = ((RadioButton)findViewById(rgStatus.getCheckedRadioButtonId())).getText().toString();
            String jamban = ((RadioButton)findViewById(rg21.getCheckedRadioButtonId())).getText().toString();
            String spal = ((RadioButton)findViewById(rg22.getCheckedRadioButtonId())).getText().toString();
            String pjb = ((RadioButton)findViewById(rg20.getCheckedRadioButtonId())).getText().toString();
            String sampah = ((RadioButton)findViewById(rg19.getCheckedRadioButtonId())).getText().toString();

            String idSAB = mDatabase.child("sab").push().getKey();


            int totalNilai = 0;
            int jmlAnggota = 0;
            boolean hasValue = true;
            String status;
            String[] txtRB = new String[17];
            String[] nilaiRB = new String[17];
            HashMap<String, String> nilaiRS = new HashMap<>();

            for(int i = 0; i<=16; i++)
            {
                if(rg[i].getCheckedRadioButtonId() != -1 && rg19.getCheckedRadioButtonId() != -1
                        && rg20.getCheckedRadioButtonId() != -1 && rg21.getCheckedRadioButtonId() != -1){
                    if(i < 8){
                        txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString().trim().substring(0, 1);
                        nilaiRB[i] = String.valueOf(setNilai(txtRB[i]) * 31);
                    }else if(i >= 8 && i < 12){
                        txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString().trim().substring(0, 1);
                        nilaiRB[i] = String.valueOf(setNilai(txtRB[i]) * 25);
                    }else if(i >= 12 && i <= 16){
                        txtRB[i] = ((RadioButton)findViewById(rg[i].getCheckedRadioButtonId())).getText().toString().trim().substring(0, 1);
                        nilaiRB[i] = String.valueOf(setNilai(txtRB[i]) * 44);
                    }

                    nilaiRS.put("nilai_" + i , nilaiRB[i]);
                    totalNilai = totalNilai + Integer.parseInt(nilaiRB[i]);
                }else{
                    hasValue = false;
                }
            }
            if(hasValue){
                String pushRS = mDatabase.child("rs").push().getKey();
                if(frmNamaKK.getText().toString().contains(",") && frmJmlAnggota.getText().toString().contains(",") && frmNIK.getText().toString().contains(",")){
                    String[] splitNamaKK = frmNamaKK.getText().toString().replace(" ", "").trim().trim().split(",");
                    String[] splitJmlAnggota = frmJmlAnggota.getText().toString().replace(" ", "").trim().split(",");
                    String[] splitNik = frmNIK.getText().toString().replace(" ", "").trim().split(",");
//                    String[] splitNoKis = frmNoKIS.getText().toString().replace(" ", "").trim().split(",");
                    for(int i = 0;i<splitNamaKK.length;i++){
                        KK setKK = new KK(splitNamaKK[i], splitJmlAnggota[i], idPetugas, pushRS, splitNik[i]);
                        String pushKK = mDatabase.child("kk").push().getKey();
                        mDatabase.child("kk").child(pushKK).setValue(setKK);
                        jmlAnggota = jmlAnggota + Integer.parseInt(splitJmlAnggota[i]);
                    }
                }else{
                    String txtNamaKK = frmNamaKK.getText().toString().trim();
                    String txtJmlAnggota = frmJmlAnggota.getText().toString().trim();
                    String txtNik = frmNIK.getText().toString().trim();
//                    String txtNoKis = frmNoKIS.getText().toString().trim();
                    KK setKK = new KK(txtNamaKK, txtJmlAnggota, idPetugas, pushRS, txtNik);
                    String pushKK = mDatabase.child("kk").push().getKey();
                    mDatabase.child("kk").child(pushKK).setValue(setKK);
                    jmlAnggota = Integer.parseInt(frmJmlAnggota.getText().toString().trim());
                }

                if(totalNilai <= 1068){
                    status = "Rumah Tidak Sehat";
                }else {
                    status = "Rumah Sehat";
                }

                submitRS(koordinat, waktu, idPetugas, totalNilai, status, jamban, spal, pjb, sampah, idSAB, txtRT, txtRW, alamat, txtNoRumah, jmlAnggota, nilaiRS, pushRS, namaKK, statusRumah);


            }else{
                Toast.makeText(this, "Error harap check semua opsi!", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Error harap isi semua opsi!", Toast.LENGTH_SHORT).show();
        }


    }

    private void submitRS(String koordinat, String waktu, String idPetugas,
                          int totalNilai, String status, String jamban, String spal, String pjb, String sampah,
                          String idSAB, String txtRT, String txtRW, String alamat, String txtNoRumah, int jmlAnggota,
                          HashMap<String, String> nilaiRS, String pushRS, String namaKK, String statusRumah){
        RS setRS = new RS(koordinat, waktu, idPetugas, totalNilai, status, jamban, spal, pjb, sampah, idSAB, txtRT, txtRW, alamat, txtNoRumah, jmlAnggota, namaKK, statusRumah);


        mDatabase.child("rs").child(pushRS).child("data").setValue(setRS);
        mDatabase.child("rs").child(pushRS).child("nilai").setValue(nilaiRS);

        if(spinnerSAB.getSelectedItemPosition() == 0){
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), sab_pompa.class);
            intent.putExtra("idSAB", idSAB);
            intent.putExtra("idRS", pushRS);
            intent.putExtra("koordinat", koordinat);
            intent.putExtra("alamat", frmAlamat.getText().toString().trim());
            intent.putExtra("kategori", "A");
            startActivity(intent);
            finish();
        }else if(spinnerSAB.getSelectedItemPosition() == 1){
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), sab_sumur_gali.class);
            intent.putExtra("idSAB", idSAB);
            intent.putExtra("idRS", pushRS);
            intent.putExtra("koordinat", koordinat);
            intent.putExtra("alamat", frmAlamat.getText().toString().trim());
            intent.putExtra("kategori", "B");
            startActivity(intent);
            finish();
        }else if(spinnerSAB.getSelectedItemPosition() == 2){
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), sab_sumur_gali_plus.class);
            intent.putExtra("idSAB", idSAB);
            intent.putExtra("idRS", pushRS);
            intent.putExtra("koordinat", koordinat);
            intent.putExtra("alamat", frmAlamat.getText().toString().trim());
            intent.putExtra("kategori", "C");
            startActivity(intent);
            finish();
        }else if(spinnerSAB.getSelectedItemPosition() == 3){
            String kategori = "D";
            SAB setSAB = new SAB(kategori, waktu, alamat, koordinat, idPetugas, pushRS);
            mDatabase.child("sab").child(idSAB).child("data").setValue(setSAB);
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            finish();
        }else if(spinnerSAB.getSelectedItemPosition() == 4) {
            String kategori = "E";
            SAB setSAB = new SAB(kategori, waktu, alamat, koordinat, idPetugas, pushRS);
            mDatabase.child("sab").child(idSAB).child("data").setValue(setSAB);
            Toast.makeText(this, "Data berhasil dikirim!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private int setNilai(String abc){
        int nilaiBobot = 0;
        switch (abc) {
            case "a":
                nilaiBobot = 0;
                break;
            case "b":
                nilaiBobot = 1;
                break;
            case "c":
                nilaiBobot = 2;
                break;
            case "d":
                nilaiBobot = 3;
                break;
            case "e":
                nilaiBobot = 4;
                break;
        }
        return nilaiBobot;
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
                RSActivity.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
