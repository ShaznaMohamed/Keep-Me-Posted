package com.keepmeposted.views.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.keepmeposted.R;
import com.keepmeposted.model.PantryListItem;

public class PcEditActivity extends AppCompatActivity {

    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
    private EditText pcName;
    public String categoryID;
    public PantryListItem item = null;
    private TextView saveBtnPc;
    private TextView cancelBtnPc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pc_edit);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        categoryID  = extras.getString("CATEGORYID");

        pcName = (EditText)findViewById(R.id.editTextPc);

        //reading data once
        ref.child("pCategory").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fetchData(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //save btn implimentation
        saveBtnPc = (TextView)findViewById(R.id.pcsavetxtview);

        saveBtnPc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item.setCategoryName(pcName.getText().toString());
                ref.child("pCategory").child(item.getId()).setValue(item);
                finish();
            }
        });

        //cancel btn implementation
        cancelBtnPc = (TextView) findViewById(R.id.pccanceltxtview);

        cancelBtnPc.setOnClickListener(new View.OnClickListener() {
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
            if(tempID.equals(categoryID))
            {
                //Toast toast = Toast.makeText(getBaseContext(), "True",Toast.LENGTH_LONG);
                //toast.show();
                item = ds.getValue(PantryListItem.class);
                pcName.setText(item.getCategoryName());
            }
        }
    }
}
