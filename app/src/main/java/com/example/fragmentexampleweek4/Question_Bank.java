package com.example.fragmentexampleweek4;

import java.io.Serializable;

public class Question_Bank implements Serializable {
    private String questionText;
    private boolean isAnswerTrue;

    private int color;


    public String getQuestionText() {
        return questionText;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

public  int getColor()
{
    return  color;
}

    public Question_Bank(String questionText, boolean isAnswerTrue,int color) {
        this.questionText = questionText;
        this.isAnswerTrue = isAnswerTrue;
        this.color=color;


    }
}
