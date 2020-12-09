package com.example.seniorapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    //
    public static final String MEMBER = "member";
    private ImageView imageView;
    private TextView nameView, birthdayView, genderView, municipalityView, barangayView, zoneView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        imageView = findViewById(R.id.imageView);
        nameView = findViewById(R.id.nameView);
        birthdayView = findViewById(R.id.birthdayView);
        genderView = findViewById(R.id.genderView);
        municipalityView = findViewById(R.id.municipalityView);
        barangayView = findViewById(R.id.barangayView);
        zoneView = findViewById(R.id.zoneView);

        Members members = (Members) getIntent().getParcelableExtra(MEMBER);

        Glide.with(this).load(members.getFullname()).into(imageView);
        nameView.setText(members.getBirthday());
        birthdayView.setText(members.getGender());
        genderView.setText(members.getMunicipality());
        municipalityView.setText(members.getBarangay());
        barangayView.setText(members.getZone());
        zoneView.setText(members.getmImageUrl());


    }
}