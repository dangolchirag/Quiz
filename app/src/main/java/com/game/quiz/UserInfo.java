package com.game.quiz;

/**
 * Created by inficare on 10/7/15.
 */
public class UserInfo {
    private String name;
    private String phone;
    private String email;

    UserInfo(String name,String phone,String email){
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
