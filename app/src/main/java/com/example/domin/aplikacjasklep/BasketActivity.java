package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BasketActivity extends AppCompatActivity {

    private String TAG = BasketActivity.class.getSimpleName(); //
    private ListView listView;
    List<BasketRowItem> image_details;
   // List<RowItem> list = new ArrayList<RowItem>();
    double endPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_basket);
        final Button bZaplac = (Button) findViewById(R.id.btnzamow);


        bZaplac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PayIntent = new Intent(BasketActivity.this, PayOrders.class);//co chce odpalic
                BasketActivity.this.startActivity(PayIntent);
                PayIntent.putExtra("ePrice",endPrice);
            }
        });


        listView = (ListView) findViewById(R.id.listView1);

        image_details = new ArrayList<>();
         new GetContacts().execute();

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
          //  Toast.makeText(BasketActivity.this, "Json Data is downloading", Toast.LENGTH_LONG).show();
         //   Bundle bundleObject = getIntent().getExtras();
          //  image_details = (List<RowItem>) bundleObject.getSerializable("items");
        }


        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://serwer1707480.home.pl/getOrder.php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    //  JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray product = new JSONArray(jsonStr);


                    // looping through All Contacts
                    for (int i = 0; i < product.length(); i++) {
                        JSONObject c = product.getJSONObject(i);

                        String name = c.getString("name");

                        double price = c.getDouble("price");
                        endPrice+=price;
                        Global.cena += price;
              //          double price = 2.0;
                        int quantity = c.getInt("quanity");
                 //       String sqquantity = squantity ;
                      //  int quantity = 2;
                        Global.cena = Global.cena * quantity;
                        image_details.add(new BasketRowItem(name,quantity,price));
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;

      /*  private RowItem getRowItem(String n, String desc, String itN, double p, int qunatity) {
        //    List<RowItem> list = new ArrayList<RowItem>();
            RowItem item = new RowItem(n,desc, itN, p,qunatity);




            return item;
        }
*/ }

            @Override
            protected void onPostExecute (Void result){
                super.onPostExecute(result);
          listView.setAdapter(new CustomBasketListAdapter(BasketActivity.this, image_details));

            }


    }
}

