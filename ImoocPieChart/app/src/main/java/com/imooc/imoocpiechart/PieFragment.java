package com.imooc.imoocpiechart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IPieDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */

public class PieFragment extends Fragment {

    private static final String DATA_KEY = "pie_fragment_data_key";
    private MonthBean mData;
    private PieChart mChart;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mData = arguments.getParcelable(DATA_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_pie, null);
        mChart = (PieChart) inflate.findViewById(R.id.pc_chart);
        initView();
        return inflate;
    }

    private void initView() {
        setData();
    }

    private void setData() {
        List<PieEntry> entrys = new ArrayList<>();
        for (int i = 0; i < mData.obj.size(); i++) {
            MonthBean.PieBean pieBean = mData.obj.get(i);
            entrys.add(new PieEntry(pieBean.value, i));
        }
        IPieDataSet dataSet = new PieDataSet(entrys, "");
        PieData pieData = new PieData(dataSet);
        mChart.setData(pieData);
    }

    public static PieFragment newInstance(MonthBean data) {
        
        Bundle args = new Bundle();
        args.putParcelable(DATA_KEY, data);

        PieFragment fragment = new PieFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
