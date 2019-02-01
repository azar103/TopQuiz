package com.zarrouk.anis.topquiz.model;

import java.util.List;

/**
 * Created by Anis Zarrouk on 01/02/2019
 */
public class Question {
    private  String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex(answerIndex);
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public List<String> getChoiceList() {

            return mChoiceList;

    }

    public void setChoiceList(List<String> choiceList) {
        if(choiceList != null) {
            mChoiceList = choiceList;
        }
    }

    public int getAnswerIndex() {

            return mAnswerIndex;

    }

    public void setAnswerIndex(int answerIndex) {
        if(answerIndex > 0 && answerIndex <=mChoiceList.size()) {
            mAnswerIndex = answerIndex;
        }
    }

}
