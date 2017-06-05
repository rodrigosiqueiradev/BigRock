package br.eti.rodrigosiqueira.bigrock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

public class CardTarefa extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_tarefa, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView status = (TextView) view.findViewById(R.id.card_status);
        status.setText("Status Maroto");

        TextView nome = (TextView) view.findViewById(R.id.card_name);
        nome.setText("Nome do mal");


        TextView desc = (TextView) view.findViewById(R.id.card_description);
        desc.setText("Ta molhado lá fora");

        MapView mapView = (MapView) view.findViewById(R.id.card_map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        mapView.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.setMinZoomPreference(16.0f);
        LatLng latLng = new LatLng(-8.186702, -34.918896);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Tarefa aqui zé ruela");
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }

    public CardTarefa() {

    }
}
