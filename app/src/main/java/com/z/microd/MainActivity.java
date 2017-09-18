package com.z.microd;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.z.microd.fragment.BaseFragment;
import com.z.microd.fragment.FirstFragment;
import com.z.microd.fragment.FourFragment;
import com.z.microd.fragment.SecondFragment;
import com.z.microd.fragment.ThirdFragment;

public class MainActivity extends AppCompatActivity {
    FrameLayout container;
    Toolbar toolbar;
    TextView title;
    private LinearLayout first;
    private ImageView firstImage;
    private TextView firstText;
    private LinearLayout second;
    private ImageView secondimage;
    private TextView secondText;
    private LinearLayout third;
    private ImageView thirdimage;
    private TextView thirdtext;
    private LinearLayout four;
    private ImageView fourimage;
    private TextView fourtext;
    private BaseFragment firstFragment;
    private BaseFragment secondFragment;
    private BaseFragment thirdFragment;
    private BaseFragment fourFragment;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();


        initToolbar();

        initBottombar();

    }

    public void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        container = (FrameLayout) findViewById(R.id.container);
        title = (TextView) findViewById(R.id.title);

        first = (LinearLayout) findViewById(R.id.first);
        firstImage = (ImageView) findViewById(R.id.firstimage);
        firstText = (TextView) findViewById(R.id.firstext);

        second = (LinearLayout) findViewById(R.id.second);
        secondimage = (ImageView) findViewById(R.id.secondimage);
        secondText = (TextView) findViewById(R.id.secondtext);

        third = (LinearLayout) findViewById(R.id.third);
        thirdimage = (ImageView) findViewById(R.id.thirdimage);
        thirdtext = (TextView) findViewById(R.id.thirdtext);

        four = (LinearLayout) findViewById(R.id.four);
        fourimage = (ImageView) findViewById(R.id.fourimage);
        fourtext = (TextView) findViewById(R.id.fourtext);
        //bottomBar = (BottomBar) findViewById(R.id.bottombar);
        firstImage.setSelected(true);
        firstText.setSelected(true);
        fragmentManager = MainActivity.this.getSupportFragmentManager();
        firstFragment = new FirstFragment();
        secondFragment = new SecondFragment();
        thirdFragment = new ThirdFragment();
        fourFragment = new FourFragment();

        fragmentManager.beginTransaction().replace(R.id.container,firstFragment).commit();
    }

    public void onTabClicked(View view) {
        switch (view.getId()) {
            case R.id.first:
                fragmentManager.beginTransaction().replace(R.id.container,firstFragment).commit();
                firstImage.setSelected(true);
                firstText.setSelected(true);
                secondimage.setSelected(false);
                secondText.setSelected(false);
                thirdimage.setSelected(false);
                thirdtext.setSelected(false);
                fourimage.setSelected(false);
                fourtext.setSelected(false);
                break;
            case R.id.second:
                fragmentManager.beginTransaction().replace(R.id.container,secondFragment).commit();
                firstImage.setSelected(false);
                firstText.setSelected(false);
                secondimage.setSelected(true);
                secondText.setSelected(true);
                thirdimage.setSelected(false);
                thirdtext.setSelected(false);
                fourimage.setSelected(false);
                fourtext.setSelected(false);
                break;
            case R.id.third:
                fragmentManager.beginTransaction().replace(R.id.container,thirdFragment).commit();
                firstImage.setSelected(false);
                firstText.setSelected(false);
                secondimage.setSelected(false);
                secondText.setSelected(false);
                thirdimage.setSelected(true);
                thirdtext.setSelected(true);
                fourimage.setSelected(false);
                fourtext.setSelected(false);
                break;
            case R.id.four:
                fragmentManager.beginTransaction().replace(R.id.container,fourFragment).commit();
                firstImage.setSelected(false);
                firstText.setSelected(false);
                secondimage.setSelected(false);
                secondText.setSelected(false);
                thirdimage.setSelected(false);
                thirdtext.setSelected(false);
                fourimage.setSelected(true);
                fourtext.setSelected(true);
                break;
            default:
                break;
        }

        //Toast.makeText(MainActivity.this, "被点击了", Toast.LENGTH_SHORT).show();
    }

    public void initToolbar() {
        toolbar.setTitle("");
        title.setText("美科科技");
        toolbar.setBackgroundColor(getResources().getColor(R.color.bottombarbackgroud));
        setSupportActionBar(toolbar);
    }

    public void initBottombar() {
        /*bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {

            }
        });*/
    }
}
