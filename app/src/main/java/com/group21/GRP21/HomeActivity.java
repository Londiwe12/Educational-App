package com.group21.GRP21;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class HomeActivity extends AppCompatActivity {
    //Variable Declaration

    String EmailHolder;
    TextView Email;
    Button logout ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Variable  Initialization

        Email = (TextView)findViewById(R.id.textView1);
        logout = (Button)findViewById(R.id.btnlogout);

        Intent intent = getIntent();

        // Receiving User Email Send By MainActivity.
//        EmailHolder = intent.getStringExtra(MainActivity.username);

        // Setting up received email to TextView.
        Email.setText(Email.getText().toString()+ EmailHolder);

        // Adding click listener to Log Out button.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(HomeActivity.this,"Log Out Successful", Toast.LENGTH_LONG).show();

            }
        });

    }
}