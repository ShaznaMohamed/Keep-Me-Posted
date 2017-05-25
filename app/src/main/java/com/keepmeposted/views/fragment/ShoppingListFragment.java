package com.keepmeposted.views.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.keepmeposted.MainActivity;
import com.keepmeposted.base.BaseFragment;
import com.keepmeposted.R;
import com.keepmeposted.javaui.ShoppinglistViewHolder;
import com.keepmeposted.model.ShoppingListItem;
import com.keepmeposted.views.activity.ShoppinglistItemDetailsActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.keepmeposted.R.id.imageView;
import static com.keepmeposted.R.id.submenuarrow;

/**
 * Created by Dishan on 9/10/2016.
 */

public class ShoppingListFragment extends BaseFragment implements View.OnClickListener{

    private View view;
    private LinearLayoutManager shoppingLayoutManager;
    private CoordinatorLayout mFragmentCoordinatorLayout, mActivityCoordinatorLayout;
    private Button mAdd,mCancel;
    private TextView mFabAdd, mFabBarcode, mFabNote,mFabDelete;
    private TextView mItemTitle,mItemUnitPrice,mListTitle;
    private DatabaseReference mFirebaseRef;
    private ShoppingListItem mShoppingListItem;
    private RecyclerView mShoppingItemsRecyclerView;
    private FirebaseRecyclerAdapter<ShoppingListItem,ShoppinglistViewHolder> mShoppingListItemAdapter;
    private ArrayList<String> mArrayList = new ArrayList<>();
    private Dialog mAddDialog;
    private String mTitle,mPrice,mShoppinglistTitle;
    private StorageReference mStorageRef;
    private ImageView mImageShoppinglistMain;
    private TextView listTitlelbl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.shoppinglist_fragment, container, false);

        initialseElements();
        initialseListeners();
        shoppingLayoutManager = new LinearLayoutManager(view.getContext());
        initialiseRecycler();

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initialseElements(){
        mShoppingItemsRecyclerView = (RecyclerView)view.findViewById(R.id.shoppinglist_itemlist);
        mFirebaseRef = FirebaseDatabase.getInstance().getReference();
        mStorageRef = FirebaseStorage.getInstance().getReference();

        //Add Button Dialog
        mAddDialog = new Dialog(getContext());
        mAddDialog.setContentView(R.layout.shoppinglist_dialog_add_item);

        //Get Main Activity's elements
        mFabAdd = (TextView) getActivity().findViewById(R.id.fab_sheet_shopping_add);
        mFabBarcode = (TextView) getActivity().findViewById(R.id.fab_sheet_shopping_barcode);
        mFabDelete = (TextView) getActivity().findViewById(R.id.fab_sheet_shopping_delete);
        mFabNote = (TextView) getActivity().findViewById(R.id.fab_sheet_shopping_note);
        mActivityCoordinatorLayout = (CoordinatorLayout) getActivity().findViewById(R.id.mainCoordinator);

        mImageShoppinglistMain = (ImageView) view.findViewById(R.id.imageShoppinglistMain);
        listTitlelbl = (TextView) view.findViewById(R.id.listTitlelbl);
        mImageShoppinglistMain.setVisibility(view.GONE);

        try {
            initialiseImages();
        } catch (IOException e) {
        }
    }

    private void initialiseImages() throws IOException {

        mStorageRef.child("images/shoppinglist/mainn.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                mImageShoppinglistMain.setVisibility(View.VISIBLE);
                Picasso.with(getActivity()).load(uri)
                        .into(mImageShoppinglistMain);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                mImageShoppinglistMain.setVisibility(View.GONE);
            }
        });
    }

    private void initialiseRecycler(){

        mShoppingListItemAdapter = new FirebaseRecyclerAdapter<ShoppingListItem, ShoppinglistViewHolder>(
                ShoppingListItem.class,
                R.layout.shoppinglist_item_item,
                ShoppinglistViewHolder.class,
                mFirebaseRef.child("Shopping").child("1")
        ) {
            @Override
            protected void populateViewHolder(ShoppinglistViewHolder viewHolder, final ShoppingListItem model, final int position) {

                viewHolder.mTitleText.setText(model.getTitle());
                viewHolder.mQuantityText.setText(model.getQuantity());
                viewHolder.mUnitPriceText.setText(model.getUnitPrice());
                viewHolder.mTotalPriceText.setText(model.getTotalprice());

                viewHolder.mTitleText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(ShoppingListFragment.this.getActivity(), ShoppinglistItemDetailsActivity.class);

                        String arr[] = {
                                model.getUid()
                        } ;
                        intent.putExtra("array",arr);

                        startActivity(intent);
                    }
                });
            }
        };

        mShoppingListItemAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int shoppingItemCount = mShoppingListItemAdapter.getItemCount();

                int lastVisiblePosition = shoppingLayoutManager.findLastCompletelyVisibleItemPosition();

                if(lastVisiblePosition == -1 || (positionStart >= (shoppingItemCount -1) && lastVisiblePosition == (positionStart-1) ))
                {
                    mShoppingItemsRecyclerView.scrollToPosition(positionStart);
                }
            }
        });

        mShoppingItemsRecyclerView.setLayoutManager(shoppingLayoutManager);
        mShoppingItemsRecyclerView.setAdapter(mShoppingListItemAdapter);

    }

    private void initialseListeners(){
        mFabAdd.setOnClickListener(this);
        mFabBarcode.setOnClickListener(this);
        mFabDelete.setOnClickListener(this);
        mFabNote.setOnClickListener(this);
        listTitlelbl.setOnClickListener(this);
    }

    private void onAddPressed(){
        showDialogBox();
    }

    private void addItem(){
        mTitle = mItemTitle.getText().toString();
        mPrice = mItemUnitPrice.getText().toString();

        if (mTitle.equals("")) {
            mItemTitle.setError(getString(R.string.empty_error));
            return;
        }

        else{
            DatabaseReference postRef = mFirebaseRef.child("Shopping").child("1").push();
            mShoppingListItem = new ShoppingListItem("1","1",mTitle,mPrice,"1",mPrice);
            postRef.setValue(mShoppingListItem);

            String uid = postRef.getKey();

            DatabaseReference KeyRef = mFirebaseRef.child("Shopping").child("1").child(uid);
            KeyRef.child("uid").setValue(uid);

            clearAddDialogBox();
            mAddDialog.dismiss();
        }
    }

    private void clearAddDialogBox(){
        mItemTitle.setText("");
        mItemUnitPrice.setText("");
    }

    private void showSnackBar(String content){
        Snackbar.make(mActivityCoordinatorLayout, content, Snackbar.LENGTH_LONG)
                .show();
    }

    private void showDialogBox(){
        mAddDialog.show();
        Window window = mAddDialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        mAdd = (Button) mAddDialog.findViewById(R.id.addButton);
        mCancel = (Button) mAddDialog.findViewById(R.id.cancelButton);

        mItemTitle = (TextView) mAddDialog.findViewById(R.id.itemTitle);
        mItemUnitPrice = (TextView) mAddDialog.findViewById(R.id.itemUnitPrice);


        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAddDialogBox();
                mAddDialog.dismiss();
            }
        });

    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.fab_sheet_shopping_add:
            {
                onAddPressed();
            }
            break;

            case R.id.fab_sheet_shopping_barcode:
            {

            }
            break;

            case R.id.fab_sheet_shopping_note:
            {

            }
            case R.id.fab_sheet_shopping_delete:
            {

            }
            case R.id.listTitlelbl:
            {
                showSnackBar("Hello");
            }
            break;

            default:
                break;
        }
    }
}
