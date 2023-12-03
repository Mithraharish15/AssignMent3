package com.example.fragmentexampleweek4;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.List;

public class Fourth_Fragment extends Fragment {
    private List<Question_Bank> questionList;
    private TextView t4;
    private int currentQuestionIndex = 3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        Answer answer=new Answer();
        questionList = answer.getAnswer();
        View view = inflater.inflate(R.layout.fragment_layoutfour, container, false);
        TextView t4=view.findViewById(R.id.text_view_fragment4);
        if (questionList != null && !questionList.isEmpty() && currentQuestionIndex < questionList.size()) {
            t4.setText(questionList.get(currentQuestionIndex).getQuestionText());
        }
        return  view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
