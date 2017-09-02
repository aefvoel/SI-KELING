package tiregdev.sipepikeling.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andexert.library.RippleView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.jenis_tpm;
import tiregdev.sipepikeling.jenis_ttu;
import tiregdev.sipepikeling.pkl;
import tiregdev.sipepikeling.utils.SessionString;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toast.makeText(this,"Selamat datang di aplikasi SIPP KELING",Toast.LENGTH_LONG).show();
        setInit();
        setAuthInstance();
        setDatabaseInstance();
        setSharedPreferences();

    }
    private void setInit(){
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            showLocationSettings();
        }
        final RippleView rippleViewRS = (RippleView) findViewById(R.id.rs);
        rippleViewRS.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, RSActivity.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewDAM = (RippleView) findViewById(R.id.pkl);
        rippleViewDAM.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, pkl.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewSAB = (RippleView) findViewById(R.id.tpm);
        rippleViewSAB.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, jenis_tpm.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewTTU = (RippleView) findViewById(R.id.ttu);
        rippleViewTTU.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, jenis_ttu.class);
                startActivity(w);
            }
        });

        final RelativeLayout btnLogout = (RelativeLayout) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logOut();
            }
        });
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
    private void showLocationSettings() {
        Snackbar snackbar = Snackbar
                .make(findViewById(android.R.id.content), "Lokasi Error: GPS Belum Dihidupkan!",
                        Snackbar.LENGTH_LONG)
                .setAction("Hidupkan", new View.OnClickListener() {
                    @Override                    public void onClick(View v) {

                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                });
        snackbar.setActionTextColor(Color.RED);
        snackbar.setDuration(Snackbar.LENGTH_INDEFINITE);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView
                .findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);

        snackbar.show();
    }
    private void logOut(){
        editor.clear();
        editor.commit();
        mFirebaseAuth.signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }
}
