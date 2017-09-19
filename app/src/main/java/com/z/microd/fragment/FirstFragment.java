package com.z.microd.fragment;

import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.z.microd.R;
import com.z.microd.api.MyHttpCallBack;
import com.z.microd.api.MyHttpManager;
import com.z.microd.utils.GlideImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private Banner banner;
    private View bannerView;
    boolean isFirst =true;
    @Override
    public void initData() {
        initBanner();
        if(isFirst==true)initPullTo();

    }
    public void initBanner(){
        bannerView = mActivity.getLayoutInflater().inflate(R.layout.header_viewpager,null);
        banner=(Banner)(bannerView.findViewById(R.id.header_banner));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> images= new ArrayList<>();
        images.add("https://camo.githubusercontent.com/72e7034bb9f3ed5e4d28c25a94adb22bb9e7cb98/687474703a2f2f6f63656835316b6b752e626b742e636c6f7564646e2e636f6d2f62616e6e65725f6578616d706c65312e706e67");
        images.add("https://camo.githubusercontent.com/078504c5723b59c8ebe787a059853fa1a603a381/687474703a2f2f6f63656835316b6b752e626b742e636c6f7564646e2e636f6d2f62616e6e65725f6578616d706c65322e706e67");
        images.add("https://camo.githubusercontent.com/fa591b0ea9768e3722fcd690cc97f987867573d9/687474703a2f2f6f63656835316b6b752e626b742e636c6f7564646e2e636f6d2f62616e6e65725f6578616d706c65332e706e67");
        images.add("https://camo.githubusercontent.com/44eeb7b3a25f1d34aa6d2ff7dd62c8f07af3b560/687474703a2f2f6f63656835316b6b752e626b742e636c6f7564646e2e636f6d2f62616e6e65725f6578616d706c65342e706e67");
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        List<String> titles= new ArrayList<>();
        titles.add("BIAo 1");
        titles.add("BIAo 2");
        titles.add("BIAo 3");
        titles.add("BIAo 4");
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mActivity,"LUN bo tu ",Toast.LENGTH_SHORT).show();
            }
        });
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        //banner.startAutoPlay();
        Log.d(TAG, "initBanner: 初始化完成 。。。");
    }
    public void initPullTo(){
        mPullRefreshListView = (PullToRefreshListView) mActivity.findViewById(R.id.pull_refresh_list);
        // 首先获取PullToRefreshListView包含的listview
        mListView = mPullRefreshListView.getRefreshableView();
        // 将viewpager的布局设置给listview的头条目
        mListView.addHeaderView(bannerView);

        // 停止listview的加载数据的操作
        //mPullRefreshListView.onRefreshComplete();

        // 给listView添加adapter
        mListView.setAdapter(listViewAdapter);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        mPullRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mActivity,"下拉刷新成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }, 2000);

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullRefreshListView.onRefreshComplete();
                        mActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(mActivity,"上拉刷新成功",Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }, 2000);
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
        isFirst=false;
    }

    @Override
    public int getLayoutID() {
        return R.layout.fragment_first;
    }
    ListAdapter listViewAdapter=new ListAdapter() {
        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int i) {
            return false;
        }

        @Override
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

        }

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int i) {
            return i;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            TextView textView = new TextView(mActivity);
            textView.setText("aaa");
            textView.setHeight(150);
            textView.setWidth(200);
            textView.setBackgroundColor(getResources().getColor(R.color.blue));
            return textView;
        }

        @Override
        public int getItemViewType(int i) {
            return 0;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }
    };

}
