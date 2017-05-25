package com.keepmeposted.views.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.keepmeposted.R;
import com.keepmeposted.base.BaseActivity;
import com.keepmeposted.javaui.PcViewHolder;
import com.keepmeposted.model.PantryListCategory;

public class PantryListCategoryActivity extends BaseActivity  {

    FloatingActionButton addFabPC;
    RecyclerView rvPC;
    DatabaseReference ref;
    Button saveBtn,mCancel;
    Dialog dialog;
    EditText editTextPc;
    private FirebaseRecyclerAdapter<PantryListCategory,PcViewHolder> pcFireAdapter;
    private LinearLayoutManager pcLayoutManager;
    Toolbar toolbar;
    PantryListCategory pc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantry_list_category);
        setTitle("Categories");

        setupToolBar();

        ref =  FirebaseDatabase.getInstance().getReference();
        pcLayoutManager = new LinearLayoutManager(this);
        pcLayoutManager.setStackFromEnd(true);

        addFabPC = (FloatingActionButton)findViewById(R.id.additemfabPC);
        //deleteAllbtnPC = (Button)findViewById(R.id.deleteallbtn);
        rvPC = (RecyclerView)findViewById(R.id.rvPC);

        addFabPC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(),"qqqqq",Toast.LENGTH_LONG).show();
                dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.pc_input_dialog);
                dialog.setTitle("Category");

                editTextPc = (EditText) dialog.findViewById(R.id.nameEditTextPC);

                //pc.setCategoryName(editText.getText().toString());
                saveBtn = (Button) dialog.findViewById(R.id.saveBtnPC);
                mCancel = (Button) dialog.findViewById(R.id.cancelBtn);
                dialog.show();

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        DatabaseReference dbRef = ref.child("pCategory").push();
                        pc = new PantryListCategory(editTextPc.getText().toString());
                        if (!editTextPc.getText().toString().equals("")){
                            pc.setId(dbRef.getKey());
                            dbRef.setValue(pc);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(v.getContext(),"Category can not be emtpty",Toast.LENGTH_LONG).show();
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

        pcFireAdapter = new FirebaseRecyclerAdapter<PantryListCategory, PcViewHolder>(
                PantryListCategory.class,
                R.layout.pantry_category_card_model,
                PcViewHolder.class,
                ref.child("pCategory")
        ) {
            @Override
            protected void populateViewHolder(final PcViewHolder viewHolder, final PantryListCategory model, final int position) {
                viewHolder.nameTxtPC.setText(model.getCategoryName());

                viewHolder.editImageButtonPC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(v.getContext(), PcEditActivity.class);
                        intent.putExtra("CATEGORYID", model.getId());
                        startActivity(intent);

                    }
                });

                viewHolder.deleteImageButtonPC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // delete item from base
                        ref.child("pCategory").child(model.getId()).removeValue();
                        Toast.makeText(v.getContext(),"Category deteted",Toast.LENGTH_LONG).show();
                    }
                });

                viewHolder.nameTxtPC.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext(),"Category clicked",Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(v.getContext(), PantryListItemActivity.class);
                        intent.putExtra("CategoryName", viewHolder.nameTxtPC.getText().toString());
                        intent.putExtra("CategoryId", model.getId());
                        startActivity(intent);
                    }
                });
            }
        };

        /*pcFireAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver(){
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int pcitemCount = pcFireAdapter.getItemCount();
                int lastVisiblePosition = pcLayoutManager.findLastCompletelyVisibleItemPosition();

                if(lastVisiblePosition == -1 || (positionStart >= (pcitemCount -1) && lastVisiblePosition == (positionStart-1) ))
                {
                    rvPC.scrollToPosition(positionStart);
                }
            }
        });*/

        rvPC.setLayoutManager(pcLayoutManager);
        rvPC.setAdapter(pcFireAdapter);

    }

    private void setupToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

}
