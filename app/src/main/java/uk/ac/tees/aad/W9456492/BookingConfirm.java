package uk.ac.tees.aad.W9456492;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BookingConfirm extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        SharedPreferences sharedPreferences = getSharedPreferences("location",MODE_PRIVATE);
        float lat = sharedPreferences.getFloat("lat",20);
        float lng  = sharedPreferences.getFloat("lng",20);

        sharedPreferences = getSharedPreferences("image",MODE_PRIVATE);
        String image = sharedPreferences.getString("image","");

        SimpleDateFormat
        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String date = dateFormat.format(Calendar.getInstance().getTime());

        sharedPreferences = getSharedPreferences("type",MODE_PRIVATE);
        String type = sharedPreferences.getString("type","");


        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth  = FirebaseAuth.getInstance();
        firebaseUser =  firebaseAuth.getCurrentUser();

        BookingModel model = new BookingModel();
        model.setDate(date);
        model.setImage(image);
        model.setLat(lat);
        model.setLng(lng);
        model.setType(type);

        firebaseDatabase.getReference("bookings").child(firebaseUser.getUid()).push().setValue(model);



    }


    @Override
    public void onBackPressed() {
        finishAffinity();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
