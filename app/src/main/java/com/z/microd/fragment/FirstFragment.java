package com.z.microd.fragment;

import android.database.DataSetObserver;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flyco.tablayout.SegmentTabLayout;
import com.flyco.tablayout.header.SimpleCardFragment;
import com.flyco.tablayout.header.ViewFindUtils;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.z.microd.R;
import com.z.microd.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by z on 2017/9/17.
 */

public class FirstFragment extends BaseFragment {
    private LinkedList<String> mListItems;
    private PullToRefreshListView mPullRefreshListView;
    private ListView mListView;
    String TAG ="zhu";
    private Banner banner;
    private View headerView;
    boolean isFirst =true;
    private String[] mTitles;
    private String[] mTitles1;

    @Override
    public void initData() {
        initView();
        initHeader();
        initBanner();
        if(isFirst==true)initPullTo();

    }
    public void initView(){
        mPullRefreshListView = (PullToRefreshListView) mActivity.findViewById(R.id.pull_refresh_list);
        // 首先获取PullToRefreshListView包含的listview
        mListView = mPullRefreshListView.getRefreshableView();
    }
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    public void initHeader(){
        mFragments = new ArrayList<>();
        mTitles = new String[]{"mCookie","Microduino"};
        mTitles1 = new String[]{"什么是mcookie套件？","What's the Microduino Series about?"};

        for (String title : mTitles1) {
            mFragments.add(SimpleCardFragment.getInstance("" + title));
        }
        headerView = mActivity.getLayoutInflater().inflate(R.layout.header,null);
        final SegmentTabLayout tab = ViewFindUtils.find(headerView, R.id.tab);
        tab.setTabData(mTitles);
        //显示未读红点
        tab.showDot(2);
        final ViewPager viewPager = ViewFindUtils.find(headerView, R.id.header_viewpager);
        viewPager.setAdapter(new MyPagerAdapter(mActivity.getSupportFragmentManager()));
        tab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }
            @Override
            public void onTabReselect(int position) {
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setCurrentItem(1);
    }
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public int getCount() {
            return 2;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
    public void initBanner(){
        //headerView = mActivity.getLayoutInflater().inflate(R.layout.header,null);
        banner=(Banner)(headerView.findViewById(R.id.header_banner));
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        List<String> images= new ArrayList<>();
        images.add("http://oss.microduino.cn/resources/index/pages/33.png");
        images.add("http://oss.microduino.cn/resources/index/pages/22.png");
        images.add("http://microduinoinc.com/wp-content/uploads/2017/04/bannerB-top.png");
        images.add("http://oss.microduino.cn/resources/index/microduino-carousel/1.jpg");
        images.add("http://microduinoinc.com/wp-content/uploads/2017/04/bannerA-top2.png");
        banner.setImages(images);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        List<String> titles= new ArrayList<>();
        titles.add("BIAo 1");
        titles.add("BIAo 2");
        titles.add("BIAo 3");
        titles.add("BIAo 4");
        titles.add("BIAo 5");
        banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(3000);
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

        // 将viewpager的布局设置给listview的头条目
        mListView.addHeaderView(headerView);

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
