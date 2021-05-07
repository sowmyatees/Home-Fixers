package uk.ac.tees.aad.W9456492;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button plumbing;
    Button ele;
    Button gardening;
    Button cleaning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    i.putExtra("option","plumbing");
                    break;
                case R.id.eletrician:
                    i.putExtra("option","ele");
                    break;
                case R.id.gardening:
                    i.putExtra("option","gardening");
                    break;
                case R.id.cleaning:
                    i.putExtra("option","cleaning");
                    break;
                default:
                    break;
            }
        startActivity(i);
    }
}
