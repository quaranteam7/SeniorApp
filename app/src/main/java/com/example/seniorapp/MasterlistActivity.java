package com.example.seniorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MasterlistActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MasterlistAdapter mAdapter;

    private DatabaseReference databaseReference;
    private ArrayList<Members> mMembers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterlist);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMembers = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Members");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot : snapshot.getChildren()){
                    Members members = postSnapshot.getValue(Members.class);
                    mMembers.add(members);

                    mAdapter = new MasterlistAdapter(MasterlistActivity.this, mMembers);
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MasterlistActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}