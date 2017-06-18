package br.eti.rodrigosiqueira.bigrock;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import br.eti.rodrigosiqueira.bigrock.dao.BigRockDAO;
import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class BigRocksFullMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationManager manager;
    private LocationListener listener;

    private List<BigRock> bigRocks;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_rocks_full_map);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        BigRockDAO bigRockDAO = new BigRockDAO(this);
        bigRocks = bigRockDAO.getBigRocks();

        MapView mapView = (MapView) findViewById(R.id.full_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();

        for(BigRock bigRock : bigRocks) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(bigRock.getNrLat()),Double.parseDouble(bigRock.getNrLng()))));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(bigRock.getNrLat()),Double.parseDouble(bigRock.getNrLng())),10.0f));
        }
    }
}
