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

import com.example.urraanproject.Common.CommonClass;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateGroup extends AppCompatActivity {

    EditText txtGroupName,txtGroupDescription;
    Button btnBroupCreation;
    String ProfileImageUrl;
    ImageView groupImage;
    Uri resultUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        inializedComponents();
        btnBroupCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Group_name = txtGroupName.getText().toString().trim();
                String Group_Description =txtGroupDescription.getText().toString().trim();
                if(Group_name.isEmpty()){
                    txtGroupName.requestFocus();
                    txtGroupName.setError("Pleas enter Name");
                } if(Group_Description.isEmpty()){
                    txtGroupDescription.requestFocus();
                    txtGroupDescription.setError("Pleas enter Country");
                }else{

                    // CommonClass.UserId
                   final DatabaseReference ref= CommonClass.Group_Reference.child(CommonClass.UserId);
                    Toast.makeText(CreateGroup.this, ""+CommonClass.UserId, Toast.LENGTH_SHORT).show();
                    Map<String,Object> map=new HashMap();
                    map.put("name",Group_name);
                    map.put("description",Group_Description);
                    ref.updateChildren(map);
                    Toast.makeText(CreateGroup.this, "Group Creadted", Toast.LENGTH_SHORT).show();
                    // CommonClass.UserReference.setValue(map);
//                    Intent intent = new Intent(CreateGroup.this, AddMembers.class);
//                    startActivity(intent);
//                    finish();

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
                        Toast.makeText(CreateGroup.this, "Please First add your image", Toast.LENGTH_SHORT).show();


                        //Snackbar.make(rootLayout,"Please attach your image..",Snackbar.LENGTH_LONG).show();
//                Intent intent = new Intent(Passenger_SettingsActivity.this, Passenger_Dashboard.class);
//                startActivity(intent);
//                finish();
//                return;
                    }


                }

            }
        });

        groupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });
    }
    private void inializedComponents() {
        txtGroupName=findViewById(R.id.txtGroupName);
        txtGroupDescription=findViewById(R.id.txtGroupName);
        btnBroupCreation=findViewById(R.id.btnGroupCreation);
        groupImage=findViewById(R.id.groupImage);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == Activity.RESULT_OK){
            final Uri imageUri = data.getData();
            resultUri = imageUri;
            groupImage.setImageURI(resultUri);
        }
    }
}
