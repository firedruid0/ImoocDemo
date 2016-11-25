package com.imooc.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.imooc.viewpagerindicator.R;

import java.util.List;

/**
 *
 */

public class ViewPagerIndicator extends LinearLayout {

    private Paint mPaint;

    private Path mPath;

    private int mTriangleWidth;

    private int mTriangleHeight;

    private static final float RADIO_TRIANGLE_WIDTH = 1/6f;

    private int mInitTranslationX;

    private int mTranslationX;

    private int mTabVisibleCount;

    private static final int COUNT_DEFAULT_TAB = 4;

    private List<String> mTitles;

    public ViewPagerIndicator(Context context) {
        super(context, null);
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);

        //获取可见Tab数量
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
        mTabVisibleCount = a.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);
        if ((mTabVisibleCount < 0)) {
            mTabVisibleCount = COUNT_DEFAULT_TAB;
        }
        a.recycle();

        //初始化画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.parseColor("#ffffffff"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setPathEffect(new CornerPathEffect(3));
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.save();
        canvas.translate(mInitTranslationX + mTranslationX, getHeight()+2);
        canvas.drawPath(mPath, mPaint);
        canvas.restore();

        super.dispatchDraw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mTriangleWidth = (int) (RADIO_TRIANGLE_WIDTH * w / mTabVisibleCount);
        mInitTranslationX = w / mTabVisibleCount / 2 -mTriangleWidth / 2 ;

        initTriangle();
    }

    //xml加载完成
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        int cCount = getChildCount();
        if (cCount == 0){
            return;
        }
        for (int i = 0; i < cCount; i++) {
            View view = getChildAt(i);
            LayoutParams lp = (LayoutParams) view.getLayoutParams();
            lp.weight = 0;
            lp.width = getScreenWidth()/mTabVisibleCount;
            view.setLayoutParams(lp);
        }
    }

    //获得屏幕宽度
    private int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    private void initTriangle() {

        mTriangleHeight = mTriangleWidth / 2;

        mPath = new Path();
        mPath.moveTo(0, 0);
        mPath.lineTo(mTriangleWidth, 0);
        mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
        mPath.close();

    }

    public void scroll(int position, float offset) {
        int tabWidth = getWidth() / mTabVisibleCount;
        mTranslationX = (int) (tabWidth * (offset + position));

        //容器移动,当tab处于移动至最后一个时
        if (position >= (mTabVisibleCount - 2) && offset > 0 && getChildCount() > mTabVisibleCount){
            if (mTabVisibleCount != 1){
                this.scrollTo((position - (mTabVisibleCount - 2)) * tabWidth + (int)(tabWidth * offset), 0);
            } else {
                this.scrollTo(position * tabWidth + (int) (tabWidth * offset), 0);
            }

        }

        invalidate();
    }

    public void setTabItemTitles(List<String> titles) {
        if (titles != null && titles.size() > 0) {
            this.removeAllViews();
            mTitles = titles;
            for (String title : mTitles) {
                addView(genetageTextView(title));
            }
        }
    }

    private static final int COLOR_TEXT_NORMAL = 0x77ffffff;

    private View genetageTextView(String title) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setText(title);
        tv.setGravity(Gravity.CENTER);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tv.setTextColor(COLOR_TEXT_NORMAL);
        tv.setLayoutParams(lp);
        return tv;
    }
}
