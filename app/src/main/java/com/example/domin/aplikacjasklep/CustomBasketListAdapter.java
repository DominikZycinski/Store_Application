package com.example.domin.aplikacjasklep;

/**
 * Created by Asus1 on 2018-02-13.
 */

import android.content.Context;
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

import com.squareup.picasso.Picasso;

import java.util.List;
import android.content.Context;
import android.content.Intent;
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

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomBasketListAdapter extends BaseAdapter {

    private List<BasketRowItem> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomBasketListAdapter(Context aContext, List<BasketRowItem> listData) {
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
            convertView = layoutInflater.inflate(R.layout.basket_list_item, null);
            holder = new ViewHolder();

            holder.NameView = (TextView) convertView.findViewById(R.id.textView_productName);
           holder.priceView = (TextView) convertView.findViewById(R.id.textView_price);

           holder.quanity = (TextView) convertView.findViewById(R.id.textView_quantity);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final BasketRowItem basketRowItem = this.listData.get(position);
        holder.NameView.setText(basketRowItem.getName());
        holder.priceView.setText("Price: " + basketRowItem.getPrice()+"PLN");
       holder.quanity.setText(String.valueOf(basketRowItem.getQuantity()));
        //
    //    holder.quanity.setFilters(new InputFilter[]{new InputFilterMinMax(0, basketRowItem.getQuantity())});
        // if(holder.quanity.isFocusable())

        //   holder.quanity.setText("");

        // holder.quanity.setText(String.valueOf(0));










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

        TextView NameView;
        TextView priceView;

        TextView quanity;

    }

}