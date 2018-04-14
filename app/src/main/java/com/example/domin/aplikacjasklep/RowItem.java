package com.example.domin.aplikacjasklep;

import java.io.Serializable;

public class RowItem implements Serializable{

    private String Name;
    private  String desc;
    // RowItem name (Without extension)
    private String imageName;
    private double price;
    private int quantity;

    public RowItem(String Name, String desc, String imageName, double price,int quantity) {
        this.Name= Name;
        this.desc = desc;
        this.imageName= imageName;
        this.price= price;
        this.quantity=quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getImageName() {
        return imageName;
    }

    public void setFlagName(String imageName) {
        this.imageName = imageName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString()  {
        return this.Name+" (Price: "+ this.price+")"+this.desc;
    }
}