package com.example.up_011;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;

public class SignUpScreen extends AppCompatActivity {

    private TextView email;
    private TextView name;
    private TextView password;
    private TextView phone;
    private PostQuery.PostQueryJsoup postQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);
        email = findViewById(R.id.email);
        name = findViewById(R.id.fullname);
        password = findViewById(R.id.password);
        phone = findViewById(R.id.phone);
    }

    public void OnSingUp(View view) {


        if (email.getText().toString().isEmpty() || name.getText().toString().isEmpty()
            || password.getText().toString().isEmpty() || phone.getText().toString().isEmpty()){
            AlertDialogs.OpenAlertDialog(this, "Заполните все поля!");
        }
        else if (!Regex.RegexCheck(email.getText().toString())){
            AlertDialogs.OpenAlertDialog(this, "Корректно введите почту!");
        }
        else{
            Registration();
            Log.i("ilya", "all");
        }
    }

    public void OnCancel(View view) {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }

    private void Registration(){
        postQuery = new PostQuery.PostQueryJsoup("https://tdnfyrrpgignogqzmkfe.supabase.co/rest/v1/users?apikey=", "Password=" + password.getText().toString() + "&Email=" + email.getText().toString() + "&Name=" + name.getText().toString());
        postQuery.execute(new PostQuery.Inter() {
            @Override
            public void returner(String str) throws JSONException {
                Log.i("ilya", str);
            }
        });
    }

}