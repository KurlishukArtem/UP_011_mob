package com.example.up_011;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import WSRfood.up.Models.User;

public class SignInScreen extends AppCompatActivity {

    TextView email;
    TextView password;
    private GetQuery.GetQueryJsoup getQuery;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_screen);
        LoadData();

    }

    public void OnSingIn(View view) {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty() ){
            AlertDialogs.OpenAlertDialog(this, "Заполните все поля!");
        }
        else if (!Regex.RegexCheck(email.getText().toString())){
            AlertDialogs.OpenAlertDialog(this, "Корректно введите почту!");
        }
        else{
            Autharization();
        }
    }

    private void LoadData(){
        getQuery = new GetQuery.GetQueryJsoup("https://tdnfyrrpgignogqzmkfe.supabase.co/rest/v1/users?select=*&apikey=");
        getQuery.execute(new GetQuery.Inter() {
            @Override
            public void returner(String str) throws JSONException {
                Log.i("ilya", str);
                users = ParseUsers(str);
            }
        });



    }

    private void Autharization(){
        try{
            for (User i : users){
                if (i.getEmail().equals(email.getText().toString()) && i.getPassword().equals(password.getText().toString())){
                    Intent intent = new Intent(this, MainScreen.class);
                    startActivity(intent);
                    return;
                }
            }
            AlertDialogs.OpenAlertDialog(this, "Неверный email или пароль");
        }
        catch (Exception ex){
            Log.i("ilya", ex.getMessage());
        }
    }

    private List<User> ParseUsers(String str) throws JSONException {
        JSONArray jsonArray = new JSONArray(str);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Integer id = jsonObject.getInt("Id");
            String name = jsonObject.getString("Name");
            String password = jsonObject.getString("Password");
            String email = jsonObject.getString("Email");
            list.add(new User(id, email, name, password));
        }
        return list;
    }

}