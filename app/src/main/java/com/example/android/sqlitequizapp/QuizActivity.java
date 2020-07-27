package com.example.android.sqlitequizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class QuizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button buttonConfirmNext;


    private TextView textViewQuestions;
    private TextView textViewSore;
    private TextView textViewQuestionsCount;
    private TextView textViewCountDown;
    private TextView textViewCorrect,textViewWrong;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionTotalCount;
    private Question currentQuestions;
    private boolean answerd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        setupui();
        fetchDB();
    }



    private void setupui() {
        textViewCorrect = (TextView) findViewById(R.id.txtcorrect);
        textViewWrong = (TextView) findViewById(R.id.txtwrong);
        textViewCountDown = (TextView) findViewById(R.id.txtviewtimer);
        textViewQuestionsCount = (TextView) findViewById(R.id.txtTotalQuestion);
        textViewSore = (TextView) findViewById(R.id.txtscore);
        buttonConfirmNext = (Button) findViewById(R.id.button);
        rbGroup = (RadioGroup) findViewById (R.id.radio_group);
        rb1 = (RadioButton) findViewById(R.id.radio_Button1);
        rb2 = (RadioButton) findViewById(R.id.radio_Button2);
        rb3 = (RadioButton) findViewById(R.id.radio_Button3);
        rb4 = (RadioButton) findViewById(R.id.radio_Button4);
    }

    private void fetchDB() {
        QuizDbHelper dbHelper = new QuizDbHelper(this);
        questionList = dbHelper.getAllQuestions();
        startquiz();
    }

    private void startquiz() {
        questionTotalCount = questionList.size();
        Collections.shuffle(questionList);

        showQuestions();

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answerd){
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked()){
                        quizOperation();

                    }else {
                        Toast.makeText(QuizActivity.this,"Please Select Atleast One Option", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOperation() {

        answerd = true;

        RadioButton rbselected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbselected) + 1;

        checkSolution(answerNr, rbselected);

    }

    private void checkSolution(int answerNr, RadioButton rbselected) {

        switch (currentQuestions.getAnswerNr() == answerNr){

            rb1.setBackground(ContextCompat.getDrawable(this,R.drawable.when_answer_correct));
        }


    }

    private void showQuestions()
    {
     rbGroup.clearCheck();
     if (questionCounter < questionTotalCount){

         currentQuestions = questionList.get(questionCounter);
         textViewQuestions.setText(currentQuestions.getQuestion());
         rb1.setText(currentQuestions.getOption1());
         rb2.setText(currentQuestions.getOption2());
         rb3.setText(currentQuestions.getOption3());
         rb4.setText(currentQuestions.getOption4());

         questionCounter++;
         answerd = false;

     }
    }
}
