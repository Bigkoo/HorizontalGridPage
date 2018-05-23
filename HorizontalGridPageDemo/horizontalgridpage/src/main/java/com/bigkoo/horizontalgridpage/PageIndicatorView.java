package com.bigkoo.horizontalgridpage;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * 页面翻页指示器
 *
 * @author 吴昊
 */
public class PageIndicatorView extends LinearLayout {

    private ArrayList<View> indicatorViews; // 指示器容器
    private int indicatorSize;  //指示器大小
    private int[] margins;    //指示器间距
    private int[] indicatorRes;//指示器资源

    public PageIndicatorView(Context context, int indicatorSize, int[] margins, int[] indicatorRes, int gravity) {
        super(context);
        this.indicatorSize = indicatorSize;
        this.margins = margins;
        this.indicatorRes = indicatorRes;
        setGravity(gravity);
        setOrientation(HORIZONTAL);//横向布局
    }

    /**
     * 初始化指示器，默认选中第一页
     *
     * @param count 指示器数量，等同页数
     */
    public void initIndicator(int count) {
        if (indicatorViews == null) {
            indicatorViews = new ArrayList<>();
        } else {
            indicatorViews.clear();
            removeAllViews();
        }

        LayoutParams params = new LayoutParams(indicatorSize, indicatorSize);
        params.setMargins(margins[0], margins[1], margins[2], margins[3]);
        for (int i = 0; i < count; i++) {
            View view = new View(getContext());
            view.setBackgroundResource(indicatorRes[0]);
            addView(view, params);
            indicatorViews.add(view);
        }
        //默认选中第一页
        if (indicatorViews.size() > 0) {
            indicatorViews.get(0).setBackgroundResource(indicatorRes[1]);
        }
    }

    /**
     * 设置选中页
     *
     * @param currentPage 当前页
     */
    public void update(int currentPage) {
        for (int i = 0; i < indicatorViews.size(); i++) {
            if (i == currentPage) {
                indicatorViews.get(i).setBackgroundResource(indicatorRes[1]);
            } else {
                indicatorViews.get(i).setBackgroundResource(indicatorRes[0]);
            }
        }
    }
}
