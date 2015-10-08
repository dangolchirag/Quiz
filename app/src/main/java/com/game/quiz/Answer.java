package com.game.quiz;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class Answer extends ActionBarActivity {
    private HashMap<Integer,String> answers;
    private QuestionsManager qMgr;
    private ArrayList<QuestionInfo> questionInfos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Bundle bundle = getIntent().getExtras();
        answers = (HashMap<Integer, String>) bundle.get("answer");
        qMgr = new QuestionsManager(Answer.this);
        questionInfos = qMgr.getQuestions();
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(new CustomAdoptor());
    }

    private class CustomAdoptor extends BaseAdapter{

        @Override
        public int getCount() {
            return questionInfos.size();
        }

        @Override
        public Object getItem(int position) {
            return questionInfos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            Holder hold;
            if (view == null){
                LayoutInflater inflater = LayoutInflater.from(Answer.this);
                view = inflater.inflate(R.layout.answer,parent,false);
                hold = new Holder();
                view.setTag(hold);

                hold.qtn = (TextView) view.findViewById(R.id.questions);
                hold.opt1 = (TextView) view.findViewById(R.id.opt1);
                hold.opt2 = (TextView) view.findViewById(R.id.opt2);
                hold.opt3 = (TextView) view.findViewById(R.id.opt3);
                hold.opt4 = (TextView) view.findViewById(R.id.opt4);
                hold.dntknw = (TextView) view.findViewById(R.id.dnt_knw);
            }else{
                hold = (Holder) view.getTag();
            }
            QuestionInfo i = questionInfos.get(position);
            hold.qtn.setText(i.getQuestions());
            hold.opt1.setText(i.getOpt1());
            hold.opt2.setText(i.getOpt2());
            hold.opt3.setText(i.getOpt3());
            hold.opt4.setText(i.getOpt4());

            check(hold.opt1, answers.get(position), i.getDef());
            check(hold.opt2, answers.get(position), i.getDef());
            check(hold.opt3, answers.get(position), i.getDef());
            check(hold.opt4, answers.get(position), i.getDef());
            check(hold.dntknw, answers.get(position), i.getDef());
            return view;
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        private void check(TextView tv, String ans,String correctAns){
            String s = tv.getText().toString();
            tv.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        if(correctAns.equals(s)) {
            tv.setBackgroundColor(getResources().getColor(R.color.primaryColor));

        }
        if(!ans.equals(correctAns)&&s.equals(ans)){
            tv.setBackgroundColor(getResources().getColor(R.color.error));
        }



        }

        class Holder{

            TextView qtn,opt1,opt2,opt3,opt4,dntknw;
        }
    }

}
