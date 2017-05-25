package com.keepmeposted.model;

/**
 * Created by chamal on 9/17/2016.
 */

public class PantryListCategory {

    private String categoryName;
    private String id;

    public PantryListCategory(String categoryName, String id) {
        this.categoryName = categoryName;
        this.id = id;
    }

    public PantryListCategory() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PantryListCategory(String categoryName) {
        this.categoryName = categoryName;
    }
}
