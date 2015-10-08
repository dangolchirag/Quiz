package com.game.quiz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by inficare on 10/7/15.
 */
public class InfoManager {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private String name = "INFO";
    private int mode = 0;

    public static String USER_NAME = "USER_NAME";
    public static String USER_PHONE = "USER_PHONE";
    public static String USER_EMAIL = "USER_EMAIL";

    public InfoManager(Context context) {
        pref = context.getSharedPreferences(name,mode);
        editor = pref.edit();
        editor.commit();
    }

    public void saveInfo(UserInfo uInfo){
        editor.putString(USER_NAME,uInfo.getName());
        editor.putString(USER_PHONE,uInfo.getPhone());
        editor.putString(USER_EMAIL,uInfo.getEmail());
        editor.commit();
    }

    public UserInfo getUserInfo(){
        return new UserInfo(pref.getString(USER_NAME,""),pref.getString(USER_PHONE,""),pref.getString(USER_EMAIL,""));
    }

    public void clearUserInfo(){
        editor.clear();
        editor.commit();
    }
}
