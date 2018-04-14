package com.example.domin.aplikacjasklep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ItemActivity extends AppCompatActivity {

    private String TAG = ItemActivity.class.getSimpleName();
    private ListView lv;
    List<RowItem> image_details;
    ArrayList<HashMap<String, String>> contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

     //  ImageView image = (ImageView)findViewById(R.id.imageBudowlane);
       // Picasso.with(ItemActivity.this).load("http://www.katalog.egatools.com/uploads/product/min/20170926130018_6745.jpg").into(image);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv = (ListView) findViewById(R.id.listView);
        Bundle bundleObject = getIntent().getExtras();


        image_details = (List<RowItem>) bundleObject.getSerializable("items");
        lv.setAdapter(new CustomListAdapter(this, image_details));

    }




}
