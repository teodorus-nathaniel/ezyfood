package com.example.ezyfood;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.example.ezyfood.database.Stores;
import com.example.ezyfood.models.Store;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Store> stores;

    boolean auto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        stores = new ArrayList<>();

        Integer auto = getIntent().getIntExtra("auto", -1);
        if(auto != -1) {
            this.auto = true;
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private class Data {
        Store store;
        float distance;

        Data(Store store, float distance) {
            this.store = store;
            this.distance = distance;
        }
    }

    public void selectNearestStore() {
        LatLng current = getCurrentLocation();

        ArrayList<Data> distances = new ArrayList<>();
        for (Store store : stores) {
            Location loc1 = new Location("");
            loc1.setLatitude(current.latitude);
            loc1.setLongitude(current.longitude);

            Location loc2 = new Location("");
            loc2.setLatitude(store.position.latitude);
            loc2.setLongitude(store.position.latitude);

            float distanceInMeters = loc1.distanceTo(loc2);
            distances.add(new Data(store, distanceInMeters));
        }

        Collections.sort(distances, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return (o1.distance > o2.distance) ? 1 : -1;
            }
        });

        Log.d("STORES", "selectNearestStore: " + distances.size());

        ArrayList<Integer> nearest = new ArrayList<>();
        for(int i=0; i<Math.min(distances.size(), 3); i++) {
            nearest.add(distances.get(i).store.id);
        }

        Log.d("STORES", "selectNearestStore: " + nearest.size());

        Intent i = new Intent(this, SelectStoreActivity.class);
        i.putIntegerArrayListExtra("nearest", nearest);
        startActivity(i);
        this.finish();
    }

    public LatLng getCurrentLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        if (ContextCompat.checkSelfPermission( this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        try {
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            double userLat = lastKnownLocation.getLatitude();
            double userLong = lastKnownLocation.getLongitude();
            LatLng location = new LatLng(userLat, userLong);
            return location;
        } catch(Exception e) {
            return null;
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng defaultLocation = new LatLng(-34, 151);
        LatLng currentLocation = getCurrentLocation();
        LatLng usedLocation = currentLocation != null ? currentLocation : defaultLocation;

        CameraPosition position = new CameraPosition.Builder().target(usedLocation).zoom(17).build();

        this.markStores();
        mMap.addMarker(new MarkerOptions().position(usedLocation).title("Your Location"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String title = marker.getTitle();
                String[] tokens = title.split(" ");
                int id = Integer.parseInt(tokens[1]);

                Intent i = new Intent(MapsActivity.this, CartCalculationActivity.class);
                i.putExtra("store_id", id);
                startActivity(i);

                return false;
            }
        });

        if (this.auto) {
            this.selectNearestStore();
        }
    }

    public void markStores() {
        try {
            stores = new Stores(this).getStores(null);
            int i = 0;
            for (Store store : stores) {
                Log.d("LOCATION_STORE", "markStores: " + store.position);
                MarkerOptions marker = new MarkerOptions().position(store.position).title("Store " + (i + 1));
                mMap.addMarker(marker);
                i++;
            }
        } catch(Exception e) {
        }
    }
}
