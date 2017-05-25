package com.keepmeposted.model;

/**
 * Created by chamal on 9/17/2016.
 */

public class PantryListItem {

    private String categoryName;
    private String PantryItem;
    private  String id;
    private String LowOrFull;
    private String price;
    private String date;

    public PantryListItem() {
    }

    public PantryListItem(String date, String price, String lowOrFull, String id, String pantryItem, String categoryName) {
        this.date = date;
        this.price = price;
        LowOrFull = lowOrFull;
        this.id = id;
        PantryItem = pantryItem;
        this.categoryName = categoryName;
    }

    public  PantryListItem(String date, String price, String id, String pantryItem, String categoryName)
    {
        this.date = date;
        this.price = price;
        this.id = id;
        PantryItem = pantryItem;
        this.categoryName = categoryName;
    }

    public PantryListItem(String id, String pantryItem) {
        this.id = id;
        PantryItem = pantryItem;
    }

    public PantryListItem(String pantryItem) {
        PantryItem = pantryItem;
    }

    public String getCategoryName() {

        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPantryItem() {
        return PantryItem;
    }

    public void setPantryItem(String pantryItem) {
        PantryItem = pantryItem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLowOrFull() {
        return LowOrFull;
    }

    public void setLowOrFull(String lowOrFull) {
        LowOrFull = lowOrFull;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
