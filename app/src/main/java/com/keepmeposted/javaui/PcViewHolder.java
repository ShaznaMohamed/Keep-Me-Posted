package com.keepmeposted.javaui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.keepmeposted.R;

/**
 * Created by chamal on 9/17/2016.
 */

public class PcViewHolder extends RecyclerView.ViewHolder{

    public TextView nameTxtPC;
    public TextView qtyLoTxtPC;
    public TextView qtyFullTxtPC;
    public ImageButton deleteImageButtonPC;
    public ImageButton editImageButtonPC;
    public CheckBox selectCheckBoxPC;

    public PcViewHolder(View itemView) {
        super(itemView);

        nameTxtPC = (TextView)itemView.findViewById(R.id.nameTxtPC);
        //qtyLoTxtPC = (TextView)itemView.findViewById(R.id.qtyLoTxtPC);
        //qtyFullTxtPC = (TextView)itemView.findViewById(R.id.qtyHiTxtPC);
        deleteImageButtonPC = (ImageButton)itemView.findViewById(R.id.imageButtonDeletePC);
        editImageButtonPC = (ImageButton)itemView.findViewById(R.id.imageButtonEditPC);
        //selectCheckBoxPC = (CheckBox)itemView.findViewById(R.id.checkBoxEditPC);

    }
}
