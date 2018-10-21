package com.example.sande.quizapp_roomdatabase;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private QuestionDatabase db;
    private List<QuestionTable> quizList;
    QuestionsDao mquestionsDao;// instance variable of interface
    private TextView question;
    private CheckBox optionOne,optionTwo,optionThree,optionFour;
    private int currentQuestionPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);


        db = QuestionDatabase.getDatabase(this);
        mquestionsDao = db.questionsDao();

        question = findViewById(R.id.tView_Question);
        optionOne = findViewById(R.id.cb1);
        optionTwo = findViewById(R.id.cb2);
        optionThree = findViewById(R.id.cb3);
        optionFour = findViewById(R.id.cb4);

        initializer(); // database is filled .... plz check
        quizList = new ArrayList<>();
        //quizList = mquestionsDao.getAllQuestions();
        //Log.i(TAG, "onCreate: " + quizList.get(0).getQuestion());

        getAsyncTask task = new getAsyncTask();
        task.execute();
        //Log.d(TAG, "onCreate: " + quizList.get(0).getQuestion());
        //question.setText(quizList.get(0).getQuestion());


    }
    public void initializer()
    {
        QuestionTable questionTable1 = new QuestionTable(1,"sandeep?","A","AB","ABC","ABCD");
        insert(questionTable1);
        QuestionTable questionTable2 = new QuestionTable(2,"chidi??","B","BB","BBC","BBCD");
        insert(questionTable2);
        QuestionTable questionTable3 = new QuestionTable(3,"cijan?","C","CB","CBC","CBCD");
        insert(questionTable3);
        QuestionTable questionTable4 = new QuestionTable(4,"yixin?","D","DB","DBC","DBCD");
        insert(questionTable4);

    }
    public void insert (QuestionTable questions)
    {
        new insertAsyncTask(mquestionsDao).execute(questions);
    }

    public class insertAsyncTask extends AsyncTask<QuestionTable,Void,Void>
    {

        private QuestionsDao mAsyncTaskDao;
        public insertAsyncTask(QuestionsDao dao) {

            mAsyncTaskDao = dao;
        }
        @Override
        protected Void doInBackground(QuestionTable... questionTables) {

            //mAsyncTaskDao.insert(questionTables[0]);
            return null;
        }
    }

    public class getAsyncTask extends AsyncTask<Void,Void,List<QuestionTable>>
    {


        @Override
        protected List<QuestionTable> doInBackground(Void... voids) {
            quizList = mquestionsDao.getAllQuestions();
            Log.i(TAG, quizList.get(0).getQuestion());
            Log.i(TAG, quizList.get(1).getQuestion());
            Log.i(TAG, quizList.get(2).getQuestion());
            Log.i(TAG, quizList.get(3).getQuestion());
            return quizList;
        }


    }

}




