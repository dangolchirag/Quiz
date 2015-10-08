package com.game.quiz;

/**
 * Created by inficare on 10/7/15.
 */
public class QuestionInfo {
    private String questions ;
    private String opt1;
    private String opt2;
    private String opt3;
    private String opt4;
    private String def;

    public QuestionInfo(String questions, String opt1, String opt2, String opt3, String opt4, String def) {
        this.questions = questions;
        this.opt1 = opt1;
        this.opt2 = opt2;
        this.opt3 = opt3;
        this.opt4 = opt4;
        this.def = def;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public String getOpt1() {
        return opt1;
    }

    public void setOpt1(String opt1) {
        this.opt1 = opt1;
    }

    public String getOpt2() {
        return opt2;
    }

    public void setOpt2(String opt2) {
        this.opt2 = opt2;
    }

    public String getOpt3() {
        return opt3;
    }

    public void setOpt3(String opt3) {
        this.opt3 = opt3;
    }

    public String getOpt4() {
        return opt4;
    }

    public void setOpt4(String opt4) {
        this.opt4 = opt4;
    }

    public String getDef() {
        return def;
    }

    public void setDef(String def) {
        this.def = def;
    }
}
