package com.keepmeposted.model;

/**
 * Created by Shazna on 10/15/2016.
 */

public class Expense {

    private String expenseName;
    private double expenseamount;
    private String expenseid;
    private double totalexpense;

    public Expense() {
    }

    public Expense(String expenseincomeName, double expenseamount, String expenseid)
    {
        this.expenseamount = expenseamount;
        this.expenseName = expenseincomeName;
        this.expenseid = expenseid;
    }

    public Expense(double totalexpense)
    {
        this.totalexpense = totalexpense;
    }

    public double getTotalexpense() { return totalexpense;}
    public String getexpenseName() { return  expenseName; }
    public double getexpenseAmount(){ return expenseamount;}
    public String getexpenseId() { return expenseid;}

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

//    public String getAllinString() {
//        return expenseName + "\', status='" + status + "\', email='" + email + "\' , phone='" + phone + "\', address='" + address + "\'}";
//    }

    public String setExpenseCollection(String expenseName)
    {
        expenseName = expenseName + ", ";
        return expenseName;
    }


}
