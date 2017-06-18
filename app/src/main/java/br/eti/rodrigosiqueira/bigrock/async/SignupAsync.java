package br.eti.rodrigosiqueira.bigrock.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.eti.rodrigosiqueira.bigrock.BigRockActivity;
import br.eti.rodrigosiqueira.bigrock.model.User;

/**
 * Created by Leonam on 18/06/2017.
 */

public class SignupAsync extends AsyncTask<User, Void, String> {

    private Context context;
    private ProgressDialog progress;

    public SignupAsync(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setMessage("Cadastrando usuário...");
        progress.show();
    }

    @Override
    protected String doInBackground(User... user) {

        String retorno = "";
        User usuario = user[0];

        try {
            URL url = new URL("http://leonamraone.com/bigrock.php?action=signup");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            JSONObject jsonParam = new JSONObject();
            jsonParam.put("name", usuario.getName());
            jsonParam.put("email", usuario.getEmail());
            jsonParam.put("password", usuario.getPassword());

            PrintStream output = new PrintStream(conn.getOutputStream());
            output.println(jsonParam.toString());

            conn.connect();

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
            StringBuilder sb = new StringBuilder();
            sb.append(br.readLine());
            retorno = sb.toString();

            conn.disconnect();

        } catch (Exception e) {

        }

        return retorno;
    }

    @Override
    protected void onPostExecute(String retorno) {

        boolean parsable = true;

        try {
            Integer.parseInt(retorno);
        } catch (NumberFormatException e) {
            parsable = false;
        }

        if (parsable) {

            progress.setMessage("Entrando no aplicativo...");

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    progress.dismiss();
                    context.startActivity(new Intent(context, BigRockActivity.class));
                }
            }, 3000);

        } else {

            progress.dismiss();
            Toast.makeText(context, retorno, Toast.LENGTH_LONG).show();

        }

    }

}