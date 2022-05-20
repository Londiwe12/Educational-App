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

public class loginActivity extends AppCompatActivity {
    TextInputEditText email, password;
    Button btnlogin, BTNN, AdminBtn;
    TextView forgot, signup;
    DbCommands DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (TextInputEditText) findViewById(R.id.email1);
        password = (TextInputEditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        AdminBtn = (Button) findViewById(R.id.Admin_login);
        forgot = (TextView) findViewById(R.id.btnforgot);
        signup = (TextView) findViewById(R.id.btnsignup1);
        DB = new DbCommands(this);


        //User Login & Validations for Input fields

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail = email.getText().toString();
                String pass = password.getText().toString();


                if(mail.equals("")||pass.equals(""))
                    Toast.makeText(loginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(mail,pass);
                    if(checkuserpass==true){
                        Intent intent  = new Intent(getApplicationContext(), Categories.class);
                        startActivity(intent);
                        email.getText().clear();
                        password.getText().clear();

                    }else{
                        Toast.makeText(loginActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        //Admin Login
        AdminBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextInputEditText textInputEditText = findViewById(R.id.email1);
                String text = textInputEditText.getText().toString();
                if(text.equals("admin")){
                    openAdminLogIn();
                }



            }
        });

        //Register new User on the database
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        //Forgot password

        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_login,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sign_u:
                start_sign_u();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void start_sign_u() {
        Intent intent = new Intent(loginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }
    public void openAdminLogIn(){
        Intent intent = new Intent(this, Admin_Login.class);
        startActivity(intent);
    }
}