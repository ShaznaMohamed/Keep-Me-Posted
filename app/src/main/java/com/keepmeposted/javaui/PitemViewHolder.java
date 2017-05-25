package com.keepmeposted.javaui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.keepmeposted.R;

/**
 * Created by chamal on 9/22/2016.
 */

public class PitemViewHolder extends RecyclerView.ViewHolder{

    public TextView nameTxtPI;
    public TextView qtyLoTxtPI;
    public TextView qtyFullTxtPI;
    public TextView deleteImageButtonPI;
    public TextView editImageButtonPI;
    public CheckBox selectCheckBoxPI;
    public TextView priceTxtPI;
    public TextView expDateTxtPI;

    public PitemViewHolder(View itemView) {
        super(itemView);

        nameTxtPI = (TextView)itemView.findViewById(R.id.nameTxtPI);
        qtyLoTxtPI = (TextView)itemView.findViewById(R.id.qtyLoTxtPI);
        //qtyFullTxtPI = (TextView)itemView.findViewById(R.id.qtyHiTxtPI);
        deleteImageButtonPI = (TextView)itemView.findViewById(R.id.imageButtonDeletePI);
        editImageButtonPI = (TextView)itemView.findViewById(R.id.imageButtonEditPI);
        selectCheckBoxPI = (CheckBox)itemView.findViewById(R.id.checkBoxEditPI);
        priceTxtPI = (TextView)itemView.findViewById(R.id.priceTxtPI);
        expDateTxtPI = (TextView)itemView.findViewById(R.id.expDateTxtPI);

    }
}
