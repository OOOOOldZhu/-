package com.z.microd.fragment;

import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.z.microd.R;
import com.z.microd.api.MyHttpCallBack;
import com.z.microd.api.MyHttpManager;

import java.io.IOException;
import java.util.LinkedList;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by z on 2017/9/17.
 */

public class FirstFragment extends BaseFragment {
    private LinkedList<String> mListItems;
    private PullToRefreshListView mPullRefreshListView;
    private ListView mListView;
    String TAG ="zhu";
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
                MyHttpManager.getInstance().doGet("www.baidu.com", new MyHttpCallBack() {
                    @Override
                    public void onBeforeRequest(Request request) {
                        Log.d(TAG, "onBeforeRequest: ");
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: ");
                    }

                    @Override
                    public void onResponse() {
                        Log.d(TAG, "onResponse: ");
                    }

                    @Override
                    public void onSuccess(Call response, Object t) {
                        Log.d(TAG, "onSuccess: "+t);
                    }

                    @Override
                    public void onError(Response response, String errorMsg) {
                        Log.d(TAG, "onError: ");
                    }
                });
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
