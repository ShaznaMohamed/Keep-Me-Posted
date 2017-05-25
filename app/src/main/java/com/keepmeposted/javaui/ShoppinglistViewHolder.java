package com.keepmeposted.javaui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.keepmeposted.R;

/**
 * Created by dsraj on 10/15/2016.
 */

public class ShoppinglistViewHolder extends RecyclerView.ViewHolder{
    public TextView mCategoryText,mTitleText,mDescriptionText,mUnitPriceText,mTotalPriceText,mQuantityText;
    public ImageButton mEditButton;
    public CheckBox mCheckBox;

    public ShoppinglistViewHolder(View itemView) {
        super(itemView);

        mCategoryText = (TextView)itemView.findViewById(R.id.itemCategory);
        mTitleText = (TextView)itemView.findViewById(R.id.itemTitle);
        mDescriptionText = (TextView)itemView.findViewById(R.id.itemDescription);
        mUnitPriceText = (TextView)itemView.findViewById(R.id.itemUnitprice);
        mTotalPriceText = (TextView)itemView.findViewById(R.id.itemTotalprice);
        mQuantityText = (TextView)itemView.findViewById(R.id.itemQuantity);
        mEditButton = (ImageButton)itemView.findViewById(R.id.imageButtonDelete);
        mCheckBox = (CheckBox)itemView.findViewById(R.id.checkBoxEditTodo);
    }
}
