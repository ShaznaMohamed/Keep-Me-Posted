package com.keepmeposted.views.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.keepmeposted.R;
import com.keepmeposted.base.BaseActivity;
import com.keepmeposted.model.ShoppingListItem;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import static android.graphics.Color.WHITE;

/**
 * Created by Dishan on 10/8/2016.
 */

public class ShoppinglistItemDetailsActivity extends BaseActivity {

    EditText mTitleE,mDescriptionE,mUnitE,mTotalE,mCategoryE,mQuantityE;
    Button mEdit,mDelete,mSave;
    int mAccent;
    DatabaseReference mFirebaseRef,mFirebaseRefItem;
    private StorageReference mStorageRef;
    String mUID,mTitle,mPrice,mDescription,mQuantity,mUnit,mCategory,mTotal;
    String arr[];
    private ImageView mImageShoppinglistItemMain;
    private ShoppingListItem mShoppingListItem;
    private Boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shoppinglist_activity_edit_item);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        arr = extras.getStringArray("array");
        mUID = arr[0];

        initialiseItems();

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editEnabled();
            }
        });

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEdits();
            }
        });
    }

    public void initialiseItems(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mFirebaseRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        mTitleE = (EditText) findViewById(R.id.itemTitleE);
        mDescriptionE = (EditText) findViewById(R.id.itemDescriptionE);
        mUnitE = (EditText) findViewById(R.id.itemUnitpriceE);
        mTotalE = (EditText) findViewById(R.id.itemTotalpriceE);
        mCategoryE = (EditText) findViewById(R.id.itemCategoryE);
        mQuantityE = (EditText) findViewById(R.id.itemQuantityE);

        mImageShoppinglistItemMain = (ImageView) findViewById(R.id.imageShoppinglistItemMain);
        mImageShoppinglistItemMain.setVisibility(View.GONE);

        mEdit = (Button) findViewById(R.id.editButtonE);
        mDelete = (Button) findViewById(R.id.deleteButtonE);
        mSave = (Button) findViewById(R.id.saveButtonE);

        mAccent = fetchAccentColor();

        editDisabled();

        try {
            initialiseImages();
        } catch (IOException e) {
        }
    }

    @Override
    public void onStart(){
        super.onStart();
        getDetails();
    }

    public void getDetails(){

        mFirebaseRefItem = mFirebaseRef.child("Shopping").child("1").child(mUID);

        ValueEventListener mShoppingItemListner = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                ShoppingListItem mItem = dataSnapshot.getValue(ShoppingListItem.class);
                mTitleE.setText(mItem.getTitle());
                mDescriptionE.setText(mItem.getDescription());
                mUnitE.setText(mItem.getUnitPrice());
                mTotalE.setText(mItem.getTotalprice());
                mCategoryE.setText(mItem.getCategory());
                mQuantityE.setText(mItem.getQuantity());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        };
        mFirebaseRefItem.addValueEventListener(mShoppingItemListner);
    }

    private void initialiseImages() throws IOException {

        mStorageRef.child("images/shoppinglist/mainn.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                mImageShoppinglistItemMain.setVisibility(View.VISIBLE);
                Picasso.with(ShoppinglistItemDetailsActivity.this)
                        .load(uri)
                        .into(mImageShoppinglistItemMain);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mImageShoppinglistItemMain.setVisibility(View.GONE);
            }
        });
    }

    public void editEnabled() {
            mTitleE.getBackground().setColorFilter(mAccent, PorterDuff.Mode.SRC_ATOP);
            mDescriptionE.getBackground().setColorFilter(mAccent, PorterDuff.Mode.SRC_ATOP);
            mUnitE.getBackground().setColorFilter(mAccent, PorterDuff.Mode.SRC_ATOP);
            mCategoryE.getBackground().setColorFilter(mAccent, PorterDuff.Mode.SRC_ATOP);
            mQuantityE.getBackground().setColorFilter(mAccent, PorterDuff.Mode.SRC_ATOP);

            mTitleE.setEnabled(true);
            mDescriptionE.setEnabled(true);
            mUnitE.setEnabled(true);
            mCategoryE.setEnabled(true);
            mQuantityE.setEnabled(true);

            mEdit.setVisibility(View.GONE);
            mSave.setVisibility(View.VISIBLE);

            editMode = true;
    }

    private void saveEdits() {
        mTitle = mTitleE.getText().toString();
        mPrice = mUnitE.getText().toString();
        mDescription = mDescriptionE.getText().toString();
        mCategory = mCategoryE.getText().toString();
        mQuantity = mQuantityE.getText().toString();
        mUnit = mUnitE.getText().toString();

        if (mTitle.equals("")) {
            mTitleE.setError(getString(R.string.empty_error));
        } else if (mUnit.equals("")) {
            mUnitE.setError(getString(R.string.empty_error));
        } else if (mQuantity.equals("")) {
            mQuantityE.setError(getString(R.string.empty_error));
        } else if (Integer.parseInt(mQuantity) == 0) {
            mTitleE.setError("Cannot be 0");
        } else {

            int price = Integer.parseInt(mPrice);
            int quantity = Integer.parseInt(mQuantity);
            int total;

            total = (price * quantity);

            mTotal = String.valueOf(total);

            DatabaseReference postRef = mFirebaseRef.child("Shopping").child("1").child(mUID);

            mShoppingListItem = new ShoppingListItem("1", mUID, mTitle, mCategory, mQuantity, mUnit, mTotal, mDescription);
            postRef.setValue(mShoppingListItem);
            disableEdit();
        }
    }

    private int fetchAccentColor() {
        TypedValue typedValue = new TypedValue();

        TypedArray a = this.obtainStyledAttributes(typedValue.data, new int[] { R.attr.colorAccent });
        int color = a.getColor(0, 0);

        a.recycle();

        return color;
    }

    public void editDisabled(){
        mTitleE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mDescriptionE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mUnitE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mTotalE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mCategoryE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mQuantityE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
    }

    public void disableEdit(){
        mTitleE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mDescriptionE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mUnitE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mCategoryE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);
        mQuantityE.getBackground().setColorFilter(WHITE, PorterDuff.Mode.SRC_ATOP);

        mTitleE.setEnabled(false);
        mDescriptionE.setEnabled(false);
        mUnitE.setEnabled(false);
        mCategoryE.setEnabled(false);
        mQuantityE.setEnabled(false);

        mSave.setVisibility(View.GONE);
        mEdit.setVisibility(View.VISIBLE);

        editMode=false;
    }

    public void onBackPressed() {

        if (editMode == true) {
            new AlertDialog.Builder(this)
                    .setMessage("Are you sure you want to discard?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            getDetails();
                            disableEdit();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
        }

        else if(editMode==false){
            super.onBackPressed();
        }

    }
}
