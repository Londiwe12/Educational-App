package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class GeographyPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geography_pdf);
        //Load PDF Study guides
        PDFView geopdfview = findViewById(R.id.geoPDFview);
        geopdfview.fromAsset("History.pdf").load();
    }
}