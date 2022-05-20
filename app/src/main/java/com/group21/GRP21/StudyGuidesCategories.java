package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class StudyGuidesCategories extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_guides_categories);

        ImageButton button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( StudyGuidesCategories.this, LifeSciencePDF.class);
                startActivity(intent);
            }
        });

        ImageButton button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( StudyGuidesCategories.this, GeographyPDF.class);
                startActivity(intent);
            }
        });

        ImageButton button0 = findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( StudyGuidesCategories.this, HistoryPDF.class);
                startActivity(intent);
            }
        });

        ImageButton button5 = findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( StudyGuidesCategories.this,EnglishPDF.class);
                startActivity(intent);
            }
        });
    }
}