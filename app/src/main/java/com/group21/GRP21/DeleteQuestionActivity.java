package com.group21.GRP21;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeleteQuestionActivity extends AppCompatActivity{
    //Variable Initialization

    private EditText question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_question);

        //Variable Declaration

        question = findViewById(R.id.edit_text_question);

        Button buttonSubmit = findViewById(R.id.button_submit1);
        buttonSubmit .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delModule();
            }
        });
    }

    private void delModule() {
        Toast.makeText(this, "Question Deleted.", Toast.LENGTH_SHORT).show();
    }
    @Override
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_delete_question,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_m:
                start_home();
                return true;
            case R.id.add_m:
                start_add_m();
                return true;
            case R.id.add_q:
                start_add_q();
                return true;
            case R.id.sign_out:
                start_sign_out();
                return true;
            case R.id.delete_m:
                start_del();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void start_add_m() {
        Intent intent = new Intent(DeleteQuestionActivity.this, InsertModuleActivity.class);
        startActivity(intent);
    }

    private void start_add_q() {
        Intent intent = new Intent(DeleteQuestionActivity.this, InsertQuestionActivity.class);
        startActivity(intent);
    }

    private void start_sign_out() {
        Intent intent = new Intent(DeleteQuestionActivity.this, loginActivity.class);
        startActivity(intent);
    }

    private void start_del() {
        Intent intent = new Intent(DeleteQuestionActivity.this, DeleteModuleActivity.class);
        startActivity(intent);
    }
    private void start_home() {
        Intent intent = new Intent(DeleteQuestionActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }

}
