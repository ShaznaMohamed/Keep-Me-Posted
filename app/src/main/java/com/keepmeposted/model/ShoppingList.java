package com.keepmeposted.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dishan on 10/8/2016.
 */

// [START post_class]
@IgnoreExtraProperties
public class ShoppingList {

    private  String mUID,mTitle,mCategory,mUser;

    public ShoppingList() {}

    public ShoppingList(String mUID,String mTitle,String mCategory,String mUser){
        this.mUID = mUID;
        this.mTitle = mTitle;
        this.mCategory = mCategory;
        this.mUser = mUser;
    }

    public String getmUID() {
        return mUID;
    }

    public void setmUID(String mUID) {
        this.mUID = mUID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmCategory() {
        return mCategory;
    }

    public void setmCategory(String mCategory) {
        this.mCategory = mCategory;
    }

    public String getmUser() {
        return mUser;
    }

    public void setmUser(String mUser) {
        this.mUser = mUser;
    }
}
