package com.keepmeposted.views.activity;;

/**
 * Created by Shazna on 10/22/2016.
 */

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;

import com.keepmeposted.R;

import static java.lang.Long.decode;
import static java.lang.Long.parseLong;

public class BudgetCalenderActivity  extends AppCompatActivity{

  CalendarView calendar, calendar1;
  TextView from,to;
  Button setDate;
  String fromdate = "-";
  String todate = "-";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //sets the main layout of the activity
            setContentView(R.layout.activity_budgetcalender);

            //initialize variables
            from = (TextView)findViewById(R.id.fromcal);
            to = (TextView)findViewById(R.id.tocal);
            setDate = (Button)findViewById(R.id.setDateRange);

            //setDateandgotback
            setDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setDateRange();
                }
            });

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

            //initializes the calendarview
            initializeCalendarfrom();
            initializeCalendarto();
        }

        public void initializeCalendarfrom() {
            calendar = (CalendarView) findViewById(R.id.calendar);

            // sets whether to show the week number.
            calendar.setShowWeekNumber(false);

            // sets the first day of week according to Calendar.
            // here we set Monday as the first day of the Calendar
            calendar.setFirstDayOfWeek(2);
            //calendar.setMinDate((Long)parseLong(Calendar.getInstance().toString()));
           // calendar.setMinDate(decode(Calendar.getInstance().toString()));
            //The background color for the selected week.
            calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));

            //sets the color for the dates of an unfocused month.
            calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));

            //sets the color for the separator line between weeks.
            calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));

            //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
            calendar.setSelectedDateVerticalBar(R.color.red);

            //sets the listener to be notified upon selected date change.
            calendar.setOnDateChangeListener(new OnDateChangeListener() {
                //show the selected date as a toast
                @Override
                public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                    Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                    from.setText("From : "+day + "/" + month + "/" + year);
                    fromdate = (day + "/" + month + "/" + year);

                    //validating the to date not to be lesser than from date
                    calendar1.setMinDate(view.getDate());
                }
            });
        }


    public void initializeCalendarto() {
        calendar1 = (CalendarView) findViewById(R.id.calendarto);

        // sets whether to show the week number.
        calendar1.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar1.setFirstDayOfWeek(2);

        //The background color for the selected week.
        calendar1.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));

        //sets the color for the dates of an unfocused month.
        calendar1.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));

        //sets the color for the separator line between weeks.
        calendar1.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar1.setSelectedDateVerticalBar(R.color.red);

        //sets the listener to be notified upon selected date change.
        calendar1.setOnDateChangeListener(new OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                to.setText("To : "+day + "/" + month + "/" + year);
                todate = (" - "+day + "/" + month + "/" + year);
            }
        });
    }

    private void setDateRange()
    {
        String daterange = fromdate + " " + todate;
        Intent intent = new Intent(this, PlanBudgetActivity.class);
        Bundle extras = new Bundle();
        extras.putString("daterange", daterange);
        intent.putExtras(extras);
        startActivity(intent);
    }

}