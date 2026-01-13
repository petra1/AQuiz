package ch.ritter1.apps.aquiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    static final String DATABASE_NAME = "questions.db";
    private static final int DATABASE_VERSION = 1;
    static final String  DATABASE_TABLE = "questions";
    static final String  COLUMN_ID = "id";
    static final String  COLUMN_QUESTION = "question";
    static final String  COLUMN_ANSWER1 = "answer1";
    static final String  COLUMN_ANSWER2 = "answer2";
    static final String  COLUMN_ANSWER3 = "answer3";
    static final String  COLUMN_ANSWER4 = "answer4";
    static final String  COLUMN_CORRECT_ANSWER_INDEX = "correct_answer_index";

    public static final String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            COLUMN_QUESTION + " TEXT, "+
            COLUMN_ANSWER1 + " TEXT, "+
            COLUMN_ANSWER2 + " TEXT, "+
            COLUMN_ANSWER3 + " TEXT, "+
            COLUMN_ANSWER4 + " TEXT, "+
            COLUMN_CORRECT_ANSWER_INDEX + " INTEGER);";

    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Was ist die Hauptstadt der Schweiz?", "Zürich", "Genf", "Bern", "Basel", 2);
        addQuestion(q1);
        Question q2 = new Question("Welcher Planet ist der grösste in unserem Sonnensystem?", "Erde", "Mars", "Jupiter", "Saturn", 2);
        addQuestion(q2);
        Question q3 = new Question("Wie viele Beine hat eine Spinne?", "6", "8", "10", "12", 1);
        addQuestion(q3);
        Question q4 = new Question("Was ist das chemische Symbol für Wasser?", "H2O", "CO2", "O2", "N2", 0);
        addQuestion(q4);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_QUESTION, question.getQuestion());
        cv.put(COLUMN_ANSWER1, question.getAnswer1());
        cv.put(COLUMN_ANSWER2, question.getAnswer2());
        cv.put(COLUMN_ANSWER3, question.getAnswer3());
        cv.put(COLUMN_ANSWER4, question.getAnswer4());
        cv.put(COLUMN_CORRECT_ANSWER_INDEX, question.getCorrectAnswerIndex());
        db.insert(DATABASE_TABLE, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + DATABASE_TABLE, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndexOrThrow(COLUMN_QUESTION)));
                question.setAnswer1(c.getString(c.getColumnIndexOrThrow(COLUMN_ANSWER1)));
                question.setAnswer2(c.getString(c.getColumnIndexOrThrow(COLUMN_ANSWER2)));
                question.setAnswer3(c.getString(c.getColumnIndexOrThrow(COLUMN_ANSWER3)));
                question.setAnswer4(c.getString(c.getColumnIndexOrThrow(COLUMN_ANSWER4)));
                question.setCorrectAnswerIndex(c.getInt(c.getColumnIndexOrThrow(COLUMN_CORRECT_ANSWER_INDEX)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
