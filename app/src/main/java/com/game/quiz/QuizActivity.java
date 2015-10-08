package com.game.quiz;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuizActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ViewPager pager;
    private ArrayList<QuestionInfo> qInfos;
    private QuestionsManager qMgr;
    private TextView index;
    private TextView error;
    private Boolean enable = true;
    private HashMap<Integer,String> answers;
    private String answer="don't Know.";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        UserInfo info = new InfoManager(QuizActivity.this).getUserInfo();
        pager = (ViewPager) findViewById(R.id.pager);
        index = (TextView) findViewById(R.id.index);
        pager.setOnPageChangeListener(this);
        answers = new HashMap<Integer,String>() ;

        qMgr = new QuestionsManager(QuizActivity.this);
        qInfos = qMgr.getQuestions();
        pager.setAdapter(new CustomPagerAdapter());


    }


    @Override
    public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

        if ( (position+1) ==  qInfos.size()){
            index.setText("tap to see result");
            index.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answers.put((position),answer);
                    Intent i = new Intent(QuizActivity.this,Result.class);
                    i.putExtra("answers",answers);
                    finish();
                    startActivity(i);
                }
            });
        }else{
            index.setOnClickListener(null);
            index.setText("Question No. "+ (position+1));
        }



    }
    @Override
    public void onPageSelected(int position) {

        answers.put((position -1), answer);
        //answer = "don't Know.";


    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class CustomPagerAdapter extends PagerAdapter  {
        private LayoutInflater inflater;
        @Override
        public int getCount() {
            return qInfos.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
             return view == ((RelativeLayout) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            TextView questions;
            RadioGroup opts;
            RadioButton opt1,opt2,opt3,opt4,dnt_knw;
            inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.pageradaptor, container,
                    false);
            questions = (TextView) view.findViewById(R.id.question);
            opts = (RadioGroup) view.findViewById(R.id.opts);
            opt1 = (RadioButton) view.findViewById(R.id.opt1);
            opt2 = (RadioButton) view.findViewById(R.id.opt2);
            opt3 = (RadioButton) view.findViewById(R.id.opt3);
            opt4 = (RadioButton) view.findViewById(R.id.opt4);
            dnt_knw = (RadioButton) view.findViewById(R.id.dnt_knw);
            QuestionInfo info = qInfos.get(position);
            questions.setText(info.getQuestions());
            opt1.setText(info.getOpt1());
            opt2.setText(info.getOpt2());
            opt3.setText(info.getOpt3());
            opt4.setText(info.getOpt4());
            opt1.setOnCheckedChangeListener(new CustomOnCheckedListener(info.getOpt1()));
            opt2.setOnCheckedChangeListener(new CustomOnCheckedListener(info.getOpt2()));
            opt3.setOnCheckedChangeListener(new CustomOnCheckedListener(info.getOpt3()));
            opt4.setOnCheckedChangeListener(new CustomOnCheckedListener(info.getOpt4()));
            dnt_knw.setOnCheckedChangeListener(new CustomOnCheckedListener(dnt_knw.getText().toString()));

            ((ViewPager) container).addView(view);
            return view;
        }
        @Override
        public void destroyItem(View container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        class CustomOnCheckedListener implements CompoundButton.OnCheckedChangeListener{
            String ans;
            CustomOnCheckedListener(String answer) {
                this.ans = answer;

            }



            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                answer = ans;

            }
        }


    }

}
