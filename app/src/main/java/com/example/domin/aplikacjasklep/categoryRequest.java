package com.example.domin.aplikacjasklep;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Asus1 on 2018-02-11.
 */

public class categoryRequest extends StringRequest {

    private static final String CATEGORY_REQUEST_URL ="https://serwer1707480.home.pl/viewBHP.php";
    private Map<String,String> params;

    //konstruktor
    public categoryRequest(String category, Response.Listener<String> listener ){
        super(Request.Method.POST, CATEGORY_REQUEST_URL, listener, null);
        params = new HashMap<>();
        //wsadamy do params;
        params.put("category", category);


    }

    //get paramas zwroci parametry ktore napisalismy wyzej

    public Map<String,String> getParams(){
        return params;
    }


}
