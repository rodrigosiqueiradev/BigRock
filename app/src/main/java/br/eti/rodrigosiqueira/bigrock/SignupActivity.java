package br.eti.rodrigosiqueira.bigrock;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.eti.rodrigosiqueira.bigrock.async.SignupAsync;
import br.eti.rodrigosiqueira.bigrock.model.User;

public class SignupActivity extends AppCompatActivity {

    private TextView tvLogin;
    private Button btnSignup;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

        tvLogin = (TextView) findViewById(R.id.link_login);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        edtName = (EditText) findViewById(R.id.edt_name);
        edtEmail = (EditText) findViewById(R.id.edt_email);
        edtPassword = (EditText) findViewById(R.id.edt_password);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void signup() {

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null || cm.getActiveNetworkInfo() == null || !cm.getActiveNetworkInfo().isConnected()) {
            Toast.makeText(getBaseContext(), "Sem acesso a internet!", Toast.LENGTH_LONG).show();
            return;
        }

        User user = new User();
        user.setEmail(edtEmail.getText().toString());
        user.setName(edtName.getText().toString());
        user.setPassword(edtPassword.getText().toString());

        if (!validate(user)) {
            Toast.makeText(getBaseContext(), "Não foi possível realizar o cadastro!", Toast.LENGTH_LONG).show();
            return;
        }

        SignupAsync signupAsync = new SignupAsync(this);
        signupAsync.execute(user);

    }

    public boolean validate(User user) {

        boolean valid = true;

        if (user.getName().isEmpty() || user.getName().length() < 3) {
            edtName.setError("O nome deve conter no mínimo 3 caracteres!");
            valid = false;
        } else {
            edtName.setError(null);
        }

        if (user.getEmail().isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user.getEmail()).matches()) {
            edtEmail.setError("Informe um endereço email válido!");
            valid = false;
        } else {
            edtEmail.setError(null);
        }

        if (user.getPassword().isEmpty() || user.getPassword().length() < 4 || user.getPassword().length() > 20) {
            edtPassword.setError("A senha deve conter entre 4 e 20 caracteres!");
            valid = false;
        } else {
            edtPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

}
