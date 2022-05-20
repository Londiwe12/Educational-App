package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ResetActivity extends AppCompatActivity {

    TextView username;
    TextInputEditText Password,rePassword;
    Button confirm;
    DbCommands DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        //Declare variables

        username = (TextView) findViewById(R.id.username_reset_text);
        Password = (TextInputEditText) findViewById(R.id.password_reset);
        rePassword = (TextInputEditText) findViewById(R.id.repassword_reset);
        confirm = (Button) findViewById(R.id.btnconfirm);
        DB = new DbCommands(this);

        Intent intent = getIntent();
        username.setText(intent.getStringExtra("username"));


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String password = Password.getText().toString();
                String repassword = rePassword.getText().toString();

                Boolean checkpasswordupdate = DB.updatepassword(user, password);
                if(password.equals(repassword)) {
                    if (checkpasswordupdate == true) {

                        Intent intent = new Intent(getApplicationContext(), loginActivity.class);
                        startActivity(intent);
                        Toast.makeText(ResetActivity.this, "Password Updated Successfully", Toast.LENGTH_SHORT).show();
                    } else {

                        Toast.makeText(ResetActivity.this, "Password Is Not Updated", Toast.LENGTH_SHORT).show();

                    }
                }else {

                    Toast.makeText(ResetActivity.this, "Password Is Not Matching", Toast.LENGTH_SHORT).show();


                }

            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_reset,menu);
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
        Intent intent = new Intent(ResetActivity.this, loginActivity.class);
        startActivity(intent);
    }
}