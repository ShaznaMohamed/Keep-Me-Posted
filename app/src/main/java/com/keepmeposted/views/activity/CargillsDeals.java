package com.keepmeposted.views.activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.keepmeposted.R;
import com.keepmeposted.model.Deal;
import com.keepmeposted.utility.adapter.DealsAdapter;

import java.util.ArrayList;
import java.util.List;

public class CargillsDeals extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DealsAdapter adapter;
    private List<Deal> dealList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deals);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        dealList = new ArrayList<>();
        adapter = new DealsAdapter(this, dealList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new CargillsDeals.GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        showDeal();
    }
    /*

    * method:showDeal
    * description:Adding deals to an array to display
    */
    private void showDeal() {
        int[] covers = new int[]{
                R.drawable.cc,
                R.drawable.c1,
                R.drawable.c2,
                R.drawable.c3,
                R.drawable.c4,
                R.drawable.c5,
        };

        Deal a = new Deal("Cargills Savings", "Today", covers[0]);
        dealList.add(a);

        a = new Deal("Cargills Deals", "Tomorrow", covers[1]);
        dealList.add(a);
        a = new Deal("Cargills Savings", "Week ago", covers[2]);
        dealList.add(a);

        a = new Deal("Cargills Savings","Today", covers[3]);
        dealList.add(a);
        a = new Deal("Cargills Loyality ", "Today", covers[4]);
        dealList.add(a);
        a = new Deal("Cargills Loyality ", "Tomorrow", covers[5]);
        dealList.add(a);

        adapter.notifyDataSetChanged();
    }

    /**
     * class:GridSpacingItemDecoration
     * description:RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * method:dpToPx
     * description:Converting dp to pixel
     * parameters:dp
     * @return:integer
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}
