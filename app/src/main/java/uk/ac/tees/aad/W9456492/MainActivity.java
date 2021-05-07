package uk.ac.tees.aad.W9456492;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button plumbing;
    Button ele;
    Button gardening;
    Button cleaning;
    Button logout;
    Button bookings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bookings  = findViewById(R.id.bookings);
        bookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ShowBookings.class));
            }
        });
        logout  = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });

        plumbing = findViewById(R.id.plumbing);
        plumbing.setOnClickListener(this);
        ele = findViewById(R.id.eletrician);
        ele.setOnClickListener(this);
        gardening = findViewById(R.id.gardening);
        gardening.setOnClickListener(this);
        cleaning =  findViewById(R.id.cleaning);
        cleaning.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {

        Intent i = new Intent(getApplicationContext(),LocationActivity.class);
            switch (v.getId()) {
                case R.id.plumbing:
                    i.putExtra("option","Plumbing");
                    break;
                case R.id.eletrician:
                    i.putExtra("option","Electric Work");
                    break;
                case R.id.gardening:
                    i.putExtra("option","Gardening");
                    break;
                case R.id.cleaning:
                    i.putExtra("option","Cleaning");
                    break;

                default:
                    break;
            }
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
