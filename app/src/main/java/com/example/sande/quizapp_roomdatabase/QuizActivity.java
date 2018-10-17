package com.example.sande.quizapp_roomdatabase;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private QuestionDatabase db;
    private List<QuestionTable> quizezList;
    private TextView question;
    private CheckBox optionOne,optionTwo,optionThree,optionFour;
    private int currentQuestionPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = QuestionDatabase.getDatabase(this);

        question = findViewById(R.id.tView_Question);
        optionOne = findViewById(R.id.cb1);
        optionTwo = findViewById(R.id.cb2);
        optionThree = findViewById(R.id.cb3);
        optionFour = findViewById(R.id.cb4);


        //show next question
        findViewById(R.id.btn_next).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(quizezList != null && quizezList.size() >0 && (currentQuestionPosition + 1) <quizezList.size())
                {
                    QuestionTable question1 = quizezList.get(++currentQuestionPosition);

                    question.setText(question1.getQuestion());
                    optionOne.setText(question1.getOption1());
                    optionTwo.setText(question1.getOption2());
                    optionThree.setText(question1.getOption3());
                    optionFour.setText(question1.getOption4());
                }else
                {
                    Toast.makeText(QuizActivity.this,"You reached at the end of Quiz",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //show previous question
        findViewById(R.id.btn_prev).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(quizezList != null && quizezList.size() >0 && (currentQuestionPosition - 1) >= 0)
                {
                    QuestionTable question1 = quizezList.get(--currentQuestionPosition);

                    question.setText(question1.getQuestion());
                    optionOne.setText(question1.getOption1());
                    optionTwo.setText(question1.getOption2());
                    optionThree.setText(question1.getOption3());
                    optionFour.setText(question1.getOption4());
                }else
                {
                    Toast.makeText(QuizActivity.this,"You reached at the start of Quiz",Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Fetch quizzes
        new fetchAllQuizzes().execute();
    }


    private class fetchAllQuizzes extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {

            quizezList =  db.questionsDao().getAllQuestions();

            return "Executed";
        }

        @Override
        protected void onPostExecute(String s)
        {
            if(quizezList == null || quizezList.size() == 0)
            {
                //Insert quizzes
                new insertAllQuizzes().execute();
            }else
            {
                //Set the first question on screen load

                QuestionTable question1 = quizezList.get(0);

                question.setText(question1.getQuestion());
                optionOne.setText(question1.getOption1());
                optionTwo.setText(question1.getOption2());
                optionThree.setText(question1.getOption3());
                optionFour.setText(question1.getOption4());
            }
        }

    }

    /**
     * @link
     */
    private class insertAllQuizzes extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {

            db.questionsDao().insertMultipleMovies(insertAllQuizQuestions());

            return "Executed";
        }

        @Override
        protected void onPostExecute(String s)
        {
            //Fetch inserted questions
            new fetchAllQuizzes().execute();
        }
    }

    private List<QuestionTable> insertAllQuizQuestions()
    {
        List<QuestionTable> quizQuestions = new ArrayList<>();
        for (int i =0; i<10;i++)
        {
            QuestionTable question = new QuestionTable();
            question.setQuestion("question " + (i +1 ));
            question.setOption1("option 1");
            question.setOption2("option 2");
            question.setOption3("option 3");
            question.setOption4("option 4");

            quizQuestions.add(question);

        }

        return quizQuestions;
    }

    }



