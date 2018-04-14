package com.example.domin.aplikacjasklep;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Domin on 11.02.2018.
 */

public class BasketRequest extends StringRequest {

    private static final String BASKET_REQUEST_URL ="https://serwer1707480.home.pl/basket.php";//z jakiego pliku php bedziemy korzystali
    private Map<String,String> params;

    //konstruktor, chcemy aby konstruktor spytal sie o te zmienne ktore sa w nim zadeklarowane
    public BasketRequest( String product_name, String date_order, String quanity, String price,String username,  Response.Listener<String> listener ){
        // public BasketRequest(/String name_customer, String surname_customer, String name, String date_order, Response.Listener<String> listener ){
        ////wysle dane do php
        //listeren jak sie funkcja skonczy to listener ja jakos poinformuje



        super(Request.Method.POST, BASKET_REQUEST_URL, listener, null);
//tu nie ma co sie dzieje jak cos pojdzie nie tak


        params = new HashMap<>();
        //wsadamy do parametry do hashmap params
        //  params.put("name_customer", name_customer);
        //  params.put("surname_customer", surname_customer);
        params.put("product_name", product_name);
        params.put("date_order", date_order );
        // params.put("username", username );
        params.put("quanity", quanity );
        params.put("price", price );
        params.put("username", username );
        // params.put("surname", surname );
       //  params.put("street", street );
        // params.put("house_num", house_num);
        // params.put("postal_code", postal_code);
        //     params.put("city", city);
        // params.put("city", city );
        // params.put("email", email );
    }

    //get paramas zwroci parametry ktore napisalismy wyzej
    //jak zapytanie jest wykonywane
    //dodajemy te dane chyba do bazy tutaj

    public Map<String,String> getParams(){
        return params;// zwroci te parametry ktore napisalismy wyzj
    }

}