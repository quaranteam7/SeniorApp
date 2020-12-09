package com.example.seniorapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class DisplayPhoto extends AppCompatActivity {

    StorageReference storageReference;
    public static final int GALLERY_REQUEST_CODE = 105;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    TextView url;
    String currentPhotoPath;
    ProgressDialog progressDialog;
    ImageView selectedPhoto;

    EditText memberName, memberBirthday, memberGender, memberMunicipality, memberBarangay, memberZone;
    Button btnAddMember, saveBtn;
    ImageView mImageView;
    ProgressBar progressBar3;

    private DatabaseReference databaseReference;
    FirebaseStorage storage;
    private StorageTask storageTask;
    private Uri mImageUri;




    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==RESULT_OK && data != null && data.getData() != null) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            selectedPhoto.setImageBitmap(imageBitmap);

        }
        if (requestCode == GALLERY_REQUEST_CODE && resultCode == RESULT_OK ) {
            Uri contentUri = data.getData();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String imageFileName = "JPEG_" + timeStamp + "." + getFileExt(contentUri);
            Log.d("tag", "onActivityResult: Gallery Image Uri " + imageFileName);
            selectedPhoto.setImageURI(contentUri);
            uploadImageToFirebase(imageFileName, contentUri);
            progressDialog = new ProgressDialog(this);
            progressDialog.setMax(100);
            progressDialog.setMessage("Uploading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();
            progressDialog.setCancelable(false);

        }
    }

    private void uploadImageToFirebase(final String name, final Uri contentUri) {
        final StorageReference image = storageReference.child("Images/" + name);
        image.putFile(contentUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                image.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        progressDialog.dismiss();
                        url.setText(uri.toString());

                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(DisplayPhoto.this,"Uploaded Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private String getFileExt(Uri contentUri) {
        ContentResolver c = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(c.getType(contentUri));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        // File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    public void galleryBtn(View view) {

        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, GALLERY_REQUEST_CODE);

    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo);

        String imageURL =getIntent().getStringExtra("mImageUrl");

        memberName = findViewById(R.id.memberName);
        memberBirthday = findViewById(R.id.memberBirthday);
        memberGender = findViewById(R.id.memberGender);
        memberMunicipality = findViewById(R.id.memberMunicipality);
        memberBarangay = findViewById(R.id.memberBarangay);
        memberZone = findViewById(R.id.memberZone);

        url = findViewById(R.id.url);

        selectedPhoto = findViewById(R.id.selectedImage);

        saveBtn = findViewById(R.id.saveBtn);

        progressBar3 = findViewById(R.id.progressBar3);

        storageReference = FirebaseStorage.getInstance().getReference("Members");
        databaseReference = FirebaseDatabase.getInstance().getReference("Members");
        storage = FirebaseStorage.getInstance();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String imageURL = url.getText().toString();
//
////        startActivity(new Intent(this, HomeActivity.class));
//
//                if(imageURL.isEmpty()){
//                    Toast.makeText(DisplayPhoto.this, "Select Image First", Toast.LENGTH_SHORT).show();
//                }else{
                    Members members = new Members(url.getText().toString(), memberName.getText().toString(), memberBirthday.getText().toString(),
                            memberGender.getText().toString(), memberMunicipality.getText().toString(), memberBarangay.getText().toString(),
                            memberZone.getText().toString());
                    String memberId = databaseReference.push().getKey();
                    databaseReference.child(memberId).setValue(members);

                    Toast.makeText(DisplayPhoto.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(DisplayPhoto.this, HomeActivity.class);
                    i.putExtra("ImageUrl",imageURL);
                    startActivity(i);
                }
//            }
            });
        }
    }

