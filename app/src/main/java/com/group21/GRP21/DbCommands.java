package com.group21.GRP21;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.group21.GRP21.DbSkeleton.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class DbCommands extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "Group21_test2.db";
    private static final int DATABASE_VERSION = 1;
    private static DbCommands instance;
    private SQLiteDatabase db;
    private ArrayList<Integer> preparedQuestionList;

//    Initializing database

    DbCommands(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public static synchronized DbCommands getInstance(Context context) {
        if (instance == null) {
            instance = new DbCommands(context.getApplicationContext());
        }
        return instance;
    }

    //    creating tables for the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_MODULE_TABLE = "CREATE TABLE " +
                ModulesTable.TABLE_NAME + "( " +
                ModulesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ModulesTable.COLUMN_NAME + " TEXT " +
                ")";
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionsTable.COLUMN_DIFFICULTY + " TEXT, " +
                QuestionsTable.COLUMN_MODULE_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionsTable.COLUMN_MODULE_ID + ") REFERENCES " +
                ModulesTable.TABLE_NAME + "(" + ModulesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";
        db.execSQL("create Table users(username TEXT ,email TEXT primary key, password TEXT)");
        db.execSQL(SQL_CREATE_MODULE_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillModulesTable();
        fillQuestionsTable();
        ArrayList<Integer> preparedQuestionList;
    }

    //    On database upgrade method
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ModulesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        db.execSQL("drop Table if exists users");
        onCreate(db);
    }

    //    On database configure method
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    //    Insert data method for a user
    public Boolean insertData(String username,String email ,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("users", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }

    //    Update password method

    public Boolean updatepassword(String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("password", password);
        long result = db.update("users",  contentValues,"email = ?",new String[] {email});
        if(result==-1) return false;
        else
            return true;
    }
    //    check username exist method

    public Boolean checkusername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //    Check email exist method

    public Boolean checkuseremail(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email =?", new String[]{email});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //    Check user password method

    public Boolean checkusernamepassword(String email ,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from users where email =? and password = ? ", new String[] {email,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //    Inserting defaults values

    private void fillModulesTable() {
        Module c1 = new Module("Life Science");
        insertModule(c1);
        Module c2 = new Module("Geography");
        insertModule(c2);
        Module c3 = new Module("History");
        insertModule(c3);
        Module c4 = new Module("English");
        insertModule(c4);
    }


    //    Add module method
    public void addModule(Module module) {
        List<Module> modules = getAllModules();

        boolean exists = false;

        for (Module existingModule : modules) {
            if (module == existingModule) {
                exists = true;
            }
        }

        if (!exists) {
            db = getWritableDatabase();
            insertModule(module);
        }
    }
    public void addModules(List<Module> modules) {
        db = getWritableDatabase();
        for (Module module : modules) {
            insertModule(module);
        }
    }

    //    Insert module method
    private void insertModule(Module module) {
        ContentValues cv = new ContentValues();
        cv.put(ModulesTable.COLUMN_NAME, module.getName());
        db.insert(ModulesTable.TABLE_NAME, null, cv);
    }

    //    Default question method

    private void fillQuestionsTable() {
        //Life science questions
        Question q1 = new Question("The number of chromosomes found in a human sperm cells is ......",
                "23", "22", "46", 1,
                Question.DIFFICULTY_EASY, Module.LIFE_SCIENCE);
        insertQuestion(q1);
        Question q2 = new Question("Which of the following teeth of child (3-4 Years) is not a part of the milk teeth?",
                "Incisors", "Canines", "Molars", 3,
                Question.DIFFICULTY_MEDIUM, Module.LIFE_SCIENCE);
        insertQuestion(q2);
        Question q3 = new Question("Why it is not advisable to sleep under a tree at night?",
                "Release of carbon dioxide", "Release of less oxygen", "Release of more oxygen", 1,
                Question.DIFFICULTY_HARD, Module.LIFE_SCIENCE);
        insertQuestion(q3);
        Question q4 = new Question("What is the averages rate of the heart beats (per minute) in an adult?",
                "60", "72", "84", 2,
                Question.DIFFICULTY_EASY, Module.LIFE_SCIENCE);
        insertQuestion(q4);
        Question q5 = new Question("Where does fertilization occurs in the female?",
                "Fallopian tube", "Uterus", "Vagina", 1,
                Question.DIFFICULTY_MEDIUM, Module.LIFE_SCIENCE);
        insertQuestion(q5);
        Question q6 = new Question("Grommets are used in the treatment of … .....",
                "Deafness", "Blindness", "Middle-ear infection", 3,
                Question.DIFFICULTY_HARD, Module.LIFE_SCIENCE);
        insertQuestion(q6);
        Question q7 = new Question("The ability of the lens of the eye to change its shape when viewing an object that is near or far is called?",
                "Binocular vision", "pupillary mechanism", "refraction of light", 2,
                Question.DIFFICULTY_EASY, Module.LIFE_SCIENCE);
        insertQuestion(q7);
        Question q8 = new Question("Which ONE of the following is the visual defect that results from the   uneven curvature of the cornea? ",
                "Astigmatism", "Long-sightedness", "short-sightedness", 1,
                Question.DIFFICULTY_MEDIUM, Module.LIFE_SCIENCE);
        insertQuestion(q8);
        Question q9 = new Question("The receptor(s) involved in maintaining balance is/are the .....",
                "organ of corti only", "maculae only", "maculae and cristae only. ", 3,
                Question.DIFFICULTY_HARD, Module.LIFE_SCIENCE);
        insertQuestion(q9);

        //Geography questions
        Question q16 = new Question("A mid-latitude cyclone occurs between … north and south of the  equator",
                "5° and 25° ", "30° and 60°", "60° and 90°  ", 2,
                Question.DIFFICULTY_EASY, Module.GEOGRAPHY);
        insertQuestion(q16);
        Question q17 = new Question("A mid-latitude cyclone is steered (driven) by the ....",
                "Easterlies", "polar easterlies", "westerlies", 3,
                Question.DIFFICULTY_MEDIUM, Module.GEOGRAPHY);
        insertQuestion(q17);
        Question q18 = new Question("Why it is not advisable to sleep under a tree at night?",
                "veering", "backing", "rotating", 2,
                Question.DIFFICULTY_HARD, Module.GEOGRAPHY);
        insertQuestion(q18);
        Question q19 = new Question("Which one of the following is not a source of geographical information?",
                "Travellers account", "old maps", "ancient epics", 3,
                Question.DIFFICULTY_EASY, Module.GEOGRAPHY);
        insertQuestion(q19);
        Question q20 = new Question("Name the school of thought of Human Geography that employed the Marxian theory.",
                "Deterministic school of thought", "Radical school of thought", "Behavioural school of thought", 2,
                Question.DIFFICULTY_MEDIUM, Module.GEOGRAPHY);
        insertQuestion(q20);
        Question q21 = new Question("To which country does Ellen C. Semple belong?",
                "USA", "France", "South Africa", 1,
                Question.DIFFICULTY_HARD, Module.GEOGRAPHY);
        insertQuestion(q21);
        Question q22 = new Question("Which one of the following is not an approach in human geography?",
                "Areal differentiation", "Quantitative revolution", "Exploration and description.", 3,
                Question.DIFFICULTY_EASY, Module.GEOGRAPHY);
        insertQuestion(q22);
        Question q23 = new Question("Who was the founder of neo-determinism?",
                "Griffith Taylor", "Blache", "Zuma Siyabonga", 1,
                Question.DIFFICULTY_MEDIUM, Module.GEOGRAPHY);
        insertQuestion(q23);
        Question q24 = new Question("The two main branches of geography are",
                "Physical and Human", "Political and Historical", "Urban", 1,
                Question.DIFFICULTY_HARD, Module.GEOGRAPHY);
        insertQuestion(q24);

        //Add History questions
        Question q25 = new Question("Bombay was given to the company in .......",
                "1690", "1661", "1680", 2,
                Question.DIFFICULTY_EASY, Module.HISTORY);
        insertQuestion(q25);
        Question q26 = new Question("First census survey was held in  ....",
                "1872", "1882", "1852", 1,
                Question.DIFFICULTY_MEDIUM, Module.HISTORY);
        insertQuestion(q26);
        Question q27 = new Question("Madras, among the British population, was known as .....",
                "Fort William", "Fort Saint George", "Fort Augustus", 2,
                Question.DIFFICULTY_HARD, Module.HISTORY);
        insertQuestion(q27);
        Question q28 = new Question("Civil lines were established for the ........",
                "Clerks", "Kings", "British", 3,
                Question.DIFFICULTY_EASY, Module.HISTORY);
        insertQuestion(q28);
        Question q29 = new Question("Lottery committee was established in ",
                "1795", "1800", "1817", 3,
                Question.DIFFICULTY_MEDIUM, Module.HISTORY);
        insertQuestion(q29);
        Question q30 = new Question("To which country does Ellen C. Semple belong?",
                "USA", "France", "South Africa", 1,
                Question.DIFFICULTY_HARD, Module.HISTORY);
        insertQuestion(q30);
        Question q31 = new Question("American Civil war was started in ......",
                "1861", "1867", "1776", 1,
                Question.DIFFICULTY_EASY, Module.HISTORY);
        insertQuestion(q31);
        Question q32 = new Question("Gateway of India was made to welcome .........",
                "Calcutta was established by the British", "The port of Surat declined in the 16th century.", "There were three Presidency cities.", 3,
                Question.DIFFICULTY_MEDIUM, Module.HISTORY);
        insertQuestion(q32);
        Question q33 = new Question("For the development of which of the following cities, Lottery Committee was made is collect funds?",
                "Bombay", " Calcutta", "Madras", 2,
                Question.DIFFICULTY_HARD, Module.HISTORY);
        insertQuestion(q33);

        //English Questions
        Question q34 = new Question("Franz looked for opportunities to skip school to do what?",
                "work on mills", "Go fishing", "collect birds eggs", 3,
                Question.DIFFICULTY_EASY, Module.ENGLISH);
        insertQuestion(q34);
        Question q35 = new Question("What was Franz banking on to enter the class as he was late?",
                "M.Hamel’s teaching on the blackboard", "commotion in the class", "Hauser helping him sneak in", 2,
                Question.DIFFICULTY_MEDIUM, Module.ENGLISH);
        insertQuestion(q35);
        Question q36 = new Question("M. Hamel is introduced as a ruler-wielding teacher. This demonstrates that:",
                "he is concerned.", "he is adamant.", "he is a hard taskmaster", 3,
                Question.DIFFICULTY_HARD, Module.ENGLISH);
        insertQuestion(q36);
        Question q37 = new Question("Franz thinks- will they make them sing in German- even the pigeons? What could this mean?",
                "harsh orders will be passed", "when people are deprived of their essence even the surroundings are affected.", "the Germans will rob France of its language", 2,
                Question.DIFFICULTY_EASY, Module.ENGLISH);
        insertQuestion(q37);
        Question q38 = new Question("What does M. Hamel’s motionless posture reflect?",
                "the school is dismissed", "sense of finality", "changing order of life", 2,
                Question.DIFFICULTY_MEDIUM, Module.ENGLISH);
        insertQuestion(q38);
        Question q39 = new Question("What is the moral that the Alphonse Daudet wants to bring out?",
                "Not to put off things that one can do that day", "one should accept everything that happens", "teachers should be respected", 1,
                Question.DIFFICULTY_HARD, Module.ENGLISH);
        insertQuestion(q39);
        Question q40 = new Question("Why did Hamel blame himself?",
                "Not having taught them enough French", "Not being strict", "Giving students a holiday at times", 3,
                Question.DIFFICULTY_EASY, Module.ENGLISH);
        insertQuestion(q40);
        Question q41 = new Question("Who sat on the back bench on the last lesson?",
                "Franz", "the village people.", "Prussians", 2,
                Question.DIFFICULTY_MEDIUM, Module.ENGLISH);
        insertQuestion(q41);
        Question q42 = new Question("What was M. Hamel going to question Franz about?",
                "participles", "adjectives", "old primer", 1,
                Question.DIFFICULTY_HARD, Module.ENGLISH);
        insertQuestion(q42);

    }

    //    Add question method

    public void addQuestion(Question question) {

        List<Question> questions = getAllQuestions();

        boolean exists = false;

        for (Question existingQuestion : questions) {
            if (existingQuestion.getQuestion() == question.getQuestion()) {
                exists = true;
            }
        }

        if (!exists) {
            db = getWritableDatabase();
            insertQuestion(question);
        }
    }
    public void addQuestions(List<Question> questions) {
        db = getWritableDatabase();
        for (Question question : questions) {
            insertQuestion(question);
        }
    }

    //    Insert question method

    private void insertQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionsTable.COLUMN_DIFFICULTY, question.getDifficulty());
        cv.put(QuestionsTable.COLUMN_MODULE_ID, question.getModuleID());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    //    Retrieve module method

    public List<Module> getAllModules() {
        List<Module> moduleList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + ModulesTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Module module = new Module();
                module.setId(c.getInt(c.getColumnIndex(ModulesTable._ID)));
                module.setName(c.getString(c.getColumnIndex(ModulesTable.COLUMN_NAME)));
                moduleList.add(module);
            } while (c.moveToNext());
        }
        c.close();
        return moduleList;
    }

    //    Retrieve question method

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setModuleID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_MODULE_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
    public ArrayList<Question> getQuestions(int categoryID, String difficulty) {
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_MODULE_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.getCount() < 5)
            return getLeftQuestions(categoryID, difficulty);
        else
            return getRandomQuestions(categoryID, difficulty);
    }

    //    Get remaining question method

    private ArrayList<Question> getLeftQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_MODULE_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                question.setModuleID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_MODULE_ID)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    //    Generate random question method

    private ArrayList<Question> getRandomQuestions(int categoryID, String difficulty) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_MODULE_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        prepareQuestionList(categoryID, difficulty);

        ArrayList<Integer> questionIDs = new ArrayList<>();
        boolean notUsed;
        while (questionIDs.size() < 5) {
            notUsed = true;
            Random rand = new Random();
            int randID = preparedQuestionList.get(rand.nextInt(preparedQuestionList.size()));
            for (int ID : questionIDs) {
                if (randID == ID)
                    notUsed = false;
            }
            if (notUsed)
                questionIDs.add(randID);
        }

        if (c.moveToFirst()) {
            do {
                for (int questionID : questionIDs) {
                    int cursorID = c.getInt(c.getColumnIndex(QuestionsTable._ID));
                    if (cursorID == questionID) {
                        Question question = new Question();
                        question.setId(c.getInt(c.getColumnIndex(QuestionsTable._ID)));
                        question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                        question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                        question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                        question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                        question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                        question.setDifficulty(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_DIFFICULTY)));
                        question.setModuleID(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_MODULE_ID)));
                        questionList.add(question);
                    }
                }
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    private void prepareQuestionList(int categoryID, String difficulty) {
        db = getReadableDatabase();

        String selection = QuestionsTable.COLUMN_MODULE_ID + " = ? " +
                " AND " + QuestionsTable.COLUMN_DIFFICULTY + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), difficulty};

        Cursor c = db.query(
                QuestionsTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (preparedQuestionList.size() != 0)
            preparedQuestionList.clear();

        if (c.moveToFirst()) {
            do {
                int questionID = c.getInt(c.getColumnIndex(QuestionsTable._ID));
                preparedQuestionList.add(questionID);
            } while (c.moveToNext());
        }
    }

    public boolean deleteModule(int moduleID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int results = db.delete("modules_list", "_id = ?", new String[]{String.valueOf(moduleID)});
        if(results > 0)
            return true;
        else
            return false;
    }

    public boolean deleteQuestion(int questionID)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        int results = db.delete("questions", "_id = ?", new String[]{String.valueOf(questionID)});
        if(results > 0)
            return true;
        else
            return false;
    }
}
