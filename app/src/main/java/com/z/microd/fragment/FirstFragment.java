package com.z.microd.fragment;

import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.z.microd.R;

import java.util.LinkedList;

/**
 * Created by z on 2017/9/17.
 */

public class FirstFragment extends BaseFragment {
    private LinkedList<String> mListItems;
    private PullToRefreshListView mPullRefreshListView;
    private ListView mListView;

    @Override
    public void init() {
        mPullRefreshListView = (PullToRefreshListView) mActivity.findViewById(R.id.pull_refresh_list);
        // 首先获取PullToRefreshListView包含的listview
        mListView = mPullRefreshListView.getRefreshableView();
        // 将viewpager的布局设置给listview的头条目
        // mListView.addHeaderView();

        // 停止listview的加载数据的操作
        //mPullRefreshListView.onRefreshComplete();

        // 给listView添加adapter
        // mListView.setAdapter(listViewAdapter);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
        mPullRefreshListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
        mPullRefreshListView.setOnLastItemVisibleListener(new PullToRefreshBase.OnLastItemVisibleListener(){
            @Override
            public void onLastItemVisible() {

            }
        });
    }


    @Override
    public int getLayoutID() {
        return R.layout.fragment_first;
    }

}
