package com.group21.GRP21;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class DeleteModuleActivity extends AppCompatActivity{

    private Spinner spinnerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_module);

        spinnerModule = findViewById(R.id.spinner_module);
        loadModules();

        Button buttonSubmit = findViewById(R.id.button_del_mod);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delModule();
            }
        });
    }
    //    Delete module button action
    private void delModule() {
        Module selectedModule = (Module) spinnerModule.getSelectedItem();
        DbCommands dbCommand = DbCommands.getInstance(this);
        int moduleID = selectedModule.getId();
        boolean success = dbCommand.deleteModule(moduleID);
        if (success)
        {
            Toast.makeText(this, "Subject Deleted.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Something went wrong while trying to delete subject. Refresh your App", Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    @SuppressLint("ResourceType")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_delete_module,menu);
        return true;
    }

    //    Nav bar

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
            case R.id.delete_q:
                start_del_q();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //  action for navbar

    private void start_add_m() {
        Intent intent = new Intent(DeleteModuleActivity.this, InsertModuleActivity.class);
        startActivity(intent);
    }

    private void start_add_q() {
        Intent intent = new Intent(DeleteModuleActivity.this, InsertQuestionActivity.class);
        startActivity(intent);
    }

    private void start_sign_out() {
        Intent intent = new Intent(DeleteModuleActivity.this, loginActivity.class);
        startActivity(intent);
    }

    private void start_del_q() {
        Intent intent = new Intent(DeleteModuleActivity.this, DeleteQuestionActivity.class);
        startActivity(intent);
    }
    private void start_home() {
        Intent intent = new Intent(DeleteModuleActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }

    private void loadModules() {
        DbCommands dbCommand = DbCommands.getInstance(this);
        List<Module> modules = dbCommand.getAllModules();
        ArrayAdapter<Module> adapterModules = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, modules);
        adapterModules.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModule.setAdapter(adapterModules);
    }
}
