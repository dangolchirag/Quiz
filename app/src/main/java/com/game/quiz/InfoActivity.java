package com.game.quiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {
   private EditText name,phone,email;
    private Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view = null;
                Boolean success = true;
                String nameStr = name.getText().toString();
                String phoneStr = phone.getText().toString();
                String emailStr = email.getText().toString();

                if (nameStr.isEmpty()){
                    name.setError("Enter Name to start Quit");
                    view = name;
                    success = false;
                }else if(phoneStr.isEmpty()){
                    phone.setError("Enter Phone to start Quit");
                    view = phone;
                    success = false;
                }else if(emailStr.isEmpty()){
                    email.setError("Enter Email to start Quit");
                    view = email;
                    success = false;
                }else if(isValidEmailAddress(emailStr)){
                    email.setError("Enter Valid Phone to start Quit");
                    view = email;
                    success = false;
                }



                if (!success){
                    view.requestFocus();
                }else{
                    UserInfo info = new UserInfo(nameStr,phoneStr,emailStr);
                    new InfoManager(InfoActivity.this).saveInfo(info);
                    finish();
                    startActivity(new Intent(InfoActivity.this,QuizActivity.class));
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
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
    private boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return !m.matches();
    }
}
