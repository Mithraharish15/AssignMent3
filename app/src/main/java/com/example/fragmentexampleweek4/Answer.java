package com.example.fragmentexampleweek4;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answer { List<Question_Bank> questionList = new ArrayList<>();
    public List<Question_Bank> getAnswer() {


        // Adding questions to the list
        questionList.add(new Question_Bank("Question1:Android class is interesting", true,Color.RED));
        questionList.add(new Question_Bank("Question2:My name is Mithrah", false,Color.BLUE));
        questionList.add(new Question_Bank("Question3:Constructor is initiating the object",true,Color.GREEN));
        questionList.add(new Question_Bank("Question4:String belong to primitive datatype",false,Color.YELLOW));
        questionList.add(new Question_Bank("Question5:want to do a project?",false,Color.CYAN));

        // Add more questions as needed

        return questionList;
    }
    public void shuffleQuestions() {
       //  questionList = getQuestionList(); // Get your question list

        // Shuffle the question list
        if (questionList != null && !questionList.isEmpty()) {
            Collections.shuffle(questionList);
        }
    }
}
