package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class KategorieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategorie);


        final ImageButton ibBHP = (ImageButton) findViewById(R.id.ibBHP);


        ibBHP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent registerIntent = new Intent(KategorieActivity.this, BasketActivity.class);
                KategorieActivity.this.startActivity(registerIntent);


            }

        });

    }
}
