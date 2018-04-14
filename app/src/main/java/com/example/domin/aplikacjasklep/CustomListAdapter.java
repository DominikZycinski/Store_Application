package com.example.domin.aplikacjasklep;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomListAdapter extends BaseAdapter {
////////




    ///////////////////
    private List<RowItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListAdapter(Context aContext, List<RowItem> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.itemImageView = (ImageView) convertView.findViewById(R.id.imageView_flag);
            holder.NameView = (TextView) convertView.findViewById(R.id.textView_countryName);
            holder.priceView = (TextView) convertView.findViewById(R.id.textView_price);
            holder.descView = (TextView) convertView.findViewById(R.id.textView_desc);
            holder.quanity = (EditText) convertView.findViewById(R.id.edt_month);
            holder.plus = convertView.findViewById(R.id.btnPlus);
            holder.minus = convertView.findViewById(R.id.btnMinus);
            holder.bAddBasket = convertView.findViewById(R.id.bDodaj);
            holder.date_order = convertView.findViewById(R.id.tvData);
            holder.username = convertView.findViewById(R.id.tvUsername);



            //convertView = layoutInflater.inflate(R.layout.activity_login, null);
            //holder.username = convertView.findViewById(R.id.etUsername);

            //  String nick =

            //   holder.date_order.setText();

            ///  TextView tvData = (TextView) findViewById(R.id.tvData);

            SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
            String date = sdf.format(new Date());
            System.out.println(date); //15/10/2013

            String x;

            x= date.toString();

            holder.date_order.setText(x);

            holder.username.setText(Global.Username);



            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final RowItem rowItem = this.listData.get(position);
        holder.NameView.setText(rowItem.getName());
        holder.priceView.setText(String.valueOf(rowItem.getPrice()));
        holder.descView.setText(rowItem.getDesc());
        //
        holder.quanity.setFilters(new InputFilter[]{new InputFilterMinMax(0, rowItem.getQuantity())});
        // if(holder.quanity.isFocusable())
        //   holder.quanity.setText("");

        // holder.quanity.setText(String.valueOf(0));

        if(rowItem.getQuantity()<1)
            holder.quanity.setText(String.valueOf(0));
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                int i = Integer.parseInt(holder.quanity.getText().toString());


                if (i < rowItem.getQuantity()) {
                    i++;
                } else
                    i = rowItem.getQuantity();

                holder.quanity.setText(String.valueOf(i));
            }


        });


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = Integer.parseInt(holder.quanity.getText().toString());
                if(rowItem.getQuantity()>0) {
                    if (i > 1) {
                        i--;
                    } else
                        i = 1;

                    holder.quanity.setText(String.valueOf(i));
                }
                else
                    holder.quanity.setText(String.valueOf(0));


            }
        });

        holder.bAddBasket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String product_name = holder.NameView.getText().toString();
                final String date_order = holder.date_order.getText().toString();
                final String price = holder.priceView.getText().toString();
                // final String username = holder.date_order.getText().toString();
                String quanity = holder.quanity.getText().toString();

              String  username = holder.username.getText().toString();
              String street = Global.Street;
             //   Global.Username = holder.toString();

                Response.Listener<String> responseListener = new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {
                        //wszystko tu sie robi kiedy response jest tworzone
                        //muismy to zmieniec w JSON obiekt
                        try {
                            //dostaniemy stringa i zmienimy go w obiekt json
                            JSONObject jsonResponse = new JSONObject(response);//jak cos tu pojdzie nie tak to try and catch zlapie ten error i go wyswietli
                            boolean success = jsonResponse.getBoolean("success");//bo response nazwalismy to w pliku php

                            //jezeli rejestracja sie powiedzie
                            if(success){
                                //  Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);//to wracamy do login page
                                //RegisterActivity.this.startActivity(intent);//odpalamy to activty
                            }
                            else //jezeli rejestracja sie nie powiedzie
                            {
                                //stworzy allert message
                                //     AlertDialog.Builder builder = new AlertDialog.Builder(CustomListAdapter.this);
                                //   builder.setMessage("Register Failed")
                                ////         .setNegativeButton("Retry", null)
                                //     .create()
                                //   .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                BasketRequest basketRequest = new BasketRequest(product_name, date_order, quanity, price, username,responseListener); //stworzy zapytanie, uzywam tu konstruktora
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(basketRequest); // dodajemy chyba zapytanie co tworzy na rekord w bazie
                //final String name = NameView.getText().toString();




            }
        });


        Picasso.with(context).load(rowItem.getImageName()).into(holder.itemImageView);


        return convertView;
    }

    // Find RowItem ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName)  {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap", pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }

    static class ViewHolder {
        ImageView itemImageView;
        TextView NameView;
        TextView priceView;
        TextView descView;
        EditText quanity;
        Button plus;
        Button minus;
        Button bAddBasket;
        TextView date_order;
        //TextView username;
        TextView username;
        TextView edt_month;
        //    EditText code;
        /*
        //final EditText etName = (EditText) findViewById(R.id.etName);
        //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        //final EditText etPassword = (EditText) findViewById(R.id.etPassword);
        final EditText etNaziwsko = (EditText) findViewById(R.id.etNazwisko);
        final EditText etStreet = (EditText) findViewById(R.id.etStreet);
        final EditText etCity = (EditText) findViewById(R.id.etCity);
        final EditText etHouse_num = (EditText) findViewById(R.id.etHouseNum);
        final EditText etPostalCode = (EditText) findViewById(R.id.etZipCode);
        final EditText etEmail = (EditText) findViewById(R.id.etEmail);
        */
    }

}