package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class StudyMeet extends AppCompatActivity {

    //Declare & Initialization of variables
    //    TextView textView11, textView12;
    Button button11;
    SharedPreferences sharedPreferences;
    private static final String KEY_NAME = "";
    private static final String KEY_PASS = "";
    private static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_meet);
//        textView11 = findViewById(R.id.textView11);
//        textView12 = findViewById(R.id.textView12);
        button11 = findViewById(R.id.button11);
        sharedPreferences =getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME,null);
        String pass = sharedPreferences.getString(KEY_PASS,null);
        if(name != null || pass != null){
//            textView11.setText(name);
//            textView12.setText(pass);
        }
        try {
            JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                    .setServerURL(new URL(""))
                    .setWelcomePageEnabled(false)
                    .build();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
      //          Toast.makeText(StudyMeet.this,"Go Back" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), Categories.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                finish();
            }
        });


    }
    public void onButtonClick(View v){
        EditText editText = findViewById(R.id.conferenceName);// here is the conference name that I can use to compare to
        String text = editText.getText().toString();// or what i can do is create multiple buttons that allow

        if (text.length()>0){

            JitsiMeetConferenceOptions options
                    = new JitsiMeetConferenceOptions.Builder()
                    .setRoom(text)
                    .build();
            JitsiMeetActivity.launch(this,options);
        }


    }

}