package com.example.fragmentexampleweek4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button TrueButt, FalseButt;
    ProgressBar progbarButt;
    private First_Fragment firstFragment;
    private int currentQuestionIndex = 0;
    private List<Question_Bank> questionList;
    private  int score=0;
     private int totalnumber=0;
     private int totalAttempts=0;
     private int totalquestionAnswered=0;
  //  private int totalquestion=questionList.size();
    //private int questionsAnswered = 0;
  private static final String STATE_CURRENT_QUESTION_INDEX = "current_question_index";
    private static final String STATE_PROGRESS = "progress";

    private static final String STATE_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TrueButt = findViewById(R.id.true_button);
        FalseButt = findViewById(R.id.false_button);
        progbarButt = findViewById(R.id.progress_bar);

        TrueButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        FalseButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // Initialize questionList
        Answer answer = new Answer();
        questionList = answer.getAnswer();

        // Show the first fragment
        //   showFirstFragment();
    /*    if (savedInstanceState != null) {
            currentQuestionIndex = savedInstanceState.getInt(STATE_CURRENT_QUESTION_INDEX);
            int savedProgress = savedInstanceState.getInt(STATE_PROGRESS, 0);
          //  score = savedInstanceState.getInt(STATE_SCORE);
            // Restore the quiz state using the saved data
            showFirstFragment();
            for (int i = 0; i < currentQuestionIndex; i++) {
                checkAnswer(questionList.get(i).isAnswerTrue());
                progbarButt.setProgress(savedProgress);
            }
        } else { */
        // If savedInstanceState is null, it's a new instance of the activity
        showFirstFragment();
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_get_average) {
            // Get the average of attempts and display a report
            getAverageAttempts();
            return true;
        } else if (id == R.id.action_select_questions) {
            // Allow the user to select the number of questions (bonus mark)
            selectNumberOfQuestions();
            return true;
        } else if (id == R.id.action_reset_results) {
            // Reset saved results
            resetSavedResults();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getAverageAttempts() {
        String fileContents = FileManager.readFromFile(MainActivity.this, "correct_answers.txt");
        if (fileContents != null) {
            // Display the average content obtained from the file in an AlertDialog
            showAverageAttemptsDialog(fileContents);
        } else {
            // Show a message if there's no content or an error reading from the file
            Toast.makeText(MainActivity.this, "Unable to retrieve average attempts", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAverageAttemptsDialog(String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Average Attempts");
        builder.setMessage(content);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void selectNumberOfQuestions() {
        // Implement logic to allow the user to select the number of questions
        // This could involve a dialog or settings screen to input the desired number
        // ...
    }

    private void resetSavedResults() {
        // Implement logic to reset saved results in the file system
        // This might involve deleting the file or clearing stored data
        // ...
    }


    private void showFirstFragment() {
        if (questionList != null && !questionList.isEmpty()&& currentQuestionIndex<questionList.size()) {
            firstFragment = First_Fragment.newInstance(questionList.get(currentQuestionIndex));
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, firstFragment)
                    .commit();
        } else {
            showLastAttemptResults();
        }
    }
    private void checkAnswer(boolean userAnswer) {
        if (firstFragment != null) {
            boolean correctAnswer = questionList.get(currentQuestionIndex).isAnswerTrue();

            if (userAnswer == correctAnswer) {
                Toast.makeText(MainActivity.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                // Update progress bar
                score++;
                updateProgressBar();

                // Proceed to the next question if available
             //   questionsAnswered++;
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    firstFragment.updateQuestion(questionList.get(currentQuestionIndex));
                } else {
                    totalAttempts++;
                    totalnumber+=score;
                    totalquestionAnswered+=questionList.size();
                    // Handle end of questions or quiz completion
                  //  showScoreDialogAndRestartQuiz();
                    showLastAttemptResults();
                }
            } else {
                Toast.makeText(MainActivity.this, "Incorrect Answer!", Toast.LENGTH_SHORT).show();
                // Handle incorrect answer logic if needed
                updateProgressBar();
                // Proceed to the next question if available
             //   questionsAnswered++;
                currentQuestionIndex++;
                if (currentQuestionIndex < questionList.size()) {
                    firstFragment.updateQuestion(questionList.get(currentQuestionIndex));
                } else {
                    // Handle end of questions or quiz completion
                    //                   // showScoreDialogAndRestartQuiz();
                    totalAttempts++;
                    totalnumber+=score;
                    totalquestionAnswered+=questionList.size();
                    showLastAttemptResults();
        }
        }
        }
        }



    private void updateProgressBar() {
      int totalQuestions = questionList.size();
       int answeredQuestions = currentQuestionIndex + 1; // Adding 1 to index since it starts from 0
        int progress = (answeredQuestions * 100) / totalQuestions;
        progbarButt.setProgress(progress);
        }
  /*  @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the current state of the quiz
        outState.putInt(STATE_CURRENT_QUESTION_INDEX, currentQuestionIndex);
        outState.putInt(STATE_PROGRESS, progbarButt.getProgress());
       // outState.putInt(STATE_SCORE, score);
        // Add other necessary data to be saved
    } */
    private void showLastAttemptResults() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Last Attempt Results");
        builder.setMessage("Your last score: " + score + "/" + questionList.size());

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //   saveResults = true;
                // Implement logic to save the results
                boolean saved = FileManager.saveToFile(MainActivity.this, "correct_answers.txt",
                        "Number of correct answers: " + score+"\n"+"Average Number of  Answer is"+totalnumber+"/"+ totalquestionAnswered+"in"+totalAttempts);
                if (saved) {
                    Toast.makeText(MainActivity.this, "Results saved to internal storage", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Failed to save results", Toast.LENGTH_SHORT).show();
                }
                // ...
                restartQuiz();
            }
        });

        builder.setNegativeButton("Ignore", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
             //   saveResults = false;
                // Implement logic to ignore the results
                // ...
                restartQuiz();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void restartQuiz() {
        currentQuestionIndex = 0;
        score = 0;
        updateProgressBar();
       // HHHH H shuffleQuestions();
        showFirstFragment();

    }

}
