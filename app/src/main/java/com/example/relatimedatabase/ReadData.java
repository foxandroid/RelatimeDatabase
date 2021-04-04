package com.example.relatimedatabase;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.relatimedatabase.databinding.ActivityReadDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ReadData extends AppCompatActivity {

    ActivityReadDataBinding binding;
    DatabaseReference reference;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.readdataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.etusername.getText().toString();
                if (!username.isEmpty()){

                    readData(username);
                }else{

                    Toast.makeText(ReadData.this,"PLease Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void readData(String username) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(username).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {

                if (task.isSuccessful()){

                    if (task.getResult().exists()){

                        Toast.makeText(ReadData.this,"Successfully Read",Toast.LENGTH_SHORT).show();
                        DataSnapshot dataSnapshot = task.getResult();
                        String firstName = String.valueOf(dataSnapshot.child("firstName").getValue());
                        String lastName = String.valueOf(dataSnapshot.child("lastName").getValue());
                        String age = String.valueOf(dataSnapshot.child("age").getValue());
                        binding.tvFirstName.setText(firstName);
                        binding.tvLastName.setText(lastName);
                        binding.tvAge.setText(age);


                    }else {

                        Toast.makeText(ReadData.this,"User Doesn't Exist",Toast.LENGTH_SHORT).show();

                    }


                }else {

                    Toast.makeText(ReadData.this,"Failed to read",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}