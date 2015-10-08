package com.game.quiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by inficare on 10/7/15.
 */
public class QuestionsManager extends SQLiteOpenHelper {

    private static final String dbName = "questions.db";
    private static final int version = 1;
    public  static final String tableName = "question";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_QUESTION = "quest";
    public static final String COLUMN_OPT1 = "opt1";
    public static final String COLUMN_OPT2 = "opt2";
    public static final String COLUMN_OPT3 = "opt3";
    public static final String COLUMN_OPT4 = "opt4";
    public static final String COLUMN_DEFAULT = "def";
    public QuestionsManager(Context context) {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tableName + " " +
                "(" + COLUMN_ID + " INTEGER PRIMARY KEY autoincrement,"
                + COLUMN_QUESTION + " text,"
                + COLUMN_OPT1 + " text,"
                + COLUMN_OPT2 + " text,"
                + COLUMN_OPT3 + " text,"
                + COLUMN_OPT4 + " text,"
                + COLUMN_DEFAULT + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + tableName);
        onCreate(db);
    }

    public void storeQuestions(ArrayList<QuestionInfo> infos){
        for (QuestionInfo info : infos){
            SQLiteDatabase db = getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put(COLUMN_QUESTION,info.getQuestions());
            cv.put(COLUMN_OPT1,info.getOpt1());
            cv.put(COLUMN_OPT2,info.getOpt2());
            cv.put(COLUMN_OPT3,info.getOpt3());
            cv.put(COLUMN_OPT4,info.getOpt4());
            cv.put(COLUMN_DEFAULT,info.getDef());
            db.insert(tableName, null, cv);
        }
    }
    public void deleteAllQuestions(){
        getWritableDatabase().execSQL("delete from " + tableName);
    }
    public void deleteQuestion(String questionNo){
        getWritableDatabase().execSQL("delete from " + tableName +" where"+ COLUMN_ID +"=" + questionNo);
    }

    public ArrayList<QuestionInfo> getQuestions(){
        ArrayList<QuestionInfo> infos = new ArrayList<>();
        Cursor cur = getReadableDatabase().rawQuery("select * from " + tableName, null);
        cur.moveToFirst();
        while (!cur.isAfterLast()) {
            String question = cur.getString(cur.getColumnIndex(COLUMN_QUESTION));
            String opt1 = cur.getString(cur.getColumnIndex(COLUMN_OPT1));
            String opt2 = cur.getString(cur.getColumnIndex(COLUMN_OPT2));
            String opt3 = cur.getString(cur.getColumnIndex(COLUMN_OPT3));
            String opt4 = cur.getString(cur.getColumnIndex(COLUMN_OPT4));
            String def = cur.getString(cur.getColumnIndex(COLUMN_DEFAULT));
            infos.add(new QuestionInfo(question,opt1,opt2,opt3,opt4,def));
            cur.moveToNext();
        }
        cur.close();
        return  infos;
    }


}
