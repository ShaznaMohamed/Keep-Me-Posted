package com.keepmeposted.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.keepmeposted.R;
import com.keepmeposted.javaui.PitemViewHolder;
import com.keepmeposted.model.PantryListItem;

import java.util.ArrayList;

public class PantryListItemActivity extends AppCompatActivity {

    public String categoryName;
    public String categoryId;

    View v;
    FloatingActionButton addFabPI;
    Button deleteAllbtnPI;
    RecyclerView rvPI;
    DatabaseReference ref;
    Button saveBtn,mCancel;
    Dialog dialog;
    EditText editTextPi;
    EditText editQtyTextPi;
    EditText editExpTextPi;
    EditText editPriceTextPi;
    private FirebaseRecyclerAdapter<PantryListItem,PitemViewHolder> piFireAdapter;
    private LinearLayoutManager piLayoutManager;
    private ArrayList<String> arrayList = new ArrayList<>();
    Toolbar toolbar;

    PantryListItem pi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list_item);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        categoryName  = extras.getString("CategoryName");
        categoryId  = extras.getString("CategoryId");

        setTitle(categoryName);
        setupToolBar();

        ref =  FirebaseDatabase.getInstance().getReference();
        piLayoutManager = new LinearLayoutManager(this);
        //piLayoutManager.setStackFromEnd(true);

        addFabPI = (FloatingActionButton)findViewById(R.id.additemfabPI);
        deleteAllbtnPI = (Button)findViewById(R.id.deleteallbtnPI);
        rvPI = (RecyclerView)findViewById(R.id.rvPI);

        addFabPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"qqqqq",Toast.LENGTH_LONG).show();
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.pitem_input_dialog);
                dialog.setTitle("Item");

                editTextPi = (EditText) dialog.findViewById(R.id.nameEditTextPI);
                editQtyTextPi = (EditText) dialog.findViewById(R.id.nameQtyTextPI);
                editExpTextPi = (EditText) dialog.findViewById(R.id.nameExpTextPI);
                editPriceTextPi = (EditText) dialog.findViewById(R.id.namePriceTextPI);

                //pc.setCategoryName(editText.getText().toString());
                saveBtn = (Button) dialog.findViewById(R.id.saveBtnPI);
                mCancel = (Button) dialog.findViewById(R.id.cancelBtn);
                dialog.show();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference dbRef = ref.child("pCategory/"+categoryId+"/items/pItem").push();
                        pi = new PantryListItem(editTextPi.getText().toString());
                        if (!editTextPi.getText().toString().equals("")){
                            pi.setId(dbRef.getKey());
                            dbRef.setValue(pi);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(v.getContext(),"Item can not be empty",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                mCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


        piFireAdapter = new FirebaseRecyclerAdapter<PantryListItem, PitemViewHolder>(
                PantryListItem.class,
                R.layout.pantry_item_card_model,
                PitemViewHolder.class,
                ref.child("pCategory/"+categoryId+"/items/pItem")
        ) {
            @Override
            protected void populateViewHolder(final PitemViewHolder viewHolder, final PantryListItem model, final int position) {
                viewHolder.nameTxtPI.setText(model.getPantryItem());
                viewHolder.priceTxtPI.setText(model.getPrice());
                viewHolder.expDateTxtPI.setText(model.getDate());

                viewHolder.editImageButtonPI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"item clicked",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), PiEditActivity.class);
                        intent.putExtra("ItemID", model.getId());
                        intent.putExtra("categoryId", categoryId);
                        startActivity(intent);

                    }
                });

                viewHolder.deleteImageButtonPI.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // delete item from base
                        ref.child("pCategory/"+categoryId+"/items/pItem").child(model.getId()).removeValue();
                        Toast.makeText(v.getContext(),"Item deteted",Toast.LENGTH_LONG).show();
                    }
                });

                viewHolder.selectCheckBoxPI.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked == true)
                        {
                            //Toast t = Toast.makeText(getContext(),"true",Toast.LENGTH_LONG);
                            //t.show();
                            arrayList.add(model.getId());
                            System.out.print("item added " + arrayList);
                        }

                        else if (isChecked == false){

                            //Toast t = Toast.makeText(getContext(),"false",Toast.LENGTH_LONG);
                            arrayList.remove(model.getId());
                            //t.show();

                        }

                    }
                });

            }
        };

        /*piFireAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int pitemCount = pcFireAdapter.getItemCount();
                int lastVisiblePosition = pcLayoutManager.findLastCompletelyVisibleItemPosition();

                if(lastVisiblePosition == -1 || (positionStart >= (pcitemCount -1) && lastVisiblePosition == (positionStart-1) ))
                {
                    rvPC.scrollToPosition(positionStart);
                }
            }
        });*/

        rvPI.setLayoutManager(piLayoutManager);
        rvPI.setAdapter(piFireAdapter);

        // Set delete all button
        deleteAllbtnPI = (Button)findViewById(R.id.deleteallbtnPI);
        deleteAllbtnPI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(arrayList.size()<=0)
                {
                    Toast.makeText(v.getContext(),"Nothing to Delete",Toast.LENGTH_LONG).show();

                }

                else {
                    for (int i = 0; i < arrayList.size(); i++) {
                        //Toast toast = Toast.makeText(getContext(), "ID is " + arrayList.get(i),Toast.LENGTH_LONG);
                        //toast.show();

                        ref.child("pCategory/"+categoryId+"/items/pItem").child(arrayList.get(i)).removeValue();

                    }

                    arrayList.clear();
                }
            }
        });

    }

    private void setupToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
