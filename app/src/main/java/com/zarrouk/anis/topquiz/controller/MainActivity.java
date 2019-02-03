package com.zarrouk.anis.topquiz.controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.zarrouk.anis.topquiz.R;
import com.zarrouk.anis.topquiz.model.Question;
import com.zarrouk.anis.topquiz.model.QuestionBank;
import com.zarrouk.anis.topquiz.model.User;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button   mPlayBtn;
    private User mUser;
    private QuestionBank mQuestionBank;
    public static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && resultCode == RESULT_OK){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayBtn = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayBtn.setEnabled(false);
        mUser = new User();
        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                    mPlayBtn.setEnabled(s.toString().length()!= 0);


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                   String firstName = mNameInput.getText().toString();
                   mUser.setFirstName(firstName);
                   Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                   startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });




    }



}
