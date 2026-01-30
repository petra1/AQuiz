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
    private static final int DATABASE_VERSION = 2;
    static final String DATABASE_TABLE = "questions";
    static final String COLUMN_ID = "id";
    static final String COLUMN_QUESTION = "question";
    static final String COLUMN_ANSWER1 = "answer1";
    static final String COLUMN_ANSWER2 = "answer2";
    static final String COLUMN_ANSWER3 = "answer3";
    static final String COLUMN_ANSWER4 = "answer4";
    static final String COLUMN_CORRECT_ANSWER_INDEX = "correct_answer_index";

    public static final String CREATE_TABLE = "CREATE TABLE " + DATABASE_TABLE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_QUESTION + " TEXT, " +
            COLUMN_ANSWER1 + " TEXT, " +
            COLUMN_ANSWER2 + " TEXT, " +
            COLUMN_ANSWER3 + " TEXT, " +
            COLUMN_ANSWER4 + " TEXT, " +
            COLUMN_CORRECT_ANSWER_INDEX + " INTEGER);";

    private SQLiteDatabase db;
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
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
        Question q1 = new Question(
                context.getString(R.string.q1_text),
                context.getString(R.string.q1_answer1),
                context.getString(R.string.q1_answer2),
                context.getString(R.string.q1_answer3),
                context.getString(R.string.q1_answer4),
                1
        );
        addQuestion(q1);
        Question q2 = new Question(
                context.getString(R.string.q2_text),
                context.getString(R.string.q2_answer1),
                context.getString(R.string.q2_answer2),
                context.getString(R.string.q2_answer3),
                context.getString(R.string.q2_answer4),
                2
        );
        addQuestion(q2);

        Question q3 = new Question(
                context.getString(R.string.q3_text),
                context.getString(R.string.q3_answer1),
                context.getString(R.string.q3_answer2),
                context.getString(R.string.q3_answer3),
                context.getString(R.string.q3_answer4),
                2
        );
        addQuestion(q3);

        Question q4 = new Question(
                context.getString(R.string.q4_text),
                context.getString(R.string.q4_answer1),
                context.getString(R.string.q4_answer2),
                context.getString(R.string.q4_answer3),
                context.getString(R.string.q4_answer4),
                3
        );
        addQuestion(q4);
        Question q5 = new Question(
                context.getString(R.string.q5_text),
                context.getString(R.string.q5_answer1),
                context.getString(R.string.q5_answer2),
                context.getString(R.string.q5_answer3),
                context.getString(R.string.q5_answer4),
                0
        );
        addQuestion(q5);
        Question q6 = new Question(
                context.getString(R.string.q6_text),
                context.getString(R.string.q6_answer1),
                context.getString(R.string.q6_answer2),
                context.getString(R.string.q6_answer3),
                context.getString(R.string.q6_answer4),
                1
        );
        addQuestion(q6);
        Question q7 = new Question(
                context.getString(R.string.q7_text),
                context.getString(R.string.q7_answer1),
                context.getString(R.string.q7_answer2),
                context.getString(R.string.q7_answer3),
                context.getString(R.string.q7_answer4),
                2
        );
        addQuestion(q7);
        Question q8 = new Question(
                context.getString(R.string.q8_text),
                context.getString(R.string.q8_answer1),
                context.getString(R.string.q8_answer2),
                context.getString(R.string.q8_answer3),
                context.getString(R.string.q8_answer4),
                3
        );
        addQuestion(q8);


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
