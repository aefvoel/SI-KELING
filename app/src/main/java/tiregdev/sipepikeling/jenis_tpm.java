package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.andexert.library.RippleView;

import tiregdev.sipepikeling.activity.DAMActivity;
import tiregdev.sipepikeling.activity.MainActivity;
import tiregdev.sipepikeling.activity.tpm_jasaboga;

public class jenis_tpm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_tpm);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final RippleView rippleViewJ9 = (RippleView) findViewById(R.id.jenisTTU9);
        rippleViewJ9.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_tpm.this, tpm_jasaboga.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ7 = (RippleView) findViewById(R.id.jenisTTU7);
        rippleViewJ7.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_tpm.this, tpm_retoran.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewDAM = (RippleView) findViewById(R.id.dam);
        rippleViewDAM.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_tpm.this, DAMActivity.class);
                startActivity(w);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here
                jenis_tpm.this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
