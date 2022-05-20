package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Categories extends AppCompatActivity {
    //Variable Declaration
    Button Studyguides, Quiz, AddNotes, StudyMeet;
    Intent intent;
    String type;
    ConstraintLayout bgimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        //Variable Initialization
        Studyguides = (Button) findViewById(R.id.studyguides);
        Quiz = (Button) findViewById(R.id.quiz);
        StudyMeet = (Button) findViewById(R.id.StudyMeet);


        // Navigate User to Main Menu
        Quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent Registercust = new Intent(Categories.this, StartingScreenActivity.class);
                    startActivity(Registercust);
                }

        });
        // Navigate User to Study Guides Selection Menu
        Studyguides.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent Registercust = new Intent(Categories.this, StudyGuidesCategories.class);
                startActivity(Registercust);
            }

        });
        // Navigate User to Meeting Menu
        StudyMeet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Registercust = new Intent(Categories.this, StudyMeet.class);
                startActivity(Registercust);
            }
        });


    }
}
