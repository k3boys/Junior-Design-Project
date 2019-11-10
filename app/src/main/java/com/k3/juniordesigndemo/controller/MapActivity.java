package com.k3.juniordesigndemo.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import androidx.annotation.NonNull;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;
import androidx.core.content.ContextCompat;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.k3.juniordesigndemo.R;
import com.k3.juniordesigndemo.model.Model;
import com.k3.juniordesigndemo.model.ReportSingleton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback,
        EditText.OnKeyListener, View.OnClickListener, GoogleMap.OnCameraIdleListener {

    private GoogleMap mMap;
    private Geocoder geocoder;

    private static final int ACCESS_FINE_LOCATION_CODE = 0;
    private static Model model = Model.instance();

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

        // Obtain the SupportMapFragment and get notified when the map is ready to be used
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if(mapFragment != null) mapFragment.getMapAsync(this);

        // If we're coming back from a report, notify user that report has been submitted
        showReportSubmittedFeedback();
    }

    /**
     * Move the camera once the map is ready and request location permissions
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Zoom in to most recent location
        LatLng location = getMostRecentLocation();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(11));
        mMap.setOnCameraIdleListener(this);

        // Request location permission from user
        requestLocationPermission();

        ReportSingleton.setContext(getApplication().getBaseContext());
    }

    /**
     * Returns user's most recent location, or location of last report if not available
     */
    public LatLng getMostRecentLocation() {
        LatLng location = null;
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(locationManager != null) {
                Location loc = locationManager.getLastKnownLocation(locationManager.getBestProvider(new Criteria(), false));
                if(loc == null) {
                    location = new LatLng(model.getLastReportLat(), model.getLastReportLong());
                } else {
                    location = new LatLng(loc.getLatitude(), loc.getLongitude());
                }
            }
        }
        if(location == null) location = new LatLng(model.getLastReportLat(), model.getLastReportLong());
        return location;
    }

    /**
     * Request location permission from the user if it has not been granted or enable location functionality
     */
    public void requestLocationPermission() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                ACCESS_FINE_LOCATION_CODE);
        } else {
            enableMyLocation();
        }
    }

    /**
     * Show feedback based on response to location permission request and enable location functionality if authorized
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Location Permission Granted", Toast.LENGTH_SHORT).show();
            enableMyLocation();
        } else {
            Toast.makeText(this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Enable location functionality (i.e. blue dot and location button)
     */
    private void enableMyLocation() {
        mMap.setMyLocationEnabled(true);

        // Move the "my location" button (extremely hacky, but works)
        // Get the button view
        View locationButton = ((View) findViewById(R.id.map)
            .findViewById(Integer.parseInt("1")).getParent())
            .findViewById(Integer.parseInt("2"));
        // Move it to bottom-right
        RelativeLayout.LayoutParams rlp = (RelativeLayout.LayoutParams) locationButton.getLayoutParams();
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, 0);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rlp.setMargins(0, 0, 0, 300);
    }

    /**
     * Show feedback that report has been submitted if we are coming back from report activity
     */
    private void showReportSubmittedFeedback() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getBoolean("submitted-report")) {
            Toast.makeText(getApplicationContext(), "Report submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Callback that listens to "enter" key to update location once user enters an address
     */
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
            try {
                List<Address> addressList = geocoder.getFromLocationName(addressEditText.getText().toString(), 1);
                if(!addressList.isEmpty()) {
                    Address address = addressList.get(0);
                    LatLng addressLocation = new LatLng(address.getLatitude(), address.getLongitude());
                    CameraUpdate update = CameraUpdateFactory.newLatLng(addressLocation);
                    mMap.animateCamera(update);
                }
            } catch(IOException e) {}
        }
        return false;
    }

    /**
     * Callback that listens to report button and launches report activity
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, PagedReportActivity.class);
        String address = addressEditText.getText().toString();
        intent.putExtra("ADDRESS", address);
        LatLng currLatLng = mMap.getCameraPosition().target;
        intent.putExtra("LAT", currLatLng.latitude);
        intent.putExtra("LNG", currLatLng.longitude);
        startActivity(intent);
    }

    /**
     * Callback for when camera becomes idle after user moves it that updates the address in the EditText
     */
    @Override
    public void onCameraIdle() {
        addressEditText.setText(getAddress());
    }

    private String getAddress() {
        try {
            LatLng currLatLng = mMap.getCameraPosition().target;
            List<Address> addressList = geocoder.getFromLocation(currLatLng.latitude, currLatLng.longitude, 1);
            if(!addressList.isEmpty()) {
                return addressList.get(0).getAddressLine(0);
            }
        } catch(IOException e) {}
        return "";
    }
}
