package com.keepmeposted.views.activity;;

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
import com.keepmeposted.model.Income;

public class IncomeEditActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private EditText piName;
    private RadioButton radioButtonLow;
    private RadioButton radioButtonFull;
    private EditText editTextPrice;
    private EditText editTextExp;
    public String itemID;
    public String categoryId;
    public Income item = null;
    private TextView saveBtnPi;
    private TextView cancelBtnPi;

    private TextView name;
    private TextView amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_income);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        itemID  = extras.getString("ItemID");
        categoryId = extras.getString("categoryId");

        amount = (TextView) findViewById(R.id.amountIncomeaEdit);
        name = (TextView) findViewById(R.id.nameIncomeEdit);

        //reading data once
        ref.child("Income/" + itemID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //save btn implementation
        saveBtnPi = (TextView)findViewById(R.id.saveIncomeBtn);

        saveBtnPi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setIncomeName(name.getText().toString());
                item.setAmount(Double.parseDouble(amount.getText().toString()));
                //ref.child("pCategory/"+categoryId+"/items/pItem").child(item.getId()).setValue(item);
                ref.child("Income/" + itemID).setValue(item);
                finish();
            }
        });

        //cancel btn implementation
//        cancelBtnPi = (TextView) findViewById(R.id.picanceltxtview);
//
//        cancelBtnPi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void fetchData(DataSnapshot dataSnapshot)
    {
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            String tempID = ds.getValue(Income.class).getId();
            if(tempID.equals(itemID))
            {
                //Toast toast = Toast.makeText(getBaseContext(), "True",Toast.LENGTH_LONG);
                //toast.show();
                item = ds.getValue(Income.class);
                name.setText(item.getIncomeName());
                amount.setText(item.getAmount()+"");

            }

        }


    }
}
