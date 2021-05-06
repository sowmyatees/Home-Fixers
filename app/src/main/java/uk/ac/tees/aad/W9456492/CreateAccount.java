package uk.ac.tees.aad.W9456492;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    EditText email ;
    EditText password;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser  = firebaseAuth.getCurrentUser();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        Button up = findViewById(R.id.signupBtn);
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
                createAccount();
            }
        });

    }



    private void createAccount() {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                firebaseUser = authResult.getUser();
                Toast.makeText(getApplicationContext(),"SuccessFully Created",Toast.LENGTH_LONG).show();
               recreate();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                firebaseUser = null;
            }
        });
    }



    private void validate() {
        if(email.getText().toString().isEmpty()){
            email.setError("Cannot be empty");
            email.findFocus();
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            email.setError("Enter valid Email");
            email.findFocus();
        }
        if(password.getText().toString().isEmpty())
        {
            password.setError("Cannot be empty");
            password.findFocus();
        }
        if(password.getText().toString().length()<6)
        {
            password.setError("More than 6 chars");
            password.findFocus();
        }

    }
    @Override
    protected void onStart() {
        super.onStart();
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
