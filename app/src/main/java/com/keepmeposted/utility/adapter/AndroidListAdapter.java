package com.keepmeposted.utility.adapter;

/**
 * Created by Shazna on 10/31/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.keepmeposted.R;

import java.util.ArrayList;

public class AndroidListAdapter extends ArrayAdapter<String> {
    ArrayAdapter<String> adapter;
    Integer[] imagesId;
    Context context;
    ArrayList<String> arrayList;

    public AndroidListAdapter(Activity context, Integer[] imagesId, ArrayAdapter<String> adapter, String[] textListView, ArrayList<String> arrayList) {
        super(context, R.layout.activity_budgetplan, textListView);
        this.adapter = adapter;
        this.imagesId = imagesId;
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public View getView(int i, View viewRow, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(viewRow == null)
        {
            viewRow = layoutInflater.inflate(R.layout.income_card, viewGroup, false);
        }

        TextView mtextView = (TextView) viewRow.findViewById(R.id.incomenameView);
       // ListView androidListView = (ListView) viewRow.findViewById(R.id.custom_listview_example);
        //androidListView.setAdapter(adapter);
       // ImageView mimageView = (ImageView) viewRow.findViewById(R.id.image_view);


      //  for (int g=0; g<adapter.getCount() ;g++) {

                mtextView.setText(adapter.getItem(i));
        i++;
                adapter.notifyDataSetChanged();

       // }

        //mtextView.setText(arrayList.get(i));



        //  mimageView.setImageResource(imagesId[i]);
        return viewRow;
    }

    public View myaddview(int m)
    {
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewRow = layoutInflater.inflate(R.layout.income_card, null,
                true);
        TextView mtextView = (TextView) viewRow.findViewById(R.id.incomenameView);
       while (m <= adapter.getCount()){
            mtextView.setText(adapter.getItem(m));
        }
        return viewRow;

    }

}
