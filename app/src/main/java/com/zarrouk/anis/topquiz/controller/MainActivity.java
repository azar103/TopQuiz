package com.zarrouk.anis.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
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
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_kEY_FIRST_NAME = "PREF_KEY_FIRST_NAME";

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        if(GAME_ACTIVITY_REQUEST_CODE == requestCode && resultCode == RESULT_OK){
            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            //stocker le score dans les preferences
            mPreferences.edit().putInt(PREF_KEY_SCORE,score).apply();
            //recuper le nom stocké
            String firstName = getPreferences(MODE_PRIVATE).getString(PREF_kEY_FIRST_NAME, null);
            greetUser();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPreferences = getPreferences(MODE_PRIVATE);
        String firstName = getPreferences(MODE_PRIVATE).getString(PREF_kEY_FIRST_NAME, null);
        int score = getPreferences(MODE_PRIVATE).getInt(PREF_KEY_SCORE, 0);


        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_txt);


        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayBtn = (Button) findViewById(R.id.activity_main_play_btn);
        mPlayBtn.setEnabled(false);
        greetUser();
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
                   //stocker le nom dans les preferences
                   mPreferences.edit().putString(PREF_kEY_FIRST_NAME,mUser.getFirstName()).apply();
                   Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                   startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);
            }
        });




    }
   private void  greetUser(){
       String firstName = getPreferences(MODE_PRIVATE).getString(PREF_kEY_FIRST_NAME, null);

       if(firstName!=null){
           int score = mPreferences.getInt(PREF_KEY_SCORE, 0);
           mGreetingText.setText("Welcome back "+firstName+
                                         "! Your best score was "+score+" will you  do better this time ?");
           mNameInput.setText(firstName);
           mNameInput.setSelection(firstName.length());
           mPlayBtn.setEnabled(true);
       }

   }


}
