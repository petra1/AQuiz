package ch.ritter1.apps.aquiz;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



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

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }
}
