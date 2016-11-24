package com.imooc.imoocpiechart;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vpMain;

    private String mJson = "[{\"date\":\"2016年5月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":21},{\"title\":\"其他\",\"value\":45}]}," +
            "{\"date\":\"2016年6月\",\"obj\":[{\"title\":\"外卖\",\"value\":32}," +
            "{\"title\":\"娱乐\",\"value\":22},{\"title\":\"其他\",\"value\":42}]}," +
            "{\"date\":\"2016年7月\",\"obj\":[{\"title\":\"外卖\",\"value\":34}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":24}]}," +
            "{\"date\":\"2016年8月\",\"obj\":[{\"title\":\"外卖\",\"value\":145}," +
            "{\"title\":\"娱乐\",\"value\":123},{\"title\":\"其他\",\"value\":124}]}]";
    private ArrayList<MonthBean> mData;
    private Button btPre;
    private Button btNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vpMain = (ViewPager) findViewById(R.id.vp_main);
        btPre = (Button) findViewById(R.id.bt_pre);
        btNext = (Button) findViewById(R.id.bt_next);

        btPre.setOnClickListener(this);
        btNext.setOnClickListener(this);

        initData();
        initView();
    }

    private void initData() {
        Gson gson = new Gson();
        mData = gson.fromJson(mJson, new TypeToken<ArrayList<MonthBean>>(){}.getType());
    }

    private void initView() {
        vpMain.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return PieFragment.newInstance(mData.get(position));
            }

            @Override
            public int getCount() {
                return mData.size();
            }
        });
        updateJumpText();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_next:
                if (vpMain.getCurrentItem() != vpMain.getAdapter().getCount()){
                    vpMain.setCurrentItem(vpMain.getCurrentItem()+1);
                }
                break;
            case R.id.bt_pre:
                if (vpMain.getCurrentItem() != 0){
                    vpMain.setCurrentItem(vpMain.getCurrentItem()-1);
                }
        }
        updateJumpText();
    }

    private void updateJumpText() {
        if (vpMain.getCurrentItem() != vpMain.getAdapter().getCount()-1) {
            btNext.setText(handleText(mData.get(vpMain.getCurrentItem()+1).date));
        } else {
            btNext.setText("没有了！");
        }
        if (vpMain.getCurrentItem() != 0) {
            btPre.setText(handleText(mData.get(vpMain.getCurrentItem()-1).date));
        } else {
            btPre.setText("没有了！");
        }
    }

    private CharSequence handleText(String date) {
        return date.substring(date.indexOf("年")+1);
    }
}
