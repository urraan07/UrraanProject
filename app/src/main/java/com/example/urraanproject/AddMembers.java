package com.example.urraanproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class AddMembers extends AppCompatActivity {
RecyclerView List_of_members;
FloatingActionButton btn_Add_member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        List_of_members = findViewById(R.id.RecyclerViewList);
        btn_Add_member = findViewById(R.id.floatingActionButton);

        btn_Add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMembers.this, Search_Member.class);
                startActivity(intent);
                finish();

            }
        });



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(AddMembers.this,DashBoard.class);
        startActivity(intent);
        finish();
        return;
    }
}
