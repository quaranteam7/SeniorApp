package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

public class AddMembersActivity extends AppCompatActivity {

    StorageReference storageReference;
    public static final int GALLERY_REQUEST_CODE = 105;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView url;
    String currentPhotoPath;
    ProgressDialog progressDialog;
    ImageView selectedPhoto;
    String ImageUrl;

    TextView imageUrl;
    EditText memberName, memberBirthday, memberGender, memberMunicipality, memberBarangay, memberZone;
    Button btnAddMember;
    ImageView mImageView;
    ProgressBar progressBar3;

    private DatabaseReference databaseReference;
    FirebaseStorage storage;
    private StorageTask storageTask;
    private Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        String imageURL =getIntent().getStringExtra("mImageUrl");

        memberName = findViewById(R.id.memberName);
        memberBirthday = findViewById(R.id.memberBirthday);
        memberGender = findViewById(R.id.memberGender);
        memberMunicipality = findViewById(R.id.memberMunicipality);
        memberBarangay = findViewById(R.id.memberBarangay);
        memberZone = findViewById(R.id.memberZone);
        imageUrl = findViewById(R.id.imageURL);
        imageUrl.setText(imageURL);

        mImageView = findViewById(R.id.mImageView);

        btnAddMember = findViewById(R.id.btnAddMember);

        progressBar3 = findViewById(R.id.progressBar3);

        storageReference = FirebaseStorage.getInstance().getReference("Members");
        databaseReference = FirebaseDatabase.getInstance().getReference("Members");
        storage = FirebaseStorage.getInstance();

        btnAddMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    addMember();
            }
        });
    }

    private void addMember() {
        Members members = new Members(imageUrl.getText().toString(), memberName.getText().toString(), memberBirthday.getText().toString(),
                memberGender.getText().toString(), memberMunicipality.getText().toString(), memberBarangay.getText().toString(),
                memberZone.getText().toString());
        String memberId = databaseReference.push().getKey();
        databaseReference.child(memberId).setValue(members);

        Toast.makeText(AddMembersActivity.this, "Upload Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, HomeActivity.class));
    }
}