package com.example.fragmentexampleweek4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.Serializable;

import java.util.List;

public class First_Fragment extends Fragment {
    private TextView t1;
    private Question_Bank currentQuestion;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
         t1 = view.findViewById(R.id.text_view_fragment);
        if (currentQuestion != null) {
            t1.setText(currentQuestion.getQuestionText());
        }
        return view;
    }
        public static First_Fragment newInstance(Question_Bank question) {
            First_Fragment fragment = new First_Fragment();
            Bundle args = new Bundle();
            args.putSerializable("question", question);
            fragment.setArguments(args);
            return fragment;
        }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       if (getArguments() != null) {
            currentQuestion = (Question_Bank) getArguments().getSerializable("question");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void updateQuestion(Question_Bank question) {
        currentQuestion = question;
        if (t1 != null) {
            t1.setText(currentQuestion.getQuestionText());
        }
    }
}
