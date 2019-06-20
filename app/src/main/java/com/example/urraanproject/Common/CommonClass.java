package com.example.urraanproject.Common;

import android.widget.Toast;

import com.example.urraanproject.OTPActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CommonClass {

    public static String verified="verified";
    public static String UserId=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
    public static DatabaseReference UserReference=FirebaseDatabase.getInstance().getReference("user");
    public static DatabaseReference Group_Reference=FirebaseDatabase.getInstance().getReference("Groups");

}
