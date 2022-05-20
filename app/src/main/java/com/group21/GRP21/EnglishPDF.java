package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class EnglishPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_pdf);
        //Load PDF Study guides
        PDFView englishpdfview = findViewById(R.id.EnglishPDFview);
        englishpdfview.fromAsset("English.pdf").load();
    }
}