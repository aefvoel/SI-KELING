package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.andexert.library.RippleView;

public class jenis_sab extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jenis_sab);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final RippleView rippleViewJ1 = (RippleView) findViewById(R.id.jennisSAB1);
        rippleViewJ1.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_pompa.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ2 = (RippleView) findViewById(R.id.jennisSAB2);
        rippleViewJ2.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_sumur_gali.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewJ3 = (RippleView) findViewById(R.id.jennisSAB3);
        rippleViewJ3.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_sab.this, sab_sumur_gali_plus.class);
                startActivity(w);
            }
        });
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
}
