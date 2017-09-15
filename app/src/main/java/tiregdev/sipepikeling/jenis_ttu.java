package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.andexert.library.RippleView;

import tiregdev.sipepikeling.activity.jenis_faskes;
import tiregdev.sipepikeling.activity.ttu_hotel;
import tiregdev.sipepikeling.activity.ttu_hotel_melati;
import tiregdev.sipepikeling.activity.ttu_kolam;
import tiregdev.sipepikeling.activity.ttu_pasar;
import tiregdev.sipepikeling.activity.ttu_sekolah;

public class jenis_ttu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_ttu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RippleView rippleViewJ1 = (RippleView) findViewById(R.id.jenisTTU1);
        rippleViewJ1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_ibadah.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ2 = (RippleView) findViewById(R.id.jenisTTU4);
        rippleViewJ2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_pasar.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ3 = (RippleView) findViewById(R.id.jenisTTU5);
        rippleViewJ3.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_sekolah.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ4 = (RippleView) findViewById(R.id.jenisTTU6);
        rippleViewJ4.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_pesantren.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ5 = (RippleView) findViewById(R.id.jenisTTU8);
        rippleViewJ5.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_kolam.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ6 = (RippleView) findViewById(R.id.jenisTTU10);
        rippleViewJ6.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, jenis_faskes.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewH1 = (RippleView) findViewById(R.id.hotel);
        rippleViewH1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_hotel.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewH2 = (RippleView) findViewById(R.id.hotelMelati);
        rippleViewH2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_hotel_melati.class);
                startActivity(w);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                jenis_ttu.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
