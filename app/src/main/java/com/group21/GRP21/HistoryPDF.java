package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class HistoryPDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_pdf);

        //Load PDF Study guides

        PDFView Historypdfview = findViewById(R.id.HistoryPDFview);
        Historypdfview.fromAsset("Geo.pdf").load();
    }
}