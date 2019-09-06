package com.k3.juniordesigndemo.controller;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.model.Model;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
    EditText.OnKeyListener, View.OnClickListener, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private static final int ACCESS_FINE_LOCATION_CODE = 0;
    private static Model model = Model.instance();
    private static boolean askedLocationPermission;
    private static boolean dontAskLocationPermisson;

    private Geocoder geocoder;

    // Activity components
    private EditText addressEditText;
    private Button submitReportButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        // Get activity components
        addressEditText = findViewById(R.id.addressEditTextMapActivity);
        submitReportButton = findViewById(R.id.submitReportButton);

        // Set address edit text button callback (for "enter" button)
        addressEditText.setOnKeyListener(this);

        // Set submit report button callback
        submitReportButton.setOnClickListener(this);

        // Create Geocoder
        geocoder = new Geocoder(this, Locale.getDefault());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        requestFineLocationPermission();
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ACCESS_FINE_LOCATION_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    public void requestFineLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        .setTitle("Location Permission")
                        .setMessage("Allow application to retrieve devices location for beet experience")
                        .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityCompat.requestPermissions(MapActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
                            }
                        })
                        .setNegativeButton("no thanks", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .create();
            } else {
                // No explanation request fine location permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, ACCESS_FINE_LOCATION_CODE);
            }
        }
    }

    public LatLng getMostRecentLocation() {
        LatLng location;
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
            location = new LatLng(loc.getLatitude(), loc.getLongitude());
        } else {
            location = new LatLng(model.getLastReportLat(), model.getLastReportLong());
        }
        return location;
    }

    public LatLng getCurrentPosition() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
        }
        throw new Error("Attempted to obtain location without permissions");
    }

    public void askLocationPermission() {
        askedLocationPermission = true;
    }

    public void transitionMapToLocation(@NonNull LatLng location) {
        final int ZOOM = 17;
        final int NORTH = 0;
        final int TILT = 40;

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 13));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(location)      // Sets the center of the map to location user
                .zoom(ZOOM)                   // Sets the zoom
                .bearing(NORTH)                // Sets the orientation of the camera to east
                .tilt(TILT)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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
        // Zoom in to Hampton Roads
        mMap = googleMap;
        LatLng hamptonRoadsLocation = new LatLng(36.9751474, -76.3496662);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(hamptonRoadsLocation));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(11));
        mMap.setOnCameraIdleListener(this);
    }

    ///////////////
    // CALLBACKS //
    ///////////////

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if ((event.getAction() == KeyEvent.ACTION_UP) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
            try {
                List<Address> addressList = geocoder
                    .getFromLocationName(addressEditText.getText().toString(), 1);
                if(!addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    LatLng addressLocation = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(addressLocation));
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        // Submit report button callback
        Intent intent = new Intent(this, ReportActivity.class);
        // Get the address from the EditText, not the Geocoder, might reconsider this approach
        String address = addressEditText.getText().toString();
        intent.putExtra("ADDRESS", address);
        startActivity(intent);
    }


    @Override
    public void onCameraIdle() {
        // Camera idle callback
        try {
            LatLng currLatLng = mMap.getCameraPosition().target;
            List<Address> addressList = geocoder
                .getFromLocation(currLatLng.latitude, currLatLng.longitude, 1);
            if(!addressList.isEmpty()) {
                addressEditText.setText(addressList.get(0).getAddressLine(0));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
