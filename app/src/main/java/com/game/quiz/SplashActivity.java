package com.game.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity {
    private QuestionsManager qMgr;
    private ArrayList<QuestionInfo> qInfo;
    private String[] questions = {"What is Australia's current population?",""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        qMgr = new QuestionsManager(SplashActivity.this);
        qInfo = qMgr.getQuestions();

        if (qInfo.size() == 0){
            qInfo = new ArrayList<>();
            qInfo.add(new QuestionInfo("What is Australia's current population?","Approx 17 million","Approx 54 million","Approx 24 million","Approx 22 million","Approx 17 million"));
            qInfo.add(new QuestionInfo("What is Australia's Capital City?","Parth","Melbourn","Canberra","Sydney","Canberra"));
            qInfo.add(new QuestionInfo("When was the Commonwealth of Australia founded?","1789","1788","1787","1786","1788"));
            qInfo.add(new QuestionInfo("In which colony/colonies were gold discovered in 1851?","New South Wales and Victoria","Northern Territory","New South Wales","Victoria","Northern Territory"));
            qInfo.add(new QuestionInfo("When is Australia Day celebrated?","25 April","26 January","26 August","26 April","26 January"));
            qInfo.add(new QuestionInfo("What is Australia's system of government?","constitutional democracy","Federal republic","presidential republic","parliamentary democracy","parliamentary democracy"));
            qInfo.add(new QuestionInfo("Who was the first governor of the colony New South Wales?","Captain Samuel Cook","Captain Philip Arthur","George W. Bush", "Arthur Philip","Captain Arthur Philip"));
            qInfo.add(new QuestionInfo("What is the Capital City of Northern Territory?","Cairns","New York","Port Douglas","Darwin","Darwin"));
            qMgr.storeQuestions(qInfo);
        }






        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                            startActivity(new Intent(SplashActivity.this,InfoActivity.class));
                        }
                    });
                }

            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_splash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
