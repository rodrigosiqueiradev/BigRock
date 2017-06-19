package br.eti.rodrigosiqueira.bigrock;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.eti.rodrigosiqueira.bigrock.async.LoginAsync;
import br.eti.rodrigosiqueira.bigrock.model.User;

public class LoginActivity extends AppCompatActivity {

    private Button btnLogin;
    private TextView tvSignup;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                                            Manifest.permission.INTERNET,
                                                            Manifest.permission.ACCESS_NETWORK_STATE,
                                                            Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

        btnLogin = (Button) findViewById(R.id.btn_login);
        tvSignup = (TextView) findViewById(R.id.link_signup);
        edtEmail = (EditText) findViewById(R.id.edt_login_email);
        edtPassword = (EditText) findViewById(R.id.edt_login_password);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void login() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null || cm.getActiveNetworkInfo() == null || !cm.getActiveNetworkInfo().isConnected()) {
            Toast.makeText(getBaseContext(), getString(R.string.noInternetAccess), Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User();
        user.setEmail(edtEmail.getText().toString());
        user.setPassword(edtPassword.getText().toString());

        if (!validate(user)) {
            Toast.makeText(getBaseContext(), getString(R.string.validadeLogin), Toast.LENGTH_LONG).show();
            return;
        }

        LoginAsync loginAsync = new LoginAsync(this);
        loginAsync.execute(user);

    }

    public boolean validate(User user) {

        boolean valid = true;

        if (user.getEmail().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            edtEmail.setError(getString(R.string.validadeEmail));
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (user.getPassword().isEmpty() || user.getPassword().length() < 4 || user.getPassword().length() > 20) {
            edtPassword.setError(getString(R.string.validadePassword));
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}