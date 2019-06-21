package com.example.urraanproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.SearchView;
import android.widget.TextView;

public class Search_Member extends AppCompatActivity {
    SearchView Search_Member;
    TextView txt_Name,txt_Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__member);

        Search_Member =(SearchView) findViewById(R.id.Search);
        txt_Name = findViewById(R.id.textNameCard);
        txt_Phone = findViewById(R.id.textPhoenCard);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(Search_Member.this,AddMembers.class);
        startActivity(intent);
        finish();
        return;
    }
}
