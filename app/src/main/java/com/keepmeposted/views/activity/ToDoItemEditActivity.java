package com.keepmeposted.views.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keepmeposted.R;
import com.keepmeposted.model.TodoItem;

import java.sql.Time;
import java.util.Calendar;
import java.util.Random;

public class ToDoItemEditActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    //private EditText TodDoName;
    private AutoCompleteTextView TodDoName;
    public String itemID;
    private TodoItem REALITEM;
    public TodoItem item = null;
    private EditText timeTextView;
    private EditText dateTextView;
    private EditText textNote;
    private DatePickerDialog DatepickerD;
    private DatePicker datepicker;
    private TimePicker timepicker;
    //private TextView saveBTN;
    //private TextView cancelBTN;
    private Button cancelBTN,saveBTN;
    private Button dateSaveDlgbtn,timeSaveDlgBtn;
    private ImageButton dayImgBtn,timeImgBtn;
    private int day,month,year = 0;
    private int minutes,hours = 99;
    private long mills = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_item_edit);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        itemID  = extras.getString("ITEMID");


        TodDoName = (AutoCompleteTextView) findViewById(R.id.textEditTodoName);
        dateTextView = (EditText) findViewById(R.id.editDate);
        timeTextView = (EditText) findViewById(R.id.editTime);
        textNote = (EditText)findViewById(R.id.textEditNotes);
        // reading data once
        ref.child("ToDoList").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // Get user value
                        fetchData(dataSnapshot);


                        // ...
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.w(TAG, "getUser:onCancelled", databaseError.toException());
                        // ...
                    }


                });



        /* handling time and claneder */

        dayImgBtn = (ImageButton)findViewById(R.id.imageButtonDay);
        timeImgBtn = (ImageButton)findViewById(R.id.imageButtonTime);

        timeImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(v.getContext(),"Clicked",Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.time_input_layout);
                dialog.setTitle("Time");

                dialog.show();

                timeSaveDlgBtn = (Button)dialog.findViewById(R.id.setTimeBtn);
                timepicker = (TimePicker) dialog.findViewById(R.id.alarmTimePicker);

                timeSaveDlgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        minutes = timepicker.getCurrentMinute();
                        hours = timepicker.getCurrentHour();

                        if (item.getUniqueID() == 1) {
                            Random r = new Random();
                            int i1 = r.nextInt((100 - 2) + 1) + 2;
                            item.setUniqueID(i1);
                        }

                        //Toast.makeText(getBaseContext(),"saved", Toast.LENGTH_SHORT).show();
                        timeTextView.setText(hours + " : " + minutes);
                        dialog.dismiss();
                    }
                });

            }
        });


        dayImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(v.getContext(),"Clicked",Toast.LENGTH_SHORT).show();
                final Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.day_input_layout);
                dialog.setTitle("Date");

                dialog.show();

                dateSaveDlgbtn = (Button)dialog.findViewById(R.id.setDateBtn);
                datepicker = (DatePicker)dialog.findViewById(R.id.alarmDatePicker);

                dateSaveDlgbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        day = datepicker.getDayOfMonth();
                        month = datepicker.getMonth();
                        year = datepicker.getYear();

                        int tempM = month + 1;

                        //Toast.makeText(getBaseContext(),"saved", Toast.LENGTH_SHORT).show();
                        dateTextView.setText(day + " / " + tempM + " / " + year);
                        dialog.dismiss();

                    }
                });

            }
        });


        /* end of time and calander */
        //DatepickerD = new DatePickerDialog();



        /* handling save button and cancel button */

        //saveBTN = (TextView)findViewById(R.id.savetxtview);
        saveBTN = (Button)findViewById(R.id.TodoSaveButton);

        saveBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(day != 0 && month != 0 && year != 0 && minutes != 99 && hours != 99)
                {


                /* code for formatting time */
                    Calendar cal = Calendar.getInstance();
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.MONTH, month);
                    cal.set(Calendar.DAY_OF_MONTH, day);
                    cal.set(Calendar.HOUR_OF_DAY, hours);
                    cal.set(Calendar.MINUTE, minutes);
                    cal.set(Calendar.SECOND, 0);
                    mills = cal.getTimeInMillis();
                /*end of code for formatting time */

                }

                if(textNote.getText() != null && textNote.getText().toString() != "")
                {
                    item.setItemDescription(textNote.getText().toString());
                }

                if(mills != 0) {
                    item.setAlarmDatetime(mills);

                }
                item.setItemName(TodDoName.getText().toString());
                ref.child("ToDoList").child(item.getItemId()).setValue(item);
                finish();
            }
        });
        //cancelBTN = (TextView)findViewById(R.id.canceltxtview);
        cancelBTN = (Button)findViewById(R.id.TodocancelButton);

        cancelBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Clicked",Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }


    private void fetchData(DataSnapshot dataSnapshot)
    {

        for(DataSnapshot ds : dataSnapshot.getChildren())
        {

            String tempID = ds.getValue(TodoItem.class).getItemId();
            if(tempID.equals(itemID))
            {
                //Toast toast = Toast.makeText(getBaseContext(), "True",Toast.LENGTH_LONG);
                //toast.show();
                item = ds.getValue(TodoItem.class);
                TodDoName.setText(item.getItemName());

                if(item.getAlarmDatetime() != 0)
                {
                    Calendar cal = Calendar.getInstance();
                    cal.setTimeInMillis(item.getAlarmDatetime());

                    int tempDay = cal.getTime().getDate();
                    int tempMonth = cal.getTime().getMonth() + 1;
                    int tempYear = cal.getTime().getYear();
                    int tempHours = cal.getTime().getHours()  ;
                    int tempMinutes = cal.getTime().getMinutes();

                    dateTextView.setText(tempDay + " / " + tempMonth + " / " +tempYear);
                    timeTextView.setText(tempHours+ " : " + tempMinutes);
                }


            }

        }


    }
}
