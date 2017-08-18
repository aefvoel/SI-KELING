package tiregdev.sipepikeling;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andexert.library.RippleView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        final RippleView rippleViewRS = (RippleView) findViewById(R.id.rs);
        rippleViewRS.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, rumah_sehat.class);
                startActivity(w);
            }
        });

        final RippleView rippleViewDAM = (RippleView) findViewById(R.id.dam);
        rippleViewDAM.setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
            @Override
            public void onComplete(RippleView rippleView) {
                Intent w = new Intent(MainActivity.this, dam.class);
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

    }
}
