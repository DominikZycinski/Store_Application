package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    public static String name2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final Button bLogin = (Button) findViewById(R.id.bLogin);
        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);



        //czkea na klikniecie a jak user klinie to wszysko co napiszemy w tej metodzie sie stanie
        registerLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jak klikniemy to to sie dzieje
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);//co chce odpalic
                LoginActivity.this.startActivity(registerIntent);
                //stworzylismy intenta ktory otworzyl register activity,  powiedzielismy login actiwity aby odpalil intenta

            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();
                name2 = etUsername.getText().toString();
                // name = username;
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){

                                //pobrac detale detla z jsona
                                String name = jsonResponse.getString("name");//id_user
                                String phone = jsonResponse.getString("phone");
                                String street = jsonResponse.getString("street");
                              //  String city = jsonResponse.getString("city");
                                String city = jsonResponse.getString("city");//postal_code
                                String postal_code = jsonResponse.getString("postal_code");//house_num
                                String house_num = jsonResponse.getString("house_num");//Street
                                String surname = jsonResponse.getString("surname");//email
                                String password = jsonResponse.getString("password");
                                String username = jsonResponse.getString("username");
                                String email = jsonResponse.getString("email");

                                //String phone = jsonResponse.getString("phone");
                                Global.Postal_code = city;
                                Global.House_num = postal_code;
                                Global.Street = house_num;
                                Global.City = phone;
                                //Global.Username =name;
                                Global.Email = surname;
                                Global.Username = password;
                                Global.Name = street;
                                Global.Surname = username;
                                Global.Phone = email;
                                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                                intent.putExtra("name", name);
                               // intent.putExtra("city", city);
                                // intent.putExtra("username", username);
                                // intent.putExtra("phone", phone);
                                //intent.putExtra("street", street);
                                // intent.putExtra("house", house);



                                LoginActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Zły login lub hasło!")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);


            }
        });
    }


}
