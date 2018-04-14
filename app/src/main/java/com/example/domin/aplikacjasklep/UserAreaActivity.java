package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
   //     final EditText etAge= (EditText) findViewById(R.id.etTel);
        final TextView welcomeMessage = (TextView) findViewById(R.id.tvWelcomeMsg);

        //odbior danych ktore zostaly wczesniej wyslane
        Intent intent = getIntent();
   //     String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
      //  int age = intent.getIntExtra("age", -1);

     //   String message = name + " welcome to your user area";
       // welcomeMessage.setText(message); //ustawiam te zmienne ze ma mi wyswietlic ta wiadomosc wyzej
        etUsername.setText(username);
   //     etAge.setText(age + "" );

    }
}
