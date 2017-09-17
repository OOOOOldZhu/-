package com.z.microd.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by z on 2017/9/17.
 */

public abstract class BaseFragment extends android.support.v4.app.Fragment {
    View rootView;
    Activity mActivity ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mActivity = this.getActivity();
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutID(), null);
        }
        init();

        return rootView;
    }


    public abstract void init();

    public abstract int getLayoutID();

}
