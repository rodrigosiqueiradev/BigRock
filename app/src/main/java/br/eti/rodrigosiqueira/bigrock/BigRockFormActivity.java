package br.eti.rodrigosiqueira.bigrock;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import br.eti.rodrigosiqueira.bigrock.dao.BigRockDAO;
import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class BigRockFormActivity extends AppCompatActivity {

    private Double latitude;
    private Double longitude;
    private BigRock bigRock = new BigRock();
    private String tpStatus = "INICIADO";

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rock_form);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Button btnSave = (Button) findViewById(R.id.btnSave);
        Button btnDelete =  (Button) findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        bigRock = (BigRock) intent.getSerializableExtra("bigRock");
        try {
            if(bigRock.getIdBigRock() > 0) {
                EditText nmBigRock = (EditText) findViewById(R.id.nmBigRock);
                nmBigRock.setText(bigRock.getNmBigRock());
                EditText dsBigRock = (EditText) findViewById(R.id.dsBigRock);
                dsBigRock.setText(bigRock.getDsBigRock());

                btnDelete.setVisibility(btnDelete.VISIBLE);
                setTitle("Edição");
            }
        } catch (Exception e){
            tpStatus = "INICIADO";
        }


        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

        btnDelete.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                BigRockDAO bigRockDAO = new BigRockDAO(v.getContext());
                bigRockDAO.deleteBigRock(bigRock);
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Context context = getApplicationContext();
                CharSequence text = "Campo Obrigatorio";
                int duration = Toast.LENGTH_SHORT;




                EditText nmBigRock = (EditText) findViewById(R.id.nmBigRock);
                EditText dsBigRock = (EditText) findViewById(R.id.dsBigRock);

                if(nmBigRock.getText().length() == 0) {
                    //CAMPO OBRIGATORIO
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }

                if(dsBigRock.getText().length() == 0) {
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    return;
                }


                BigRockDAO bigRockDAO = new BigRockDAO(v.getContext());

                bigRock.setNmBigRock(nmBigRock.getText().toString());
                bigRock.setDsBigRock(dsBigRock.getText().toString());
                bigRock.setTpStatus(tpStatus);
                bigRock.setNrLat(latitude.toString());
                bigRock.setNrLng(longitude.toString());

                try {
                    if(bigRock.getIdBigRock() > 0) {
                        bigRock = bigRockDAO.updateBigRock(bigRock);
                    } else {
                        bigRock = bigRockDAO.insertBigRock(bigRock);
                    }
                }catch (Exception e) {
                    bigRock = bigRockDAO.insertBigRock(bigRock);
                }



                Intent newIntent = getIntent().putExtra("bigRock", bigRock);
                setResult(RESULT_OK, newIntent);
                finish();

            }
        });
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.ckChecked:
                if (checked) {
                    tpStatus = "FINALIZADO";
                } else {
                    tpStatus = "INICIADO";
                }
                break;
        }
    }
}
