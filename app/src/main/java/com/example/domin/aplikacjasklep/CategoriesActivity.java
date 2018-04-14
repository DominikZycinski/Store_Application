package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {
    static int klik = 0;
    private ListView listView;
    List<RowItem> item_details;
    List<RowItem> item_details1;
    private String TAG = BasketActivity.class.getSimpleName();
    //   List<RowItem> list = new ArrayList<RowItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);


        final ImageButton ibBHP = (ImageButton) findViewById(R.id.ibBHP);
        final ImageButton ibBudowlane = (ImageButton) findViewById(R.id.ibBudowlane);
        final ImageButton ibElektronarzedzia = (ImageButton) findViewById(R.id.ibElektronarzedzia);
        final ImageButton ibElektryczne = (ImageButton) findViewById(R.id.ibElektryczne);
        final ImageButton ibHydrauliczne = (ImageButton) findViewById(R.id.ibHydrauliczne);
        final ImageButton ibKlucze = (ImageButton) findViewById(R.id.ibKlucze);
        final ImageButton ibNasadowe = (ImageButton) findViewById(R.id.ibKluczeNasadowe);
        final ImageButton ibLaczenie = (ImageButton) findViewById(R.id.ibLaczenie);
        final ImageButton ibMotoryzacja = (ImageButton) findViewById(R.id.ibMotoryzacja);
        final ImageButton ibOgrodowe = (ImageButton) findViewById(R.id.ibOgrodowe);
        final ImageButton ibPneumatyczne = (ImageButton) findViewById(R.id.ibPneumatyczne);
        final ImageButton ibPomiarowe = (ImageButton) findViewById(R.id.ibPomiarowe);
        final ImageButton ibSzczypce = (ImageButton) findViewById(R.id.ibSzczypce);
        final ImageButton ibScierne = (ImageButton) findViewById(R.id.ibScierne);
        final ImageButton ibTransport = (ImageButton) findViewById(R.id.ibTransport);
        final ImageButton ibWkretaki = (ImageButton) findViewById(R.id.ibWkretaki);
        // final TextView tvLicznik = (TextView) findViewById(R.id.tvLicznik);

        item_details = new ArrayList<>();

        // getData("BHP");
        ibBHP.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {



                new GetContacts("BHP").execute();

                // budowlaneIntent.get
            }

        });

        ibBudowlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new GetContacts("Budowlane").execute();
            }
        });

        ibElektronarzedzia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Elektronarzedzia").execute();
            }
        });

        ibElektryczne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Elektryczne").execute();
            }
        });

        ibHydrauliczne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Hydrauliczne").execute();
            }
        });

        ibKlucze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Klucze").execute();
            }
        });

        ibNasadowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Nasadowe").execute();
            }
        });

        ibLaczenie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Laczenia").execute();
            }
        });

        ibMotoryzacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Motoryzacja").execute();
            }
        });

        ibOgrodowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Ogrodowe").execute();
            }
        });

        ibPneumatyczne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Pneumatyczne").execute();
            }
        });

        ibPomiarowe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Pomiarowe").execute();
            }
        });

        ibSzczypce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Szczypce").execute();
            }
        });

        ibScierne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Scierne").execute();
            }
        });


        ibTransport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Transport").execute();
            }
        });

        ibWkretaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Wkretaki").execute();
            }
        });
    }

    public class GetContacts extends AsyncTask<Void, Void, Void> {
        String category;
        GetContacts(String s)
        {
            category=s;

        }
        @Override

        protected void onPreExecute() {
            super.onPreExecute();
       //     Toast.makeText(CategoriesActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
            //  Bundle bundleObject = getIntent().getExtras();

        }


        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://serwer1707480.home.pl/view"+category+".php";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    //  JSONObject jsonObj = new JSONObject(jsonStr);
                    JSONArray product = new JSONArray(jsonStr);


        item_details.clear();                    // looping through All Contacts
                    for (int i = 0; i < product.length(); i++) {
                        JSONObject c = product.getJSONObject(i);
                        String code = c.getString("code");
                        String name = c.getString("name");

                        double price = c.getDouble("price");
                        String description = c.getString("description");
                        String image = c.getString("image");
                        int quantity = c.getInt("quantity");
                        item_details.add(new RowItem(name,description,image,price,quantity));
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
        }
      /*  private RowItem getRowItem(String n, String desc, String itN, double p, int qunatity) {
        //    List<RowItem> list = new ArrayList<RowItem>();
            RowItem item = new RowItem(n,desc, itN, p,qunatity);




            return item;
        }*/


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            //  listView.setAdapter(new CustomListAdapter(CategoriesActivity.this, item_details));
            Intent budowlaneIntent = new Intent(CategoriesActivity.this, ItemActivity.class);
         //   getData("BHP");
            Bundle bundle = new Bundle();

            bundle.putSerializable("items", (Serializable) item_details);

            //   budowlaneIntent.putExtra("test", item_details);
            budowlaneIntent.putExtras(bundle);

            CategoriesActivity.this.startActivity(budowlaneIntent);

        }

    }


        }

