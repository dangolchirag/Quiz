package com.game.quiz;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class Result extends ActionBarActivity implements View.OnClickListener {
    HashMap<Integer,String> answers;
    QuestionsManager qMgr;
    ArrayList<QuestionInfo> qInfo;
    TextView score;
    Button answer , reset, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        score = (TextView) findViewById(R.id.score);
        Bundle bundle = getIntent().getExtras();
        qMgr = new QuestionsManager(Result.this);
        qInfo = qMgr.getQuestions();
        answers = (HashMap<Integer, String>) bundle.get("answers");
        score.setText(getScore()+" out of "+qInfo.size());
        answer = (Button) findViewById(R.id.ans);
        reset = (Button) findViewById(R.id.reset);
        email = (Button) findViewById(R.id.email);
        answer.setOnClickListener(this);
        reset.setOnClickListener(this);
        email.setOnClickListener(this);
    }

    private String getScore(){
        int score = 0;
       for(int i = 0;i< answers.size();i++){
           Log.i("q",qInfo.get(i).getDef());
           Log.i("ans",answers.get(i));
           if(qInfo.get(i).getDef().equals(answers.get(i))){
               score++;
           }
       }
        return ""+score;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ans:

                Intent i = new Intent(Result.this,Answer.class);
                i.putExtra("answer",answers);
                startActivity(i);
                break;
            case R.id.reset:
                finish();
                startActivity(new Intent(Result.this,InfoActivity.class));
                break;
            case R.id.email:

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                InfoManager info = new InfoManager(Result.this);
                UserInfo userInfo = info.getUserInfo();
                intent.putExtra(Intent.EXTRA_EMAIL, userInfo.getEmail());
                intent.putExtra(Intent.EXTRA_SUBJECT, "Result of "+userInfo.getName());
                intent.putExtra(Intent.EXTRA_TEXT, "getScore()+\" out of \"+qInfo.size()");

                startActivity(Intent.createChooser(intent, "Send mail..."));
                Toast.makeText(Result.this,"Email send",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
