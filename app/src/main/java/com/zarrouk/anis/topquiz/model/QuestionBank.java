package com.zarrouk.anis.topquiz.model;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anis Zarrouk on 01/02/2019
 */
public class QuestionBank {
    private List<Question> mQuestionList;
    private int mNextQuestionIndex;

    public QuestionBank(List<Question> questionList) {

        mQuestionList = questionList;
        Collections.shuffle(mQuestionList);
        mNextQuestionIndex = 0;
    }

    public Question getQuestion(){
        if(mNextQuestionIndex == mQuestionList.size()){
            mNextQuestionIndex = 0;
        }

        return mQuestionList.get(mNextQuestionIndex++);
    }
}
