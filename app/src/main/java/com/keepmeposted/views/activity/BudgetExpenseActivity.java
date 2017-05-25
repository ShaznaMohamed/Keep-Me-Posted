package com.keepmeposted.views.activity;;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.keepmeposted.MainActivity;
import com.keepmeposted.R;
import com.keepmeposted.base.BaseActivity;


import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Shazna on 11/6/2016.
 */

public class BudgetExpenseActivity extends BaseActivity {

    TextView cc1e, cc2e,cc3e,cc4e, cc5e, cc6e, cc7e, cc8e, actualtot, plantot,balantot, perctot, sumIncometot, sumacttot, sumbal;
    TextView cart1e,cart2e, cart3e, cart4e, cart5e, cart6e, cart7e, cart8e;
    TextView cc1ef, cc2ef,cc3ef,cc4ef, cc5ef, cc6ef, cc7ef, cc8ef;
    TextView cc1efp, cc2efp,cc3efp,cc4efp, cc5efp, cc6efp, cc7efp, cc8efp;
    public float[] yData = {150, 200, 150, 250, 350, 100, 300, 400};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budgetexpense);

        final CardView barCard = (CardView)findViewById(R.id.barchartcard);

        //Go Back
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("General Budget");

        //show the table
        final CardView expensecardd = (CardView) findViewById(R.id.expensescard);
        final CardView expSummary = (CardView)findViewById(R.id.expSummaryCard);

        Spinner spin = (Spinner)findViewById(R.id.spinner);
        final String selectedone = spin.getSelectedItem().toString();


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            int i = 0;
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                i++;
                if(id == 1)
                {
                    expensecardd.setVisibility(View.GONE);
                    expSummary.setVisibility(View.GONE);
                    barCard.setVisibility(View.GONE);

                    //design the table
                    final float[] arg1 = {150, 200, 250, 250, 350, 100, 300, 300};
                    final float[] cartprice = {100, 200, 329, 329, 326, 130, 230, 399};
                    expenseTabDesigning(cartprice, arg1, 18000.00);

                    expensecardd.setVisibility(View.VISIBLE);
                    expSummary.setVisibility(View.VISIBLE);

                    //generateBarChart
                    final CardView barCard = (CardView)findViewById(R.id.barchartcard);
                    final CardView tableCard = (CardView)findViewById(R.id.expensescard);

                    Button gc = (Button)findViewById(R.id.generateBarchart);
                    gc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            barCard.setVisibility(View.VISIBLE);
                            generateBarChart(arg1,cartprice);
                            tableCard.bringToFront();

                        }
                    });
                }
                if(id == 2)
                {
                    expensecardd.setVisibility(View.GONE);
                    expSummary.setVisibility(View.GONE);
                    barCard.setVisibility(View.GONE);

                    //design the table
                    final float[] arg1 = {2000, 2900, 2500, 1500, 1350, 1900, 1500, 1300};
                    final float[] cartprice = {2000, 2990, 2290, 1790, 1326, 1300, 1230, 1399};
                    expenseTabDesigning(cartprice, arg1, 50000.00);

                    expensecardd.setVisibility(View.VISIBLE);
                    expSummary.setVisibility(View.VISIBLE);

                    //generateBarChart
                    final CardView barCard = (CardView)findViewById(R.id.barchartcard);
                    final CardView tableCard = (CardView)findViewById(R.id.expensescard);
                    Button gc = (Button)findViewById(R.id.generateBarchart);
                    gc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            barCard.setVisibility(View.VISIBLE);
                            generateBarChart(arg1,cartprice);
                            tableCard.bringToFront();

                        }
                    });
                }
                if(id == 3)
                {
                    expensecardd.setVisibility(View.GONE);
                    expSummary.setVisibility(View.GONE);
                    barCard.setVisibility(View.GONE);

                    //design the table
                    final float[] arg1 = {250, 200, 250, 300, 1000, 500, 1000, 500};
                    final float[] cartprice = {300, 200, 329, 229, 1126, 600, 1230, 399};
                    expenseTabDesigning(cartprice, arg1, 40000.00);

                    expensecardd.setVisibility(View.VISIBLE);
                    expSummary.setVisibility(View.VISIBLE);

                    //generateBarChart
                    final CardView barCard = (CardView)findViewById(R.id.barchartcard);
                    final CardView tableCard = (CardView)findViewById(R.id.expensescard);
                    Button gc = (Button)findViewById(R.id.generateBarchart);
                    gc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            barCard.setVisibility(View.VISIBLE);
                            generateBarChart(arg1,cartprice);
                            tableCard.bringToFront();

                        }
                    });
                }
                if(id == 4)
                {
                    expensecardd.setVisibility(View.GONE);
                    expSummary.setVisibility(View.GONE);
                    barCard.setVisibility(View.GONE);

                    //design the table
                    final float[] arg1 = {15000, 20000, 25000, 25000, 35000, 1000, 30000, 30000};
                    final float[] cartprice = {18000, 20000, 30290, 32900, 32600, 1300, 23000, 39900};
                    expenseTabDesigning(cartprice, arg1, 35000.00);

                    expensecardd.setVisibility(View.VISIBLE);
                    expSummary.setVisibility(View.VISIBLE);

                    //generateBarChart
                    final CardView barCard = (CardView)findViewById(R.id.barchartcard);
                    final CardView tableCard = (CardView)findViewById(R.id.expensescard);
                    Button gc = (Button)findViewById(R.id.generateBarchart);
                    gc.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {


                            barCard.setVisibility(View.VISIBLE);
                            generateBarChart(arg1,cartprice);
                            tableCard.bringToFront();

                        }
                    });
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    public void expenseTabDesigning(float[] cartprices, float[] argss, double totincome )
    {
        //float[] argss = {150, 200, 250, 250, 350, 100, 300, 300};

        TableLayout tle = (TableLayout) findViewById(R.id.la1e);
        tle.setStretchAllColumns(true);
        tle.setBackgroundColor(Color.parseColor("#F8F8FF"));

        cc1e = (TextView)findViewById(R.id.cc1e);
        cc2e = (TextView)findViewById(R.id.cc2e);
        cc3e = (TextView)findViewById(R.id.cc3e);
        cc4e = (TextView)findViewById(R.id.cc4e);
        cc5e = (TextView)findViewById(R.id.cc5e);
        cc6e = (TextView)findViewById(R.id.cc6e);
        cc7e = (TextView)findViewById(R.id.cc7e);
        cc8e = (TextView)findViewById(R.id.cc8e);

        cc1e.setText(argss[0]+"");
        cc2e.setText(argss[1]+"");
        cc3e.setText(argss[2]+"");
        cc4e.setText(argss[3]+"");
        cc5e.setText(argss[4]+"");
        cc6e.setText(argss[5]+"");
        cc7e.setText(argss[6]+"");
        cc8e.setText(argss[7]+"");

        actualtot = (TextView) findViewById(R.id.actualTot);
        plantot = (TextView) findViewById(R.id.planTot);
        balantot = (TextView) findViewById(R.id.balanceTot);
        perctot = (TextView)findViewById(R.id.percentageTot);
        sumIncometot = (TextView)findViewById(R.id.totIncomeamn);
        sumacttot = (TextView)findViewById(R.id.totExpamn);
        sumbal = (TextView)findViewById(R.id.totBalamn);

        TableLayout tl = (TableLayout)findViewById(R.id.la1es);
        tl.setStretchAllColumns(true);

        float actualtotall = argss[0]+ argss[1]+ argss[2]+ argss[3]+ argss[4]+ argss[5]+ argss[6]+ argss[7];
        actualtot.setText(actualtotall+"");
        sumacttot.setText("Rs. "+actualtotall);
        sumIncometot.setText("Rs. "+totincome);
        sumbal.setText("Rs. "+(totincome - actualtotall));

        if((2500.00 - actualtotall) < 0.00)
            sumbal.setTextColor(getResources().getColor(R.color.purered));
        else
            sumbal.setTextColor(getResources().getColor(R.color.darkgreen));

        cart1e = (TextView)findViewById(R.id.cart1e);
        cart2e = (TextView)findViewById(R.id.cart2e);
        cart3e = (TextView)findViewById(R.id.cart3e);
        cart4e = (TextView)findViewById(R.id.cart4e);
        cart5e = (TextView)findViewById(R.id.cart5e);
        cart6e = (TextView)findViewById(R.id.cart6e);
        cart7e = (TextView)findViewById(R.id.cart7e);
        cart8e = (TextView)findViewById(R.id.cart8e);

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

        cc1ef = (TextView)findViewById(R.id.cc1ef);
        cc2ef = (TextView)findViewById(R.id.cc2ef);
        cc3ef = (TextView)findViewById(R.id.cc3ef);
        cc4ef = (TextView)findViewById(R.id.cc4ef);
        cc5ef = (TextView)findViewById(R.id.cc5ef);
        cc6ef = (TextView)findViewById(R.id.cc6ef);
        cc7ef = (TextView)findViewById(R.id.cc7ef);
        cc8ef = (TextView)findViewById(R.id.cc8ef);

        if((cartprices[0] - argss[0]) < 0.0)
            cc1ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc1ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[1] - argss[1]) < 0.0)
            cc2ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc2ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[2] - argss[2]) < 0.0)
            cc3ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc3ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[3] - argss[3]) < 0.0)
            cc4ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc4ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[4] - argss[4]) < 0.0)
            cc5ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc5ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[5] - argss[5]) < 0.0)
            cc6ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc6ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[6] - argss[6]) < 0.0)
            cc7ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc7ef.setTextColor(getResources().getColor(R.color.darkgreen));

        if((cartprices[7] - argss[7]) < 0.0)
            cc8ef.setTextColor(getResources().getColor(R.color.purered));
        else
            cc8ef.setTextColor(getResources().getColor(R.color.darkgreen));

        cc1ef.setText((cartprices[0] - argss[0])+"");
        cc2ef.setText((cartprices[1] - argss[1])+"");
        cc3ef.setText((cartprices[2] - argss[2])+"");
        cc4ef.setText((cartprices[3] - argss[3])+"");
        cc5ef.setText((cartprices[4] - argss[4])+"");
        cc6ef.setText((cartprices[5] - argss[5])+"");
        cc7ef.setText((cartprices[6] - argss[6])+"");
        cc8ef.setText((cartprices[7] - argss[7])+"");

        float balancetotal = (cartprices[0] - argss[0]) + (cartprices[1] - argss[1]) + (cartprices[2] - argss[2]) + (cartprices[3] - argss[3]) +(cartprices[4] - argss[4]) + (cartprices[5] - argss[5])+ (cartprices[6] - argss[6]) + (cartprices[7] - argss[7]);
        if(balancetotal < 0.0)
           balantot.setTextColor(getResources().getColor(R.color.purered));
        else
            balantot.setTextColor(getResources().getColor(R.color.darkgreen));
        balantot.setText(balancetotal+"");

        DecimalFormat f = new DecimalFormat("##.00");

        double perc1 = (argss[0] / cartprices[0]) * 100.0 ;
        double perc2 = (argss[1] / cartprices[1]) * 100.0 ;
        double perc3 = (argss[2] / cartprices[2]) * 100.0 ;
        double perc4 = (argss[3] / cartprices[3]) * 100.0 ;
        double perc5 = (argss[4] / cartprices[4]) * 100.0 ;
        double perc6 = (argss[5] / cartprices[5]) * 100.0 ;
        double perc7 = (argss[6] / cartprices[6]) * 100.0 ;
        double perc8 = (argss[7] / cartprices[7]) * 100.0 ;

        cc1efp = (TextView)findViewById(R.id.cc1efp);
        cc2efp = (TextView)findViewById(R.id.cc2efp);
        cc3efp = (TextView)findViewById(R.id.cc3efp);
        cc4efp = (TextView)findViewById(R.id.cc4efp);
        cc5efp = (TextView)findViewById(R.id.cc5efp);
        cc6efp = (TextView)findViewById(R.id.cc6efp);
        cc7efp = (TextView)findViewById(R.id.cc7efp);
        cc8efp = (TextView)findViewById(R.id.cc8efp);

        if(perc1 > 100.00)
            cc1efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc1efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc2 > 100.00)
            cc2efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc2efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc3 > 100.00)
            cc3efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc3efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc4 > 100.00)
            cc4efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc4efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc5 > 100.00)
            cc5efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc5efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc6 > 100.00)
            cc6efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc6efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc7 > 100.00)
            cc7efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc7efp.setTextColor(getResources().getColor(R.color.darkgreen));
        if(perc8 > 100.00)
            cc8efp.setTextColor(getResources().getColor(R.color.purered));
        else
            cc8efp.setTextColor(getResources().getColor(R.color.darkgreen));

        cc1efp.setText(f.format(perc1)+"%");
        cc2efp.setText(f.format(perc2)+"%");
        cc3efp.setText(f.format(perc3)+"%");
        cc4efp.setText(f.format(perc4)+"%");
        cc5efp.setText(f.format(perc5)+"%");
        cc6efp.setText(f.format(perc6)+"%");
        cc7efp.setText(f.format(perc7)+"%");
        cc8efp.setText(f.format(perc8)+"%");

        double tot = perc1 + perc2 +perc3 +perc4 +perc5 +perc6 +perc7 +perc8;

        perctot.setText(f.format(tot)+"%");

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to discard?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        goMain();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    public void goMain(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void usingSpinner(View v)
    {
        CardView expensecardd = (CardView) findViewById(R.id.expensecard);

        Spinner spin = (Spinner)findViewById(R.id.spinner);
        String selectedone = spin.getSelectedItem().toString();
        if(selectedone != null)
        {
            expensecardd.setVisibility(v.VISIBLE);
        }
    }

    public void generateBarChart(float[] act, float[] act1)
    {

      // BarChart barChart = (BarChart) findViewById(R.id.chart);

         HorizontalBarChart barChart= (HorizontalBarChart) findViewById(R.id.chart);
        /*
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(4f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(6f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(10f, 6));
        entries.add(new BarEntry(14f, 7));
        entries.add(new BarEntry(2f, 5));

         BarDataSet dataset1 = new BarDataSet(entries, "# of Calls");
         */

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("Food");
        labels.add("Cloth");
        labels.add("Travelling");
        labels.add("Stationary");
        labels.add("Furniture");
        labels.add("Medicine");
        labels.add("Bill");
        labels.add("Utensils");


        /* for create Grouped Bar chart*/
        ArrayList<BarEntry> group1 = new ArrayList<>();
        group1.add(new BarEntry(act1[0], 0));
        group1.add(new BarEntry(act1[1], 1));
        group1.add(new BarEntry(act1[2], 2));
        group1.add(new BarEntry(act1[3], 3));
        group1.add(new BarEntry(act1[4], 4));
        group1.add(new BarEntry(act1[5], 5));
        group1.add(new BarEntry(act1[6], 6));
        group1.add(new BarEntry(act1[7], 7));

        ArrayList<BarEntry> group2 = new ArrayList<>();
        group2.add(new BarEntry(act[0], 0));
        group2.add(new BarEntry(act[1], 1));
        group2.add(new BarEntry(act[2], 2));
        group2.add(new BarEntry(act[3], 3));
        group2.add(new BarEntry(act[4], 4));
        group2.add(new BarEntry(act[5], 5));
        group2.add(new BarEntry(act[6], 6));
        group2.add(new BarEntry(act[7], 7));

        BarDataSet barDataSet1 = new BarDataSet(group1, "Planned Amount");
        //barDataSet1.setColor(Color.rgb(0, 155, 0));
        barDataSet1.setColor(getResources().getColor(R.color.darkgreen));

        BarDataSet barDataSet2 = new BarDataSet(group2, "Actual Amount");
        barDataSet2.setColor(getResources().getColor(R.color.purered));


        ArrayList<IBarDataSet> dataset = new ArrayList<>();
        dataset.add(barDataSet1);
        dataset.add(barDataSet2);
        /**/

        BarData data = new BarData(labels,dataset);
//        // dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        barChart.setData(data);
        barChart.animateY(5000);
        barChart.setDescription("Expense Graph");
        barChart.setDescriptionPosition(2f, 2f);

    }
}
