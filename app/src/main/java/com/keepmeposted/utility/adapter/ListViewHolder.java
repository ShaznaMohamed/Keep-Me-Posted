//package com.keepmeposted.utility.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.TextView;
//import com.keepmeposted.R;
//import com.keepmeposted.model.ShoppingList;
//
///**
// * Created by Dishan on 10/8/2016.
// */
//
//public class ListViewHolder extends RecyclerView.ViewHolder {
//
//    public TextView titleView;
//    public TextView categoryView;
//
//    public ListViewHolder(View itemView) {
//        super(itemView);
//
//        titleView = (TextView) itemView.findViewById(R.id.list_title);
//        categoryView = (TextView) itemView.findViewById(R.id.list_category);
//    }
//
//    public void bindToPost(ShoppingList list) {
//        titleView.setText(list.title);
//        categoryView.setText(list.category);
//    }
//}
