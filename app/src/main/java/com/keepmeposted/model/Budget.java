package com.keepmeposted.model;

/**
 * Created by Shazna on 10/15/2016.
 */

public class Budget {

    private String expenseName;
    private double expenseamount;
    private String expenseid;
    private double totalexpense;
    private String incomeName;
    private double amount;
    private String incomeid;
    private double totalincome;
    private double balance;

    public Budget() {
    }

    public Budget(String expenseincomeName, double expenseamount, String expenseid, String incomeName, double amount, String incomeid, double totalexpense, double totincome, double balance )
    {
        this.expenseamount = expenseamount;
        this.expenseName = expenseincomeName;
        this.expenseid = expenseid;
        this.amount = amount;
        this.incomeName = incomeName;
        this.incomeid = incomeid;
        this.totalincome = totincome;
        this.totalexpense = totalexpense;
        this.balance = balance;
    }


    public double getTotalexpense() { return totalexpense;}
    public String getexpenseName() { return  expenseName; }
    public double getexpenseAmount(){ return expenseamount;}
    public String getexpenseId() { return expenseid;}
    public double getTotalincome() { return totalincome; }
    public String getIncomeName() { return  incomeName; }
    public double getAmount(){ return amount;}
    public String getIncomeidId() { return incomeid;}
    public double getBalance() { return balance; }


    public void setexpenseName(String expenseincomeName) {
        this.expenseName = expenseincomeName;
    }

    public void setexpenseId(String expenseid) {
        this.expenseid = expenseid;
    }

    public void setexpenseAmount(double expenseamount) {
        this.expenseamount = expenseamount;
    }

    public void setTotalexpense(double totalexpense1) { this.totalexpense = totalexpense1;}

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public void setIncomeid(String id) {
        this.incomeid = id;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTotalincome(double totalincome1) { this.totalincome = totalincome1; }

    public void setBalance(double balance1) { this.balance = balance1; }
}
