package com.k3.juniordesigndemo.controller;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.model.Model;

public class viewActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static Model model = Model.instance();
    private static boolean askedLocationPermission;
    private static boolean dontAskLocationPermisson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        askedLocationPermission = false;
        dontAskLocationPermisson = false;
        //TODO: load don't ask again from previous response
    }

    public LatLng getRecentLocation() {
        LatLng location;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = getCurrentPosition();
        } else {
            if (dontAskLocationPermisson || askedLocationPermission) {
                location =  new LatLng(model.getLastReportLat(), model.getLastReportLong());
            } else {
                askLocationPermission();
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    location = getCurrentPosition();
                } else {
                    location = new LatLng(model.getLastReportLat(), model.getLastReportLong());
                }
            }
        }
        return location;
    }

    public LatLng getCurrentPosition() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            return new LatLng(loc.getLatitude(), loc.getLongitude());
        }
        throw new Error("Attempted to obtain location without permissions");
    }

    public void askLocationPermission() {
        askedLocationPermission = true;
    }

    public void TransitionMapToLocation(LatLng location) {
        if (location != null) {
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13));

            CameraPosition cameraPosition = new CameraPosition.Builder()
                    .target(location)      // Sets the center of the map to location user
                    .zoom(17)                   // Sets the zoom
                    .bearing(90)                // Sets the orientation of the camera to east
                    .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                    .build();                   // Creates a CameraPosition from the builder
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
        GoogleMap mMap = googleMap;
        LatLng currentLocation = getRecentLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(currentLocation));
    }
}
