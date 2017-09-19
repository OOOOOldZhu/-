package com.z.microd;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.z.microd.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    Activity mActivity ;
    private Banner banner;
    private View bannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mActivity=this;
        initBanner();
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
        banner.setDelayTime(2000);
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
        banner.startAutoPlay();
    }
}
