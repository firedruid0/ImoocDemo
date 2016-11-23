package com.imooc.imoocpiechart;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.utils.Utils;

/**
 *
 */

public class PieChart extends com.github.mikephil.charting.charts.PieChart {
    public PieChart(Context context) {
        super(context);
    }

    public PieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //int size = (int) Utils.convertDpToPixel(50f);
        int size = (int) Utils.convertDpToPixel(MeasureSpec.getSize(widthMeasureSpec));
        setMeasuredDimension(
                Math.max(getSuggestedMinimumWidth(),
                        resolveSize(size,
                                widthMeasureSpec)),
//                Math.max(getSuggestedMinimumHeight(),
//                        resolveSize(size,
//                                heightMeasureSpec)));
                Math.max(getSuggestedMinimumHeight(),
                        resolveSize(size,
                                widthMeasureSpec)));
    }
}
