package com.keepmeposted.views.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.keepmeposted.base.BaseFragment;
import com.keepmeposted.R;

/**
 * Created by Dishan on 9/9/2016.
 */

public class TestFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_test, container, false);
    }

}
