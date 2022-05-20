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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class InsertQuestionActivity extends AppCompatActivity{

    //Declare variables

    private EditText question;
    private EditText answerNr1;
    private EditText answerNr2;
    private EditText answerNr3;
    private Spinner spinnerAnswers;
    private Spinner spinnerDifficulty;
    private Spinner spinnerModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_question);

        //Initialize variables

        question = findViewById(R.id.edit_text_question);
        answerNr1 = findViewById(R.id.edit_text_option1);
        answerNr2 = findViewById(R.id.edit_text_option2);
        answerNr3 = findViewById(R.id.edit_text_option3);
        spinnerAnswers = findViewById(R.id.spinner_answerNr);
        spinnerDifficulty = findViewById(R.id.spinner_difficulty);
        spinnerModule = findViewById(R.id.spinner_module);

        loadAnswers();
        loadDifficultyLevels();
        loadModules();

        Button buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuestion();
            }
        });
    }
    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_menu_addq,menu);
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
        Intent intent = new Intent(InsertQuestionActivity.this, InsertModuleActivity.class);
        startActivity(intent);
    }

    private void start_home() {
        Intent intent = new Intent(InsertQuestionActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }
    private void loadAnswers() {
        List<Integer> answers = new ArrayList<Integer>();
        answers.add(1);
        answers.add(2);
        answers.add(3);

        ArrayAdapter<Integer> adapterAnswers = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, answers);
        adapterAnswers.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnswers.setAdapter(adapterAnswers);
    }

    private void loadModules() {
        DbCommands dbcommand = DbCommands.getInstance(this);
        List<Module> categories = dbcommand.getAllModules();

        ArrayAdapter<Module> adapterModules = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categories);
        adapterModules.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerModule.setAdapter(adapterModules);
    }

    private void loadDifficultyLevels() {
        String[] difficultyLevels = Question.getAllDifficultyLevels();

        ArrayAdapter<String> adapterDifficulty = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, difficultyLevels);
        adapterDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDifficulty.setAdapter(adapterDifficulty);
    }

    private void addQuestion() {
        String questionString = question.getText().toString();
        String answerNr1String = answerNr1.getText().toString();
        String answerNr2String = answerNr2.getText().toString();
        String answerNr3String = answerNr3.getText().toString();
        int correctAnswer = (int) spinnerAnswers.getSelectedItem();
        String difficulty = (String) spinnerDifficulty.getSelectedItem();
        Module selectedModule = (Module) spinnerModule.getSelectedItem();
        int moduleID = selectedModule.getId();

        boolean filled = true;
        if (!checkLength(questionString))
            filled = false;
        if (!checkLength(answerNr1String))
            filled = false;
        if (!checkLength(answerNr2String))
            filled = false;
        if (!checkLength(answerNr3String))
            filled = false;

        if (filled) {
            Question newQuestion = new Question(questionString, answerNr1String, answerNr2String, answerNr3String, correctAnswer, difficulty, moduleID);
            DbCommands dbcommand = DbCommands.getInstance(this);
            dbcommand.addQuestion(newQuestion);

            clearFields();

            Toast.makeText(this, "Question added!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        question.setText("");
        answerNr1.setText("");
        answerNr2.setText("");
        answerNr3.setText("");
    }
    private void start_main() {
        Intent intent = new Intent(InsertQuestionActivity.this, StartingScreenActivity.class);
        startActivity(intent);
    }
    private boolean checkLength(String string) {
        if (string.length() > 0)
            return true;
        else
            return false;
    }
}
