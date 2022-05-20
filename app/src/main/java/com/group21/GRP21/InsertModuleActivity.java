package com.group21.GRP21;

import android.annotation.SuppressLint;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertModuleActivity extends AppCompatActivity{
    //Variable Declaration
    private EditText module;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_module);

        //Variable Initialization

        module = findViewById(R.id.edit_text_module);

        Button buttonSubmit = findViewById(R.id.button_add_mod);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addModule();
            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_addm,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_q:
                start_add_q();
                return true;
            case R.id.home_m:
                start_home();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void start_add_q() {
        Intent intent = new Intent(InsertModuleActivity.this, InsertQuestionActivity.class);
        startActivity(intent);
    }

    private void start_home() {
        Intent intent = new Intent(InsertModuleActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }
    private void addModule() {
        String moduleString = module.getText().toString();

        if (checkLength(moduleString)) {
            Module newModule = new Module(moduleString);
            DbCommands dbcommand = DbCommands.getInstance(this);
            dbcommand.addModule(newModule);

            clearField();

            Toast.makeText(this, "Module added!", Toast.LENGTH_SHORT).show();

        } else
            Toast.makeText(this, "Module name is too short.", Toast.LENGTH_SHORT).show();
    }

    //    Clear all fields after insert

    private void clearField() {
        module.setText("");
    }

    private void start_main() {
        Intent intent = new Intent(InsertModuleActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }

    private boolean checkLength(String string) {
        if (string.length() > 3)
            return true;
        else
            return false;
    }
}
