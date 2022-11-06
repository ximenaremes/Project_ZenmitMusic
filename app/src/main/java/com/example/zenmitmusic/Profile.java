package com.example.zenmitmusic;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {

   private ImageView iconBack;
   private ImageView image_profile_user,image_phone,image_email;
   private TextView textName,textPhone,textEmail;
   private Button resendCode;
   private String email,userId;
   private FirebaseDatabase database;
   private DatabaseReference userRef;
   private FirebaseFirestore fStore;
   private StorageReference storageReference;
   private FirebaseAuth mAuth;
   private FirebaseUser user;
   private final String TAG =this.getClass().getName().toUpperCase();



    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        iconBack = findViewById(R.id.iconBack);
        textName =findViewById(R.id.textName);
        textPhone=findViewById(R.id.textPhone);
        textEmail=findViewById(R.id.textEmail);
        image_profile_user=findViewById(R.id.image_profile_user);


        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        iconBack.setOnClickListener(view -> startActivity(new Intent(Profile.this, Settings.class)));


        StorageReference profileRef = storageReference.child("USER/" +mAuth.getCurrentUser().getUid() + "profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(image_profile_user);
            }
        });

        userId = mAuth.getCurrentUser().getUid();
        user = mAuth.getCurrentUser();

        DocumentReference documentReference = fStore.collection("USER").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                textName.setText(value.getString("name"));
                textPhone.setText(value.getString("phone"));
                textEmail.setText(value.getString("email"));
            }
        });
        if(user!=null)
            Log.d("Profile...", "OnCreate: " + userId + "phone "+ mAuth.getCurrentUser().getPhoneNumber()+  "name " + mAuth.getCurrentUser().getDisplayName() + "email " +mAuth.getCurrentUser().getEmail());

//        DocumentReference documentReference = fStore.collection("users").document(userId);
//        documentReference.addSnapshotListener(this, (docum))

       /* button_change_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                //startActivityForResult(openGalleryIntent, 1000);
            }
        });*/

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            if(resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                //profileImage.setImageURI(imageUri);  //comment now


                uploadImageToFirebase(imageUri);

            }
        }
    }
    private void uploadImageToFirebase(Uri imageUri) {
        //upload image to firebase storage
        StorageReference fileReference = storageReference.child("USER/" +mAuth.getCurrentUser().getUid() + "profile.jpg");
        fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                //Toast.makeText(ProfileActivity.this, "Image Uploaded.", Toast.LENGTH_SHORT).show();
                fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(image_profile_user);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Profile.this, "Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

