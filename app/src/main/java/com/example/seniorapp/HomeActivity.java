package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnLogout;
    Button btnDeaths;
    Button btnMasterlist;
    Button btnBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnAdd = findViewById(R.id.btnAdd);
        btnDeaths = findViewById(R.id.btnDeaths);
        btnMasterlist = findViewById(R.id.btnMasterlist);
        btnBirthday = findViewById(R.id.btnBirthday);
//        btnLogout.findViewById(R.id.btnLogout);

        btnDeaths.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DeceasedActivity.class));
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DisplayPhoto.class));
            }
        });

        btnMasterlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMasterlistActivity();
            }
        });


    }

    private void openMasterlistActivity() {
        Intent intent = new Intent(this, MasterlistActivity.class);
        startActivity(intent);
    }
}