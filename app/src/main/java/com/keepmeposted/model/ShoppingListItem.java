package com.keepmeposted.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dishan on 10/8/2016.
 */

public class ShoppingListItem {

    private String shoppinglist,uid,title,category,unitPrice,totalprice,description,quantity;

    public ShoppingListItem() {}

    public ShoppingListItem(String shoppinglist,String uid, String title, String unitprice, String quantity, String totalprice) {
        this.shoppinglist = shoppinglist;
        this.uid = uid;
        this.title = title;
        this.unitPrice = unitprice;
        this.quantity = quantity;
        this.totalprice = totalprice;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public ShoppingListItem(String shoppinglist, String uid, String title, String category, String quantity, String unitprice, String totalprice, String description) {
        this.shoppinglist = shoppinglist;
        this.uid = uid;
        this.title = title;
        this.category = category;
        this.description = description;
        this.unitPrice = unitprice;
        this.totalprice  = totalprice;
        this.quantity = quantity;
    }

    public String getShoppinglist() {
        return shoppinglist;
    }

    public void setShoppinglist(String shoppinglist) {
        this.shoppinglist = shoppinglist;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.totalprice = totalprice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
