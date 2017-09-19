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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        mActivity = this.getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getLayoutID(), null);
        }
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity=this.getActivity();
        initData();
    }

    public abstract void initData(); // init() 方法不能写在 fragment onCreate()中，因为子类实现的时候mActivity还没初始化完毕.

    public abstract int getLayoutID();

}
