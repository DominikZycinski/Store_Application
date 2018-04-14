package com.example.domin.aplikacjasklep;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentDetails extends AppCompatActivity {
TextView txtID,txtAmount,txtStatus;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        txtID = (TextView) findViewById(R.id.txtID);
       // txtAmount =(TextView) findViewById(xR.id.txtAmount);
     //   txtStatus = (TextView) findViewById(R.id.txtStatus);


        Intent intent = getIntent();
       final Button bPowrot = (Button) findViewById(R.id.bPowrot);
///        final ImageButton ibKlucze = (ImageButton) findViewById(R.id.ibKlucze);

        bPowrot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PayIntent = new Intent(PaymentDetails.this, StartActivity.class);//co chce odpalic
                PaymentDetails.this.startActivity(PayIntent);
            }
        });

        try{
            JSONObject jsonObject = new JSONObject(intent.getStringExtra("PaymentDetails"));
            showDetails(jsonObject.getJSONObject("response"),intent.getStringExtra("PaymentAmount"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showDetails(JSONObject response, String paymentAmount) {


        try {
            txtID.setText((response.getString("id")));
          //  txtAmount.setText((response.getString(String.format("$%s",paymentAmount))));
            //txtStatus.setText((response.getString("state")));


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
