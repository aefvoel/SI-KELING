package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.andexert.library.RippleView;

import tiregdev.sipepikeling.activity.ttu_pasar;

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
                Toast.makeText(getBaseContext(),"Segera hadir pada versi berikutnya",Toast.LENGTH_LONG).show();
            }
        });

        final RippleView rippleViewJ4 = (RippleView) findViewById(R.id.jenisTTU6);
        rippleViewJ4.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Toast.makeText(getBaseContext(),"Segera hadir pada versi berikutnya",Toast.LENGTH_LONG).show();
            }
        });

        final RippleView rippleViewJ5 = (RippleView) findViewById(R.id.jenisTTU8);
        rippleViewJ5.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Toast.makeText(getBaseContext(),"Segera hadir pada versi berikutnya", Toast.LENGTH_LONG).show();
            }
        });

        final RippleView rippleViewJ6 = (RippleView) findViewById(R.id.jenisTTU10);
        rippleViewJ6.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Toast.makeText(getBaseContext(),"Segera hadir pada versi berikutnya",Toast.LENGTH_LONG).show();
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
