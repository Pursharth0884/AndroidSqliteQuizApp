package com.example.android.sqlitequizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.sqlitequizapp.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "GoQuiz";
    private static final int DATABASE_VERSION = 1;


    private SQLiteDatabase db;


    public QuizDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTION_TABLE = "CREATE TABLE " +
                 QuestionTable.TABLE_NAME +" ( "+
                 QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTION_TABLE);


        fillQuestionsTable();



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ QuestionTable.TABLE_NAME);
        onCreate(db);

    }

    public void addQuestions(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION,question.getQuestion());
        cv.put(QuestionTable.COLUMN_OPTION1,question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2,question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3,question.getOption3());
        cv.put(QuestionTable.COLUMN_OPTION4,question.getOption4());
        cv.put(QuestionTable.COLUMN_ANSWER_NR,question.getAnswerNr());

        db.insert(QuestionTable.TABLE_NAME,null,cv);
    }

    public void fillQuestionsTable(){


        Question q1 = new Question(" What Is Android ? ","OS","Drivers","Software","Hardware",1 );
        addQuestions(q1);
        Question q2 = new Question(" Which of the following is the core module of Android for Internet computing ? ","SQLite RDBMS","SQL Server RDBMS","OpenPL","None of the mentioned",1 );
        addQuestions(q2);
        Question q3 = new Question(" Which of the following Google Application can be found in Android ? ","Google Translate","Google Shopper","My Tracks","All of the mentioned",4 );
        addQuestions(q3);
        Question q4 = new Question(" Which of the following applications are processed locally on the phone ? "," Google Earth","Google Maps"," Google Voice","All of the mentioned",4 );
        addQuestions(q4);
        Question q5 = new Question(" Which of the following has not included support for Adobe Flash or Java on its platform? "," Microsoft","Apple","Google","None of the mentioned",2 );
        addQuestions(q5);
        Question q6 = new Question(" Which of the following is Apple’s cloud service for hosted email service ? ","MobileMe","MobApp","MobileCloud","None of the mentioned",1 );
        addQuestions(q6);
        Question q7 = new Question(" Which of the following programming medium is used by MobileMe to create the appearance of a desktop application inside a browser? ","HTML"," DHTML","Jquery"," All of the mentioned",2 );
        addQuestions(q7);
        Question q8 = new Question(" Which of the following is a photo and video sharing service by MobileMe ? "," Find My iPhone","iWeb Publish","MobileMe Gallery","iDisk",3 );
        addQuestions(q8);
        Question q9 = new Question(" Which of the following is Apple’s instant messaging service that interoperates with AIM ? ","iDisk","iChat","wechat","all of the mentioned",2 );
        addQuestions(q9);
        Question q10 = new Question(" Which of the following web service can be controlled by iAWSManager cloud app from an iPhone ? "," EC2","ELB","SQS","All of the mentioned",4 );
        addQuestions(q10);
    }


    public ArrayList<Question> getAllQuestions(){

        ArrayList<Question> questionList = new ArrayList<>();

        db = getReadableDatabase();

        String Projection[] = {
                QuestionTable._ID,
                QuestionTable.COLUMN_QUESTION,
                QuestionTable.COLUMN_OPTION1,
                QuestionTable.COLUMN_OPTION2,
                QuestionTable.COLUMN_OPTION3,
                QuestionTable.COLUMN_OPTION4,
                QuestionTable.COLUMN_ANSWER_NR,


        };
        Cursor c = db.query(QuestionTable.TABLE_NAME,
                Projection,
                null,
                null,
                null,
                null,
                null
        );


    if ( c.moveToFirst()){

        do {
            Question question = new Question();
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION4)));
            question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));

            questionList.add(question);

        }while (c.moveToNext());




        }
    c.close();//closing the cursor
    return questionList;
    }
}
