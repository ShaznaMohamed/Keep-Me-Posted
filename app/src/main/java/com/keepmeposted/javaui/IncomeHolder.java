package com.keepmeposted.javaui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.keepmeposted.R;

/**
 * Created by Shazna on 10/15/2016.
 */

public class IncomeHolder extends RecyclerView.ViewHolder{

    public TextView incomeNameView;
    public TextView incomeamountView;
    public ImageButton editIB;
    public ImageButton deleteIB;

    public IncomeHolder(View itemView){
        super(itemView);

        incomeNameView = (TextView)itemView.findViewById(R.id.incomenameView);
        incomeamountView = (TextView)itemView.findViewById(R.id.incomeamountView);
        deleteIB = (ImageButton)itemView.findViewById(R.id.imageButtonDeleteIncome);
        editIB = (ImageButton)itemView.findViewById(R.id.imageButtonEditIncome);

    }
}
