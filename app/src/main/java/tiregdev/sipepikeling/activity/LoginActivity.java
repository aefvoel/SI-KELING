package tiregdev.sipepikeling.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import tiregdev.sipepikeling.R;
import tiregdev.sipepikeling.utils.SessionString;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText frmEmail, frmPass;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference mDatabase;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setInit();
        setAuthInstance();
        setDatabaseInstance();
        setSharedPreferences();
    }

    private void setInit() {
        frmEmail = (EditText) findViewById(R.id.email);
        frmPass = (EditText) findViewById(R.id.pass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogin();
            }
        });
    }

    private void onLogin() {
        String txtEmail = frmEmail.getText().toString().trim();
        String txtPass = frmPass.getText().toString().trim();
        if (txtEmail.equals("") || txtPass.equals("")) {
            Toast.makeText(this, "Data belum diisi!", Toast.LENGTH_SHORT).show();
        } else if (isIncorrectEmail(txtEmail)) {
            Toast.makeText(this, "Email tidak valid!", Toast.LENGTH_SHORT).show();
        } else {
            loginPetugas(txtEmail, txtPass);
        }
    }

    private void loginPetugas(String txtEmail, String txtPass) {
        mFirebaseAuth.signInWithEmailAndPassword(txtEmail, txtPass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            editor.putBoolean(SessionString.EXTRA_LOGIN_SESSION, true);
                            editor.putString(SessionString.EXTRA_STATUS_USER, "Petugas");
                            editor.putString(SessionString.EXTRA_KEY_ID_USER, task.getResult().getUser().getUid());
                            editor.commit();
                            goToMainActivity();
                        } else {
                            Toast.makeText(getBaseContext(), "Email tidak valid!", Toast.LENGTH_SHORT).show();
                            Log.e("ERROR", task.getException().getMessage());
                        }
                    }
                }
        );
    }

    private void setSharedPreferences() {
        pref = getApplicationContext().getSharedPreferences(SessionString.EXTRA_DATABASE_SESSION, MODE_PRIVATE);
        editor = pref.edit();
        checkLoginSession();
    }

    private void checkLoginSession() {
        if(pref.getBoolean(SessionString.EXTRA_LOGIN_SESSION, false)){
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    private void setAuthInstance() {
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            }
        };
    }

    private boolean isIncorrectEmail(String userEmail) {
        return !android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches();
    }

    private void setDatabaseInstance() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);
    }
}
