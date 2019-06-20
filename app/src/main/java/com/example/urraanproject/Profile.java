package com.example.urraanproject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.urraanproject.Common.CommonClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

EditText Txt_name,Txt_country;

Button Btn_Submit;
String ProfileImageUrl;
String Name,Country;
Uri resultUri;
ImageView ProfileImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);


initializeComponents();

        Btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Name = Txt_name.getText().toString().trim();
                Country =Txt_country.getText().toString().trim();
                if(Name.isEmpty()){
                    Txt_name.requestFocus();
                    Txt_name.setError("Pleas enter Name");
                } if(Country.isEmpty()){
                    Txt_country.requestFocus();
                    Txt_country.setError("Pleas enter Country");
                }else{

                   // CommonClass.UserId
                    final DatabaseReference ref=CommonClass.UserReference.child(CommonClass.UserId);
                    Toast.makeText(Profile.this, ""+CommonClass.UserId, Toast.LENGTH_SHORT).show();
                    Map<String,Object> map=new HashMap();
                    map.put("name",Name);
                    map.put("country",Country);
                    ref.updateChildren(map);
                   // CommonClass.UserReference.setValue(map);


                    if (resultUri != null) {

                        StorageReference filePath = FirebaseStorage.getInstance().getReference().child("profile_images").child(CommonClass.UserId);
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(), resultUri);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos);
                        byte[] data = baos.toByteArray();
                        UploadTask uploadTask = filePath.putBytes(data);

                        uploadTask.addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                finish();
                                return;
                            }
                        });
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                Uri downloadUrl = taskSnapshot.getDownloadUrl();

                                Map newImage = new HashMap();
                                newImage.put("profileImageUrl", downloadUrl.toString());
                                ref.updateChildren(newImage);
                                //simpleProgressBar.setVisibility(View.GONE);

//                                Intent intent=new Intent(Profile.this,Passenger_Dashboard.class);
//                                startActivity(intent);
//                                finish();
//                                return;
                            }
                        });


                    } else {
                        Toast.makeText(Profile.this, "Please First add your image", Toast.LENGTH_SHORT).show();


                        //Snackbar.make(rootLayout,"Please attach your image..",Snackbar.LENGTH_LONG).show();
//                Intent intent = new Intent(Passenger_SettingsActivity.this, Passenger_Dashboard.class);
//                startActivity(intent);
//                finish();
//                return;
                    }




                }


            }
        });

        ProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

    }

    private void initializeComponents() {


        Txt_name = findViewById(R.id.txtName);
        Txt_country = findViewById(R.id.txtCountry);
        Btn_Submit = findViewById(R.id.submitbtn);
        ProfileImage=findViewById(R.id.imageView);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            ProfileImage.setImageURI(resultUri);
        }
    }
}
