package com.example.domin.aplikacjasklep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class OgrodoweActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ogrodowe);


        ImageView image = (ImageView)findViewById(R.id.imageOgrodowe);
        Picasso.with(OgrodoweActivity.this).load("http://www.katalog.egatools.com/uploads/product/min/20170926130711_6726.jpg").into(image);

    }
}
