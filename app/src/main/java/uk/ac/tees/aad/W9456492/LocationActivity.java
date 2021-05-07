package uk.ac.tees.aad.W9456492;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

public class LocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        String value =  getIntent().getExtras().get("option").toString();

        SharedPreferences sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString("type",value);
        edit.apply();

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);
        Button useLocation  =  findViewById(R.id.useLocation);
        useLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddImage.class));


            }
        });


    }


        @Override
        public void onMapReady(GoogleMap googleMap)
        {
            mMap = googleMap;
            LatLng location = new LatLng(12,25);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
            getCurrentLatLng(googleMap);
        }

    private void getCurrentLatLng(final GoogleMap googleMap){


        ActivityCompat.requestPermissions( this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            new AlertDialog.Builder(this).setMessage("Turn on the GPS").create().show();
        }
        else {

            boolean grantedPermissions =(
                    ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)  != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(LocationActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            );

            if (grantedPermissions)
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
            else {
                FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                            @Override
                            public void onSuccess(Location location)
                            {
                                mMap = googleMap;
                                LatLng loc = new LatLng(location.getLatitude(),location.getLongitude());
                                latLng = loc;
                                mMap.addMarker(new MarkerOptions().position(loc).title("MY Location"));
                                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 12.0f));

                                SharedPreferences sharedPreferences = getSharedPreferences("location",MODE_PRIVATE);
                                SharedPreferences.Editor editer = sharedPreferences.edit();
                                editer.putFloat("lat",(float)location.getLatitude());
                                editer.putFloat("lng",(float)location.getLongitude());
                                editer.apply();


                            }
                        });
            }

        }
    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
