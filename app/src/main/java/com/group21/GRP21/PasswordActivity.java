package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class PasswordActivity extends AppCompatActivity {
    TextInputEditText email;
    EditText username;
    Button reset;
    DbCommands DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        email = (TextInputEditText) findViewById(R.id.email_reset);
        reset = (Button) findViewById(R.id.btnreset);
        DB = new DbCommands(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = email.getText().toString();

                Boolean checkuser = DB.checkuseremail(mail);
                if(checkuser==true){

                    Intent intent = new Intent(getApplicationContext(), ResetActivity.class);
                    intent.putExtra("username", mail);
                    startActivity(intent);

                }else {

                    Toast.makeText(PasswordActivity.this,"User Does not Exists", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_password,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_i:
                start_sign_i();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    private void start_sign_i() {
        Intent intent = new Intent(PasswordActivity.this, loginActivity.class);
        startActivity(intent);
    }
}