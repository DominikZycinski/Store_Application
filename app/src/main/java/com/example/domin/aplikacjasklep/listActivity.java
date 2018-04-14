package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Domin on 27.01.2018.
 */

public class listActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        setContentView(R.layout.activity_categories);


      //  final ImageButton ibBHP = (ImageButton) findViewById(R.id.ibBHP);
        final Button bDodaj =  (Button) findViewById(R.id.bDodaj);
       // final TextView tvLicznik = (TextView) findViewById(R.id.tvLicznik);

        bDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //   klik = klik +1;
            }
        });

        //    tvLicznik.setText(String.valueOf(klik));


    }
}