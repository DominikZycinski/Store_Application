package com.example.domin.aplikacjasklep;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ElektronarzedziaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budowlane);

        TextView test =(TextView) findViewById(R.id.test);
        Bundle bundleObject = getIntent().getExtras();
        String te = (String) bundleObject.getSerializable("items");
     test.setText(te);
    }
}
