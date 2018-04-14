package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {
    List<RowItem> item_details;

    private String TAG = BasketActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        item_details = new ArrayList<>();
        final Button bCategories = (Button) findViewById(R.id.bKategorie);
        final TextView loginLink = (TextView) findViewById(R.id.tvLogowanie);
     //   final ImageButton ibElektroLogo = (ImageButton) findViewById(R.id.ibElektroLogo);
        final ImageButton ibBudowlaneLogo = (ImageButton) findViewById(R.id.ibBudowlaneLogo);
        final ImageButton ibOgrodoweLogo = (ImageButton) findViewById(R.id.ibOgrodoweLogo);
        final ImageView ivMotoryzacja = (ImageView)findViewById(R.id.ivMotoryzacja);
        final ImageView ivKlucze = (ImageView)findViewById(R.id.ivKlucze);
        final ImageView ivWkretaki = (ImageView)findViewById(R.id.ivWkretaki);
        final ImageButton ibElektroLogo = (ImageButton) findViewById(R.id.ibElektroLogo);
        final TextView koszykLink = (TextView) findViewById(R.id.tvKoszyk);

                bCategories.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent categoriesIntent = new Intent(StartActivity.this, CategoriesActivity.class);
                        StartActivity.this.startActivity(categoriesIntent);
                    }
                });

        //czkea na klikniecie a jak user klinie to wszysko co napiszemy w tej metodzie sie stanie
        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //jak klikniemy to to sie dzieje
                Intent registerIntent = new Intent(StartActivity.this, LoginActivity.class);//co chce odpalic
                StartActivity.this.startActivity(registerIntent);
                //stworzylismy intenta ktory otworzyl register activity,  powiedzielismy login actiwity aby odpalil intenta

            }
        });

        /*ibElektroLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                  new GetContacts("Elektronarzedzia").execute();
            }});
*/
        ibBudowlaneLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Budowlane").execute();
            }
        });

        ibOgrodoweLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Ogrodowe").execute();
            }
        });

        ivMotoryzacja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Motoryzacja").execute();
            }
        });

        ivKlucze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Klucze").execute();
            }
        });

        ivWkretaki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Wkretaki").execute();
            }
        });

        ibElektroLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetContacts("Elektronarzedzia").execute();
            }
        });

        koszykLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent koszykIntent = new Intent(StartActivity.this, BasketActivity.class);
                StartActivity.this.startActivity(koszykIntent);
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
       //h     Toast.makeText(StartActivity.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
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
            Intent budowlaneIntent = new Intent(StartActivity.this, ItemActivity.class);
            //   getData("BHP");
            Bundle bundle = new Bundle();

            bundle.putSerializable("items", (Serializable) item_details);

            //   budowlaneIntent.putExtra("test", item_details);
            budowlaneIntent.putExtras(bundle);

            StartActivity.this.startActivity(budowlaneIntent);

        }

    }
}
