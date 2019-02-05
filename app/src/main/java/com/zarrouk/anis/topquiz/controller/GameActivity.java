package com.zarrouk.anis.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.zarrouk.anis.topquiz.R;
import com.zarrouk.anis.topquiz.model.Question;
import com.zarrouk.anis.topquiz.model.QuestionBank;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView mQuestionText;
    private Button mAnswer1Btn, mAnswer2Btn, mAnswer3Btn, mAnswer4Btn;
    private QuestionBank mQuestionBank;
    private Question  mCurrentQuestion;
    private  int mNumberofQuestions;
    private int mScore;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE ";
    private boolean mEnableTouchEvents;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mEnableTouchEvents = true;
        mNumberofQuestions = 4;
        mQuestionBank =  this.generateQuestions();
        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswer1Btn = (Button) findViewById(R.id.activity_game_answer1_btn);
        mAnswer2Btn = (Button) findViewById(R.id.activity_game_answer2_btn);
        mAnswer3Btn = (Button) findViewById(R.id.activity_game_answer3_btn);
        mAnswer4Btn = (Button) findViewById(R.id.activity_game_answer4_btn);

        mAnswer1Btn.setTag(0);
        mAnswer2Btn.setTag(1);
        mAnswer3Btn.setTag(2);
        mAnswer4Btn.setTag(3);

        mAnswer1Btn.setOnClickListener(this);
        mAnswer2Btn.setOnClickListener(this);
        mAnswer3Btn.setOnClickListener(this);
        mAnswer4Btn.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);



    }

    private void displayQuestion(final Question question){
        mQuestionText.setText(question.getQuestion());
        mAnswer1Btn.setText(question.getChoiceList().get(0));
        mAnswer2Btn.setText(question.getChoiceList().get(1));
        mAnswer3Btn.setText(question.getChoiceList().get(2));
        mAnswer4Btn.setText(question.getChoiceList().get(3));
    }
    private QuestionBank generateQuestions() {
        Question question1 = new Question("Who is the creator of Android?", Arrays.asList("Andy Rubin",
                                                                                          "Steve Wozinak",
                                                                                          "Jake Wharton",
                                                                                          "Paul Smith"),
                                          0);
        Question question2 = new Question("When did the first man land on the moon?", Arrays.asList("1958",
                                                                                                    "1962",
                                                                                                    "1967",
                                                                                                    "1969"),
                                          3);
        Question question3 = new Question("What is the house number of The Simpsons?",
                                          Arrays.asList("42",
                                                        "101",
                                                        "666",
                                                        "742"),
                                          3);

        return new QuestionBank(Arrays.asList(question1,
                                              question2,
                                              question3));


    }


    @Override
    public void onClick(View v) {
        int responseIndex = (int)v.getTag();
        if(responseIndex == mCurrentQuestion.getAnswerIndex()){
            Toast.makeText(this, "Correct!",Toast.LENGTH_SHORT).show();
            mScore++;
        }else{
            Toast.makeText(this, "Wrong!",Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if(--mNumberofQuestions == 0){
                    endGame();
                }else{
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000);



    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }
    private void endGame(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this) ;
        builder.setTitle("Well done !")
                .setMessage("Your score is "+ mScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                           Intent intent = new Intent();
                           intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                           setResult(RESULT_OK, intent);
                           finish();
                    }
                })
                .create()
                .show();
    }


}
