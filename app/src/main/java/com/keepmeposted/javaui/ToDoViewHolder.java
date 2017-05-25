package com.keepmeposted.javaui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.keepmeposted.R;

/**
 * Created by VISH_V on 9/11/2016.
 */

public class ToDoViewHolder  extends RecyclerView.ViewHolder{
    public TextView nameTxt;
    public ImageButton deleteImageButton;
    public ImageButton editImageButton;
    public CheckBox selectCheckBox;

    public ToDoViewHolder(View itemView) {
        super(itemView);

        nameTxt = (TextView)itemView.findViewById(R.id.nameTxt);
        deleteImageButton = (ImageButton)itemView.findViewById(R.id.imageButtonDelete);
        editImageButton = (ImageButton)itemView.findViewById(R.id.imageButtonEdit);
        selectCheckBox = (CheckBox)itemView.findViewById(R.id.checkBoxEditTodo);
    }
}

