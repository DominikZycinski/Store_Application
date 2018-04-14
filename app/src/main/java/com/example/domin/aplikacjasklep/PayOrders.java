package com.example.domin.aplikacjasklep;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;

public class PayOrders extends AppCompatActivity {

    public static final int PAYPAL_REQUEST_CODE = 7171;


    private  static PayPalConfiguration config = new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId(Config.PAYPAL_CLIENT_ID);
    Button btnPayNow;
  //  EditText edtAmount;


    double amount = 0.0;

    @Override
    protected void onDestroy() {
        stopService(new Intent(this,PayPalService.class));

        super.onDestroy();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_orders);


        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);
       amount = getIntent().getDoubleExtra("ePrice",0.0);
//amount=10.0;
        final TextView etusername = (TextView) findViewById(R.id.etUsername);
        etusername.setText(Global.Username);
        final TextView eTname = (TextView) findViewById(R.id.etName);
        eTname.setText(Global.Name);
        final TextView etsurname = (TextView) findViewById(R.id.etNazwisko);
        etsurname.setText(Global.Surname);
        final TextView ethousenum = (TextView) findViewById(R.id.etHouseNum);
        ethousenum.setText(Global.House_num);
        final TextView etstreet = (TextView) findViewById(R.id.etStreet);
        etstreet.setText(Global.Street);
        final TextView etzipcode = (TextView) findViewById(R.id.etZipCode);
        etzipcode.setText(Global.Postal_code);
        final TextView etcity = (TextView) findViewById(R.id.etCity);
        etcity.setText(Global.City);
        final TextView ettel = (TextView) findViewById(R.id.etTel);
        ettel.setText(Global.Phone);
        final TextView etemail = (TextView) findViewById(R.id.etEmail);
        etemail.setText(Global.Email);


        //final Button bZaplac = (Button) findViewById(R.id.);
        final Button bZaplac = (Button) findViewById(R.id.bZaplac);
        //jak ktos nacisnie przycisk register
        bZaplac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                proccesPayment();

                final String username = etusername.getText().toString();
                final String name = eTname.getText().toString();
                //final String password = etPassword.getText().toString();
                final String surname = etsurname.getText().toString();
                final String street = etstreet.getText().toString();
                final String house_num = ethousenum.getText().toString();
                final String postal_code = etzipcode.getText().toString();
                // final String city = etCity.getText().toString();
                final String city = etcity.getText().toString();
                final String phone = ettel.getText().toString();
                final String email = etemail.getText().toString();
                //final int phone = Integer.parseInt(etTel.getText().toString());
                //product_name, date_order, quanity,
                final String product_name = "nic";
                final String date_order = "nic";
                final String quanity = "nic";
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        //wszystko tu sie robi kiedy response jest tworzone
                        //muismy to zmieniec w JSON obiekt
                        try {
                            //dostaniemy stringa i zmienimy go w obiekt json
                            JSONObject jsonResponse = new JSONObject(response);//jak cos tu pojdzie nie tak to try and catch zlapie ten error i go wyswietli
                            boolean success = jsonResponse.getBoolean("success");//bo response nazwalismy to w pliku php

                            //jezeli rejestracja sie powiedzie
                            if (success) {
                                //Intent intent = new Intent(PayOrders.this, LoginActivity.class);//to wracamy do login page
                                //PayOrders.this.startActivity(intent);//odpalamy to activty
                            } else //jezeli rejestracja sie nie powiedzie
                            {
                                //stworzy allert message
                                AlertDialog.Builder builder = new AlertDialog.Builder(PayOrders.this);
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

                // RegisterRequest registerRequest
                PayOrdersRequest payordersRequest = new PayOrdersRequest(name, surname, username, street, house_num, postal_code, city, phone, email, product_name, date_order, quanity, responseListener); //stworzy zapytanie, uzywam tu konstruktora
                //  String name, String surname, String username, String street, String house_num, String postal_code, String city, String phone, String email, String product_name, String date_order,String quanity, Response.Listener<String> listener ){
                ////wysle dane do php
                RequestQueue queue = Volley.newRequestQueue(PayOrders.this);
                queue.add(payordersRequest); // dodajemy chyba zapytanie co tworzy na rekord w bazie
            }
        });
    }
    private void proccesPayment() {


        PayPalPayment payPalPayment = new PayPalPayment(new BigDecimal(Global.cena),"PLN","donate",PayPalPayment.PAYMENT_INTENT_SALE);

        Intent intent = new Intent(this,PaymentActivity.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION,config);
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT,payPalPayment);

        startActivityForResult(intent,PAYPAL_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == PAYPAL_REQUEST_CODE )
        {

            if(resultCode == RESULT_OK)
            {

                PaymentConfirmation confirmation = data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if(confirmation!=null)
                {

                    try{
                        String paymentDetails = confirmation.toJSONObject().toString(4);

                        startActivity(new Intent(this, PaymentDetails.class)
                                .putExtra("PaymentDetails",paymentDetails)
                                .putExtra("PaymentAmount",amount)
                        );



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }


            }
            else if(resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show();



        }
        else  if(resultCode == PaymentActivity.RESULT_EXTRAS_INVALID)
            Toast.makeText(this,"Invalid",Toast.LENGTH_SHORT).show();


    }
    }

