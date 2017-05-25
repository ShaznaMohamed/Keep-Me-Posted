package com.keepmeposted.views.fragment;
//import com.firebase.client.AuthData;
//import com.firebase.client.DataSnapshot;
//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;
//import com.firebase.client.ValueEventListener;
//import com.keepmeposted.Bases.BaseActivity;
import com.keepmeposted.views.activity.BudgetExpenseActivity;
import com.keepmeposted.MainActivity;
import com.keepmeposted.views.activity.PlanBudgetActivity;
import com.keepmeposted.views.fragment.DatePickerFragment;
import android.app.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.keepmeposted.base.BaseFragment;
import com.keepmeposted.R;
import com.keepmeposted.model.ShoppingListItem;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Shazna on 9/11/2016.
 */

public class BudgetFragment extends BaseFragment {

    private RelativeLayout catChartLayout;
    private PieChart mChart, m2chart,catChartLayout2, cartChartLaoyut2, m2cartChart;
    Spinner budgetType;
    private Button datePickerdDoneButton;
    TextView text;
    private float[] yData= {150,200,150,250,350,100,300,400};
    float[] cartprice = {100, 200, 129, 329, 326, 130, 230, 299};
    //private float[] yData = {5,10,15,30,40};
    private String[] xData = {"Diary","Vegetables","Fruites","Provisions","Medicine","Beverages","Gifts","Dress"};
    boolean list,cart;
    EditText cc1, cc2,cc3,cc4, cc5, cc6, cc7, cc8;
    TextView cc1e, cc2e,cc3e,cc4e, cc5e, cc6e, cc7e, cc8e, actualtot, plantot;
    TextView cart1,cart2, cart3, cart4, cart5, cart6, cart7, cart8;
    TextView cart1e,cart2e, cart3e, cart4e, cart5e, cart6e, cart7e, cart8e;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_budget, container, false);

        TableLayout tablecheck = (TableLayout)view.findViewById(R.id.checktable);
        tablecheck.setStretchAllColumns(true);

        //designExpenseTable
        expenseTabDesigning(view,yData,cartprice);

        //View cart prices
        viewcartprice(view,cartprice);

        //Generate list chart
        final TextView catogeryTotal = (TextView) view.findViewById(R.id.catTotal);
        generatePie(view, catogeryTotal, yData);

        //Get amounts
        ShowAmounts(view,yData);

        //Draw table
        tableDesigning(view);

        //View table total
           GetTotal(view, cartprice);
        //getTempTableTotal(view);

        //View budget Table
        Button viewtable = (Button)view.findViewById(R.id.viewtable);
        viewtable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CardView tablecard = (CardView)view.findViewById(R.id.tablecard);
                tablecard.setVisibility(View.VISIBLE);
                CardView chechbxCard = (CardView)view.findViewById(R.id.checkboxcard);
                chechbxCard.setVisibility(View.VISIBLE);
                CardView shopCard = (CardView)view.findViewById(R.id.viewShop);
                shopCard.bringToFront();
                shopCard.setVisibility(View.GONE);
            }
        });

        Button btnfrom = (Button)view.findViewById(R.id.buttonfrom);
        btnfrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogWithoutDateField(v).show();
                //onDateSet(createDialogWithoutDateField(v).getDatePicker());
                onDateChanged(createDialogWithoutDateField(v).getDatePicker(),view);
              //  showDatePickerDialog(v);
              //  ((ViewGroup)new datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
            }
        });
        ;

        //Go to the plan Activity
        Button goPlan = (Button)view.findViewById(R.id.planbudget);
        goPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPlan();
            }
        });

        //Go to the plan Activity
        Button goExpense = (Button)view.findViewById(R.id.viewexpensebudg);
        goExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBudgetExpense();
            }
        });

        return  view;

    }

    private void addData(PieChart mchart){

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for(int i=0; i<yData.length; i++)
            yVals1.add(new Entry(yData[i],i));

        ArrayList<String> xVals = new ArrayList<String>();

        for(int i=0; i<xData.length; i++)
            xVals.add(xData[i]);

        //create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Categories");
        dataSet.setSliceSpace(0);
        dataSet.setSelectionShift(10);

        //add many colors
        ArrayList<Integer> colors= new ArrayList<Integer>();

        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS )
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS )
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS )
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        //instantiate pie data object
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        mchart.setData(data);

        //undo all highlights
        mchart.highlightValues(null);

        //update pie chart
        mchart.invalidate();


    }


    public float itemClicked(View v, float expense)
    {
        float cat1;
        CheckBox checkBox = (CheckBox)v;
        if(checkBox.isChecked())
        cat1 = expense;
        else
            cat1 = 0f;
        return cat1;
    }

    public void generatePie(final View v, final TextView catTotal, final float[] argss)
    {
        Button gcb = (Button)v.findViewById(R.id.generateCatPie);

        gcb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {

                CardView piecard = (CardView)v.findViewById(R.id.piecard);
                piecard.setVisibility(View.VISIBLE);
                CardView check = (CardView)v.findViewById(R.id.checkboxcard);
                check.bringToFront();


                yData[0] = itemClicked(v.findViewById(R.id.s1), argss[0]);
                yData[1] = itemClicked(v.findViewById(R.id.s2), argss[1]);
                yData[2] = itemClicked(v.findViewById(R.id.s3), argss[2]);
                yData[3] = itemClicked(v.findViewById(R.id.s4), argss[3]);
                yData[4] = itemClicked(v.findViewById(R.id.s5), argss[4]);
                yData[5] = itemClicked(v.findViewById(R.id.s6), argss[5]);
                yData[6] = itemClicked(v.findViewById(R.id.s7), argss[6]);
                yData[7] = itemClicked(v.findViewById(R.id.s8), argss[7]);


                //Display Total
                float total = yData[0] + yData[1] + yData[2] + yData[3] + yData[4] + yData[5] + yData[6] + yData[7];
                catTotal.setText("Total Rs."+total);

                //Draw pie
                catChartLayout2 = (PieChart) v.findViewById(R.id.m_piechat);
                m2chart = (PieChart)v.findViewById(R.id.m_piechat);
                m2chart = new PieChart(getContext());

                //addpie chart to catChartLayout
                catChartLayout2.addView(m2chart);
                catChartLayout2.setBackgroundColor(Color.WHITE);

                //configure pie chart
                m2chart.setUsePercentValues(true);
                m2chart.setDescription("Category wise Budget Summary");
                m2chart.setDescriptionTextSize(10);

                //enable hole and configure
                m2chart.setDrawHoleEnabled(true);
                m2chart.setHoleColor(2);
                m2chart.setHoleRadius(0);
                m2chart.setTransparentCircleRadius(50);

                //enable rotatoin of the chart by touch
                m2chart.setRotationAngle(0);
                m2chart.setRotationEnabled(true);

                //set a chart value selected listener
                m2chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                        //display msg when value selected
                        if(e==null)
                            return;
                        Toast.makeText(getContext(), xData[e.getXIndex()] + " = Rs " + e.getVal() ,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });

                //add data
                addData(m2chart);

                //customize legends
                Legend l = m2chart.getLegend();
                l.setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER.RIGHT_OF_CHART);
                l.setXEntrySpace(15);
                l.setYEntrySpace(15);


            }
        });

    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
        //newFragment.createDialogWithoutDateField(v).show();
    }

    public DatePickerDialog createDialogWithoutDateField(View v) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        //datePickerdDoneButton = (Button)findViewById.

        DatePickerDialog dpd = new DatePickerDialog(v.getContext(), null, year, month, day);


        try {
            java.lang.reflect.Field[] datePickerDialogFields = dpd.getClass().getDeclaredFields();
            for (java.lang.reflect.Field datePickerDialogField : datePickerDialogFields) {
                if (datePickerDialogField.getName().equals("mDatePicker")) {
                    datePickerDialogField.setAccessible(true);
                    DatePicker datePicker = (DatePicker) datePickerDialogField.get(dpd);

                    java.lang.reflect.Field[] datePickerFields = datePickerDialogField.getType().getDeclaredFields();
                    for (java.lang.reflect.Field datePickerField : datePickerFields) {
                        Log.i("test", datePickerField.getName());
                        if ("mDaySpinner".equals(datePickerField.getName())) {
                            datePickerField.setAccessible(true);
                            Object dayPicker = datePickerField.get(datePicker);
                            ((View) dayPicker).setVisibility(View.GONE);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
        }
        return dpd;
    }


    public void onDateChanged(DatePicker datePicker,View v) {

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth()+1;
        int year = datePicker.getYear();

//        TextView daate = (TextView)v.findViewById(R.id.textviewfrom);
//        daate.setText( month + "- " + year);
    }

    public void ShowAmounts(View v, final float[] argss) {
        cc1 = (EditText) v.findViewById(R.id.cc1);
        cc2 = (EditText) v.findViewById(R.id.cc2);
        cc3 = (EditText) v.findViewById(R.id.cc3);
        cc4 = (EditText) v.findViewById(R.id.cc4);
        cc5 = (EditText) v.findViewById(R.id.cc5);
        cc6 = (EditText) v.findViewById(R.id.cc6);
        cc7 = (EditText) v.findViewById(R.id.cc7);
        cc8 = (EditText) v.findViewById(R.id.cc8);

        cc1.setText(Float.toString(argss[0]));
        cc2.setText(Float.toString(argss[1]));
        cc3.setText(Float.toString(argss[2]));
        cc4.setText(Float.toString(argss[3]));
        cc5.setText(Float.toString(argss[4]));
        cc6.setText(Float.toString(argss[5]));
        cc7.setText(Float.toString(argss[6]));
        cc8.setText(Float.toString(argss[7]));

    }

    public void GetTotal(final View v, final float[] cartprices)
    {
        cc1 = (EditText) v.findViewById(R.id.cc1);
        cc2 = (EditText) v.findViewById(R.id.cc2);
        cc3 = (EditText) v.findViewById(R.id.cc3);
        cc4 = (EditText) v.findViewById(R.id.cc4);
        cc5 = (EditText) v.findViewById(R.id.cc5);
        cc6 = (EditText) v.findViewById(R.id.cc6);
        cc7 = (EditText) v.findViewById(R.id.cc7);
        cc8 = (EditText) v.findViewById(R.id.cc8);

        //View table total
        Button viewtotal = (Button)v.findViewById(R.id.viewtotal);
        viewtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                TextView totaltext = (TextView)v.findViewById(R.id.tabletotal);


                try {

                    float am1 = Float.parseFloat(cc1.getText().toString());
                    float am2 = Float.parseFloat(cc2.getText().toString());
                    float am3 = Float.parseFloat(cc3.getText().toString());
                    float am4 = Float.parseFloat(cc4.getText().toString());
                    float am5 = Float.parseFloat(cc5.getText().toString());
                    float am6 = Float.parseFloat(cc6.getText().toString());
                    float am7 = Float.parseFloat(cc7.getText().toString());
                    float am8 = Float.parseFloat(cc8.getText().toString());


                    float totall = am1 + am2 + am3 + am4 + am5 + am6 + am7 + am8;
                    totaltext.setText(totall+"");

                    TextView carttot = (TextView)v.findViewById(R.id.cartTotal);
                    float tot = cartprices[0]+cartprices[1]+cartprices[2]+cartprices[3]+cartprices[4]+cartprices[5]+cartprices[6]+cartprices[7];
                    carttot.setText(tot+"");
                }
                catch (Exception e)
                {

                }
            }
        });
    }

    public void getTempTableTotal(final View v)
    {
        Button viewtotal = (Button)v.findViewById(R.id.viewtotal);
        viewtotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                TextView totaltext = (TextView)v.findViewById(R.id.tabletotal);
                float tot = yData[0] + yData[1] + yData[2] + yData[3] +yData[4] +yData[5] +yData[6] +yData[7];
                totaltext.setText("Total Rs. "+tot);
            }
        });
    }

    public void tableDesigning(View v)
    {
//        TableRow row1 = (TableRow)v.findViewById(R.id.rowheader);
//        row1.setBackgroundColor(Color.BLUE);

        TableLayout tl = (TableLayout)v.findViewById(R.id.la1);
        tl.setStretchAllColumns(true);
        tl.setBackgroundColor(Color.parseColor("#F8F8FF"));
    }

    public void viewcartprice(View v, final float[] cartprices )
    {
        cart1 = (TextView)v.findViewById(R.id.cart1);
        cart2 = (TextView)v.findViewById(R.id.cart2);
        cart3 = (TextView)v.findViewById(R.id.cart3);
        cart4 = (TextView)v.findViewById(R.id.cart4);
        cart5 = (TextView)v.findViewById(R.id.cart5);
        cart6 = (TextView)v.findViewById(R.id.cart6);
        cart7 = (TextView)v.findViewById(R.id.cart7);
        cart8 = (TextView)v.findViewById(R.id.cart8);

        cart1.setText(cartprices[0]+"");
        cart2.setText(cartprices[1]+"");
        cart3.setText(cartprices[2]+"");
        cart4.setText(cartprices[3]+"");
        cart5.setText(cartprices[4]+"");
        cart6.setText(cartprices[5]+"");
        cart7.setText(cartprices[6]+"");
        cart8.setText(cartprices[7]+"");

    }

    public void goPlan(){
        Intent intent = new Intent(getActivity(),PlanBudgetActivity.class);
        startActivity(intent);
    }

    public void expenseTabDesigning(View v, final float[] argss, float[] cartprices)
    {
//        TableRow row1 = (TableRow)v.findViewById(R.id.rowheader);
//        row1.setBackgroundColor(Color.BLUE);

        TableLayout tle = (TableLayout)v.findViewById(R.id.la1e);
        tle.setStretchAllColumns(true);
        tle.setBackgroundColor(Color.parseColor("#F8F8FF"));

        cc1e = (TextView) v.findViewById(R.id.cc1);
        cc2e = (TextView) v.findViewById(R.id.cc2);
        cc3e = (TextView) v.findViewById(R.id.cc3);
        cc4e = (TextView) v.findViewById(R.id.cc4);
        cc5e = (TextView) v.findViewById(R.id.cc5);
        cc6e = (TextView) v.findViewById(R.id.cc6);
        cc7e = (TextView) v.findViewById(R.id.cc7);
        cc8e = (TextView) v.findViewById(R.id.cc8);

        cc1e.setText(Float.toString(argss[0]));
        cc2e.setText(Float.toString(argss[1]));
        cc3e.setText(Float.toString(argss[2]));
        cc4e.setText(Float.toString(argss[3]));
        cc5e.setText(Float.toString(argss[4]));
        cc6e.setText(Float.toString(argss[5]));
        cc7e.setText(Float.toString(argss[6]));
        cc8e.setText(Float.toString(argss[7]));

        actualtot = (TextView)v.findViewById(R.id.actualTot);
        plantot = (TextView)v.findViewById(R.id.planTot);

        float actualtotal = argss[0] + argss[1] + argss[2] + argss[3] + argss[4] + argss[5] + argss[6] + argss[7];
        actualtot.setText(actualtotal+"");

        cart1e = (TextView)v.findViewById(R.id.cart1e);
        cart2e = (TextView)v.findViewById(R.id.cart2e);
        cart3e = (TextView)v.findViewById(R.id.cart3e);
        cart4e = (TextView)v.findViewById(R.id.cart4e);
        cart5e = (TextView)v.findViewById(R.id.cart5e);
        cart6e = (TextView)v.findViewById(R.id.cart6e);
        cart7e = (TextView)v.findViewById(R.id.cart7e);
        cart8e = (TextView)v.findViewById(R.id.cart8e);

        cart1e.setText(cartprices[0]+"");
        cart2e.setText(cartprices[1]+"");
        cart3e.setText(cartprices[2]+"");
        cart4e.setText(cartprices[3]+"");
        cart5e.setText(cartprices[4]+"");
        cart6e.setText(cartprices[5]+"");
        cart7e.setText(cartprices[6]+"");
        cart8e.setText(cartprices[7]+"");

        float plannedtotal = cartprices[0]+ cartprices[1]+cartprices[2]+cartprices[3]+cartprices[4]+cartprices[5]+cartprices[6]+cartprices[7];
        plantot.setText(plannedtotal+"");

    }

    public void goBudgetExpense(){
        Intent intent = new Intent(getActivity(),BudgetExpenseActivity.class);
        startActivity(intent);
    }


}
