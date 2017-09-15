package tiregdev.sipepikeling.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.andexert.library.RippleView;

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.jenis_tpm;
import tiregdev.sipepikeling.tpm_retoran;

public class jenis_faskes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_faskes);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RippleView rippleViewF1 = (RippleView) findViewById(R.id.puskesmas);
        rippleViewF1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_faskes.this, faskes_puskes.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewF2 = (RippleView) findViewById(R.id.rmhskt);
        rippleViewF2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_faskes.this, faskes_rumah_sakit.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewF3 = (RippleView) findViewById(R.id.klinik);
        rippleViewF3.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
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
                jenis_faskes.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
