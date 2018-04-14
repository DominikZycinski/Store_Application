package com.example.domin.aplikacjasklep;

import java.io.Serializable;

/**
 * Created by Asus1 on 2018-02-13.
 */

public class BasketRowItem implements Serializable {



        private String Name;
        private double price;
        private int quantity;

        public BasketRowItem(String Name, int quantity,double price) {
            this.Name= Name;
            this.price = price*quantity;
            this.quantity=quantity;
        }





        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString()  {
            return this.Name+" (ilosc: "+ this.quantity+")";
        }
    }



