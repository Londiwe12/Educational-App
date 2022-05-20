package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

public class LifeSciencePDF extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_science_pdf);

        PDFView lspdfview = findViewById(R.id.LsPDFview);
        lspdfview.fromAsset("Gr12-Life-Sciences-TG-Study-Guide.pdf").load();

    }
}