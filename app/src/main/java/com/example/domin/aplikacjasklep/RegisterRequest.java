package com.example.domin.aplikacjasklep;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Domin on 09.01.2018.
 */
//laczymy sie zbaza danych i otrzymujemy wyniki jaki stringi
public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL ="https://serwer1707480.home.pl/register3.php";//z jakiego pliku php bedziemy korzystali
    private Map<String,String> params;

    //konstruktor, chcemy aby konstruktor spytal sie o te zmienne ktore sa w nim zadeklarowane
    public RegisterRequest(String name, String username, int phone, String password, String surname, String street , String house_num, String postal_code, /*String city,*/ String city, String email, Response.Listener<String> listener ){
        ////wysle dane do php
        //listeren jak sie funkcja skonczy to listener ja jakos poinformuje
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
//tu nie ma co sie dzieje jak cos pojdzie nie tak


        params = new HashMap<>();
        //wsadamy do parametry do hashmap params
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("phone", phone + "");
        params.put("surname", surname );
        params.put("street", street );
        params.put("house_num", house_num);
        params.put("postal_code", postal_code);
   //     params.put("city", city);
        params.put("city", city );
        params.put("email", email );
    }

    //get paramas zwroci parametry ktore napisalismy wyzej
    //jak zapytanie jest wykonywane
    //dodajemy te dane chyba do bazy tutaj
    public Map<String,String> getParams(){
        return params;// zwroci te parametry ktore napisalismy wyzj
    }

}