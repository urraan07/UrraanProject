package com.example.urraanproject.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.example.urraanproject.OTPActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.MODE_PRIVATE;

public class CommonClass {
    public static String Tag="MyTag";
    public static SharedPreferences sharedPreferences;
    public static String Isverified="verified";
    public static String UserId=FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
    public static DatabaseReference UserReference=FirebaseDatabase.getInstance().getReference("user");
    public static DatabaseReference Group_Reference=FirebaseDatabase.getInstance().getReference("Groups");




}
