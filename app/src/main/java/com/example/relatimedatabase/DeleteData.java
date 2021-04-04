package com.example.relatimedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.relatimedatabase.databinding.ActivityDeleteDataBinding;
import com.example.relatimedatabase.databinding.ActivityReadDataBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DeleteData extends AppCompatActivity {

    ActivityDeleteDataBinding binding;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleteDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.deletedataBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = binding.etusername.getText().toString();
                if (!username.isEmpty()){

                    deleteData(username);

                }else{

                    Toast.makeText(DeleteData.this,"Please Enter Username",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void deleteData(String username) {

        reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(username).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){

                    Toast.makeText(DeleteData.this,"Successfuly Deleted",Toast.LENGTH_SHORT).show();
                    binding.etusername.setText("");


                }else {

                    Toast.makeText(DeleteData.this,"Failed",Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
}