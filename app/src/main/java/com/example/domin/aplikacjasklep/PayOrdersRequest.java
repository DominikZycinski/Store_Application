package com.example.domin.aplikacjasklep;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Domin on 15.02.2018.
 */

public class PayOrdersRequest  extends StringRequest{
    private static final String REGISTER_REQUEST_URL ="https://serwer1707480.home.pl/PayOrdersRequest.php";//z jakiego pliku php bedziemy korzystali
    private Map<String,String> params;

    //konstruktor, chcemy aby konstruktor spytal sie o te zmienne ktore sa w nim zadeklarowane
    public PayOrdersRequest(String name, String surname, String username, String street, String house_num, String postal_code, String city, String phone, String email, String product_name, String date_order,String quanity, Response.Listener<String> listener ){
        ////wysle dane do php
        //listeren jak sie funkcja skonczy to listener ja jakos poinformuje
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
//tu nie ma co sie dzieje jak cos pojdzie nie tak


        params = new HashMap<>();
        //wsadamy do parametry do hashmap params
        params.put("name", name);
        params.put("surname", surname);
        params.put("username", username);
        params.put("street", street);
        params.put("house_num", house_num);
        params.put("postal_code", postal_code );
        params.put("city", city );
        params.put("phone", phone);
        params.put("email", email );
        params.put("product_name", product_name );
        params.put("date_order", date_order);
        params.put("quanity", quanity);

    }

    //get paramas zwroci parametry ktore napisalismy wyzej
    //jak zapytanie jest wykonywane
    //dodajemy te dane chyba do bazy tutaj
    public Map<String,String> getParams(){
        return params;// zwroci te parametry ktore napisalismy wyzj
    }

}



