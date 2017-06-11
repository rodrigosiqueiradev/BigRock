package br.eti.rodrigosiqueira.bigrock;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import br.eti.rodrigosiqueira.bigrock.model.BigRock;

public class CardTarefa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private LocationListener listener;
    private LocationManager locationManager;

    private BigRock bigRock;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_tarefa, container, false);

        this.bigRock = new Gson().fromJson(getArguments().getString("BigRock"), BigRock.class);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //PLACEHOLDER FOR TASK CONTENT
        TextView status = (TextView) view.findViewById(R.id.card_status);
        status.setText(this.bigRock.getTpStatus());

        TextView nome = (TextView) view.findViewById(R.id.card_name);
        nome.setText(this.bigRock.getNmBigRock());

        TextView desc = (TextView) view.findViewById(R.id.card_description);
        desc.setText(this.bigRock.getDsBigRock());
        //PLACEHOLDER FOR TASK CONTENT

        //LOAD MAP WITH FAKE LOCATION
        MapView mapView = (MapView) view.findViewById(R.id.card_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setAllGesturesEnabled(false);

        mMap.clear();
        mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(this.bigRock.getNrLat()),Double.parseDouble(this.bigRock.getNrLng()))));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Double.parseDouble(this.bigRock.getNrLat()),Double.parseDouble(this.bigRock.getNrLng())),13.0f));
    }

    public CardTarefa() {

    }
}
