package com.example.relatimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.relatimedatabase.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    String firstName, lastName, age, userName;
    FirebaseDatabase db;
    DatabaseReference reference;

    Button readdataPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        readdataPage = findViewById(R.id.readdataPage);
        readdataPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReaddatapage();
            }
        });



        binding.registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstName = binding.firstName.getText().toString();
                lastName = binding.lastName.getText().toString();
                age = binding.age.getText().toString();
                userName = binding.userName.getText().toString();

                if (!firstName.isEmpty() && !lastName.isEmpty() && !age.isEmpty() && !userName.isEmpty()){

                    Users users = new Users(firstName,lastName,age,userName);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");
                    reference.child(userName).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            binding.firstName.setText("");
                            binding.lastName.setText("");
                            binding.age.setText("");
                            binding.userName.setText("");
                            Toast.makeText(MainActivity.this,"Successfuly Updated",Toast.LENGTH_SHORT).show();

                        }
                    });

                }

            }
        });

    }

    private void openReaddatapage() {
        Intent intent = new Intent(this, ReadData.class);
        startActivity(intent);
    }
}