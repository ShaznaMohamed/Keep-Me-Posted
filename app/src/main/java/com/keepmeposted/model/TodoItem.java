package com.keepmeposted.model;

/**
 * Created by VISH_V on 9/16/2016.
 */

public class TodoItem {

    private String itemName;
    private String itemId;
    private String itemCategory;
    private long alarmDatetime = 0;
    private String itemDescription;
    private int uniqueID = 1;


    public TodoItem() {
    }

    public TodoItem(String itemName, String itemCategory) {
        this.itemName = itemName;
        this.itemCategory = itemCategory;
    }

    public TodoItem(String itemName, String itemId, String itemCategory) {
        this.itemName = itemName;
        this.itemId = itemId;
        this.itemCategory = itemCategory;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public long  getAlarmDatetime() {
        return alarmDatetime;
    }

    public void setAlarmDatetime(long alarmDatetime) {
        this.alarmDatetime = alarmDatetime;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }
}
