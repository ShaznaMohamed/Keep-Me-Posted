package com.keepmeposted.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keepmeposted.R;
import com.keepmeposted.model.PantryListItem;

public class PiEditActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private EditText piName;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonFull;
    private EditText editTextPrice;
    private EditText editTextExp;
    public String itemID;
    public String categoryId;
    public PantryListItem item = null;
    private TextView saveBtnPi;
    private TextView cancelBtnPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_edit);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        itemID  = extras.getString("ItemID");
        categoryId = extras.getString("categoryId");

        piName = (EditText)findViewById(R.id.editTextPi);
        radioButtonLow = (RadioButton)findViewById(R.id.radioButtonLow);
        radioButtonFull = (RadioButton)findViewById(R.id.radioButtonFull);
        editTextPrice = (EditText)findViewById(R.id.editTextPrice);
        editTextExp = (EditText)findViewById(R.id.editTextExp);

        //reading data once
        ref.child("pCategory/"+categoryId+"/items/pItem").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //save btn implimentation
        saveBtnPi = (TextView)findViewById(R.id.pisavetxtview);

        saveBtnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setPantryItem(piName.getText().toString());
                item.setPrice(editTextPrice.getText().toString());
                item.setDate(editTextExp.getText().toString());
                ref.child("pCategory/"+categoryId+"/items/pItem").child(item.getId()).setValue(item);
                finish();
            }
        });

        //cancel btn implementation
        cancelBtnPi = (TextView) findViewById(R.id.picanceltxtview);

        cancelBtnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            String tempID = ds.getValue(PantryListItem.class).getId();
            if(tempID.equals(itemID))
            {
                item = ds.getValue(PantryListItem.class);
                piName.setText(item.getPantryItem());
                editTextExp.setText(item.getDate());
                editTextPrice.setText(item.getPrice());

            }
        }
    }
}
