package com.keepmeposted.model;

/**
 * Created by Shazna on 10/15/2016.
 */

public class Income {

    private String incomeName;
    private double amount;
    private String id;
    private double totalincome;

    public Income() {
    }

    public Income(String incomeName, double amount, String id)
    {
        this.amount = amount;
        this.incomeName = incomeName;
        this.id = id;
    }
    public Income(double totincome)
    {
        this.totalincome = totincome;
    }

    public double getTotalincome() { return totalincome; }
    public String getIncomeName() { return  incomeName; }
    public double getAmount(){ return amount;}
    public String getId() { return id;}

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTotalincome(double totalincome1) { this.totalincome = totalincome1; }
}
