package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Admin_Login extends AppCompatActivity {
    //Variable Declaration
    Button AddQ, AddSub, deleteM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        //Variable Initialization

        AddQ = (Button) findViewById(R.id.AddQuestion);
        AddSub = (Button) findViewById(R.id.AddSubject);
        deleteM = (Button) findViewById(R.id.DeleteModule);



        //Admin Adding Subjects
        AddSub.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Login.this, InsertModuleActivity.class);
                startActivity(intent);
            }
        });
        // Admin Adding Questions
        AddQ.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Login.this, InsertQuestionActivity.class);
                startActivity(intent);
            }
        });
        //Admin Delete Subjects
        deleteM.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Admin_Login.this, DeleteModuleActivity.class);
                startActivity(intent);
            }
        });

    }
}