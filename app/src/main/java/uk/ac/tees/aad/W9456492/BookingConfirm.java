package uk.ac.tees.aad.W9456492;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class BookingConfirm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        SharedPreferences sharedPreferences = getSharedPreferences("location",MODE_PRIVATE);
        float lat = sharedPreferences.getFloat("lat",20);
        float lng  = sharedPreferences.getFloat("lng",20);

        sharedPreferences = getSharedPreferences("image",MODE_PRIVATE);
        String image = sharedPreferences.getString("image","");



    }
}
