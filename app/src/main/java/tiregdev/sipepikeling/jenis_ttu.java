package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.andexert.library.RippleView;

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

        final RippleView rippleViewJ7 = (RippleView) findViewById(R.id.jenisTTU7);
        rippleViewJ7.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(jenis_ttu.this, ttu_tpm.class);
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
