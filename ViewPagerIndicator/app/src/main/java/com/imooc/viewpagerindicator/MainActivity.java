package com.imooc.viewpagerindicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.imooc.view.ViewPagerIndicator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends FragmentActivity {

    private ViewPagerIndicator mIndicator;
    private ViewPager mViewPager;

    private List<String> mTitles = Arrays.asList("短信1","收藏2","推荐3", "短信4","收藏5","推荐6", "短信7","收藏8","推荐9");
    private List<VpSimpleFragment> mContents = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();

        mViewPager.setAdapter(mAdapter);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mIndicator.scroll(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initDatas() {
        for (String title : mTitles) {
            VpSimpleFragment fragment = VpSimpleFragment.newInstance(title);
            mContents.add(fragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mContents.get(position);
            }

            @Override
            public int getCount() {
                return mContents.size();
            }
        };
    }

    private void initViews() {
        mIndicator = (ViewPagerIndicator) findViewById(R.id.id_indicatot);
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
    }
}
