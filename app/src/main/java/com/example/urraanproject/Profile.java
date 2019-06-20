package com.example.urraanproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.urraanproject.Common.CommonClass;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Profile extends AppCompatActivity {

EditText Txt_name,Txt_country;

Button Btn_Submit;

String Name,Country;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_main);

        Txt_name = findViewById(R.id.txtName);
        Txt_country = findViewById(R.id.txtCountry);
        Btn_Submit = findViewById(R.id.submitbtn);



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
                    DatabaseReference ref=CommonClass.UserReference.child(CommonClass.UserId);
                    Toast.makeText(Profile.this, ""+CommonClass.UserId, Toast.LENGTH_SHORT).show();
                    Map<String,Object> map=new HashMap();
                    map.put("name",Name);
                    map.put("country",Country);
                    ref.updateChildren(map);
                   // CommonClass.UserReference.setValue(map);
                }


            }
        });



    }
}
