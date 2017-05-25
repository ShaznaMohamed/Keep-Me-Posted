package com.keepmeposted.views.fragment;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.keepmeposted.base.BaseFragment;
import com.keepmeposted.javaui.AlertReciever;
import com.keepmeposted.javaui.ToDoViewHolder;
import com.keepmeposted.model.TodoItem;
import com.keepmeposted.R;
import com.keepmeposted.views.activity.ToDoItemEditActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by VISH_V on 9/16/2016.
 */

public class ToDoFragment extends BaseFragment {
    View v;
    private RecyclerView todoRecyclerview;
    private LinearLayoutManager todoLayoutManager;
    private DatabaseReference REF;
    private TextView fab,deleteAllButton;
    private EditText todoInputText;
    private Button dialogButton;
    private TodoItem td;
    private FirebaseRecyclerAdapter<TodoItem,ToDoViewHolder> todoFireAdapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_todo, container, false);
        todoRecyclerview = (RecyclerView)v.findViewById(R.id.rv);
        todoLayoutManager = new LinearLayoutManager(v.getContext());
        REF = FirebaseDatabase.getInstance().getReference();
        todoLayoutManager.setStackFromEnd(true);

        /*Adding a dialog box to add item */

        fab = (TextView) getActivity().findViewById(R.id.additemfab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.todo_input_dialog);
                dialog.setTitle("To Do");

                // set the custom dialog components - text, image and button
                todoInputText= (EditText) dialog.findViewById(R.id.nameEditText);

                //ImageView image = (ImageView) dialog.findViewById(R.id.image);
                //image.setImageResource(R.drawable.ic_play_dark);

                dialogButton = (Button) dialog.findViewById(R.id.saveBtn);

                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //dialog.dismiss();
                        td = new TodoItem(todoInputText.getText().toString(),"catDefault");
                        if(!todoInputText.getText().toString().equals("")) {
                            DatabaseReference dbref = REF.child("ToDoList").push();
                            dbref.setValue(td);
                            dbref.child("itemId").setValue(dbref.getKey());
                            dialog.dismiss();                        }

                        else{
                            Toast toast = Toast.makeText(getContext(), "Name Cannot be Empty" ,Toast.LENGTH_LONG);
                            toast.show();
                        }
                    }
                });

                dialog.show();
            }
        });

        // inserting data to firebase for testing
        //TodoItem td = new TodoItem("Egg","cat1");
        //REF.child("ToDoList").push().setValue(td);
        // ending testing

        /*Handling with Firebase */
        todoFireAdapter = new FirebaseRecyclerAdapter<TodoItem, ToDoViewHolder>(
                TodoItem.class,
                R.layout.todo_card_model,
                ToDoViewHolder.class,
                REF.child("ToDoList")
        ) {
            @Override
            protected void populateViewHolder(ToDoViewHolder viewHolder, final TodoItem model, final int position) {
                viewHolder.nameTxt.setText(model.getItemName());

                viewHolder.editImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast toast = Toast.makeText(getContext(), "position is " + position,Toast.LENGTH_LONG);
                        //toast.show();

                        Intent intent = new Intent(getContext(), ToDoItemEditActivity.class);
                        //TodoItem passingItem = model;
                        intent.putExtra("ITEMID", model.getItemId());
                        startActivity(intent);
                    }
                });

                viewHolder.deleteImageButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Confirming from user
                        // delete item from base
                        REF.child("ToDoList").child(model.getItemId()).removeValue();
                    }
                });

                viewHolder.selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked == true)
                        {
                            //Toast t = Toast.makeText(getContext(),"true",Toast.LENGTH_LONG);
                            //t.show();
                            arrayList.add(model.getItemId());
                            System.out.print("VISAL added " + arrayList);
                        }

                        else if (isChecked == false){

                            //Toast t = Toast.makeText(getContext(),"false",Toast.LENGTH_LONG);
                            arrayList.remove(model.getItemId());
                            //t.show();
                        }
                    }
                });

                /* Setting alarm in the system calander */
                Calendar cal = Calendar.getInstance();
                long currentTimeInmillSec = cal.getTimeInMillis();
                if(model.getAlarmDatetime() != 0  && model.getAlarmDatetime() > currentTimeInmillSec){
                    SetAlarm(model.getAlarmDatetime(),v, model.getItemName(),model.getItemDescription() != "" ? model.getItemDescription() : "You have to do this now !",model.getUniqueID());
                }
            }
        };

        todoFireAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int todoitemCount = todoFireAdapter.getItemCount();
                int lastVisiblePosition = todoLayoutManager.findLastCompletelyVisibleItemPosition();

//                if(lastVisiblePosition == -1 || (positionStart >= (todoitemCount -1) && lastVisiblePosition == (positionStart-1) ))
//                {
//                    todoRecyclerview.scrollToPosition(positionStart);
//                }
            }
        });

        todoRecyclerview.setLayoutManager(todoLayoutManager);
        todoRecyclerview.setAdapter(todoFireAdapter);

        // Set delete all button
        deleteAllButton = (TextView) getActivity().findViewById(R.id.deleteallbtn);
        deleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arrayList.size()<=0)
                {
                    Toast toast = Toast.makeText(getContext(), "Nothing to Delete " ,Toast.LENGTH_LONG);
                    toast.show();

                }

                else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        REF.child("ToDoList").child(arrayList.get(i)).removeValue();
                    }
                    arrayList.clear();
                }
            }
        });

        return  v;
    }

    public void SetAlarm(long datetimeinMillsec, View view, String item, String description, int uniqueID) {

        //Long alertTime = new GregorianCalendar().getTimeInMillis() + 5 * 1000;
        Intent alertIntent = new Intent(getContext(), AlertReciever.class);
        alertIntent.putExtra("todoitemname",item);
        alertIntent.putExtra("todoitemdesciption",description);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, datetimeinMillsec, PendingIntent.getBroadcast(getContext(), uniqueID, alertIntent, PendingIntent.FLAG_UPDATE_CURRENT));
    }
}
