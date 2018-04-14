package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);//to mowi ze plik pracuje z tym plikiem xml

        //te zmienne trzymaja to co dostana z pliku xml( czyli z moich pol w ktorych cos wpisuje
        final EditText etTel = (EditText) findViewById(R.id.etTel); //tworze zmienna etAge typu edit text,final bo tylko tu sie moze jakos zmienic
        //findViewById szuka mi w pliku xml takiej zmiennej etAge,  (EditText) castuje mi to na typ EditText i zapisujemy to do zmiennej etAge
        final EditText etName = (EditText) findViewById(R.id.etName);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etNaziwsko = (EditText) findViewById(R.id.etNazwisko);
        final EditText etStreet = (EditText) findViewById(R.id.etStreet);
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        final EditText etHouse_num = (EditText) findViewById(R.id.etHouseNum);
        final EditText etPostalCode = (EditText) findViewById(R.id.etZipCode);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
     //   final EditText etCity = (EditText) findViewById(R.id.etCity);
        final Button bRegister = (Button) findViewById(R.id.bRegister);

        //jak ktos nacisnie przycisk register
        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etName.getText().toString();
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                final String surname = etNaziwsko.getText().toString();
                final String street = etStreet.getText().toString();
                final String house_num = etHouse_num.getText().toString();
                final String postal_code = etPostalCode.getText().toString();
               // final String city = etCity.getText().toString();
                final String city = etCity.getText().toString();
                final String email = etEmail.getText().toString();
                final int phone = Integer.parseInt(etTel.getText().toString());

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        //wszystko tu sie robi kiedy response jest tworzone
                        //muismy to zmieniec w JSON obiekt
                        try {
                            //dostaniemy stringa i zmienimy go w obiekt json
                            JSONObject jsonResponse = new JSONObject(response);//jak cos tu pojdzie nie tak to try and catch zlapie ten error i go wyswietli
                            boolean success = jsonResponse.getBoolean("success");//bo response nazwalismy to w pliku php

                            //jezeli rejestracja sie powiedzie
                            if(success){
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);//to wracamy do login page
                                RegisterActivity.this.startActivity(intent);//odpalamy to activty
                            }
                            else //jezeli rejestracja sie nie powiedzie
                            {
                                //stworzy allert message
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegisterRequest registerRequest = new RegisterRequest(name, username, phone, password, surname, street, house_num,postal_code,/* city,*/ city, email, responseListener); //stworzy zapytanie, uzywam tu konstruktora
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest); // dodajemy chyba zapytanie co tworzy na rekord w bazie
            }
        });

    }
}