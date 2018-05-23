package com.bigkoo.horizontalgridpage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * 横向滑动页面
 *
 * @author wuhao
 */
public class HorizontalGridPage extends LinearLayout {

    PageGridView gridView;
    PageIndicatorView indicatorView;

    public HorizontalGridPage(Context context) {
        this(context, null);
    }

    public HorizontalGridPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalGridPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 初始化
     *
     * @param builder 参数构建器
     */
    public void init(PageBuilder builder) {
        setOrientation(LinearLayout.VERTICAL);//纵向排列

        if (builder == null) {
            builder = new PageBuilder.Builder().build();
        }

        int[] grid = builder.getGrid();
        int swipePercent = builder.getSwipePercent();
        gridView = new PageGridView(getContext(), grid, swipePercent);

        int indicatorSize = dip2px(builder.getIndicatorSize());
        int[] margins = {dip2px(builder.getIndicatorMargins()[0]), dip2px(builder.getIndicatorMargins()[1]),
                dip2px(builder.getIndicatorMargins()[2]), dip2px(builder.getIndicatorMargins()[3])};
        int[] indicatorRes = builder.getIndicatorRes();
        int gravity = builder.getIndicatorGravity();
        indicatorView = new PageIndicatorView(getContext(), indicatorSize, margins, indicatorRes, gravity);
        gridView.setIndicator(indicatorView);

        addView(gridView);
        if (builder.isShowIndicator()) {
            addView(indicatorView);
        } else {
            removeView(indicatorView);
        }
    }

    /**
     * 设置Adapter
     *
     * @param adapter 设置的Adapter
     */
    public void setAdapter(PageGridAdapter adapter) {
        gridView.setAdapter(adapter);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     *
     * @param dpValue 要转换的dp值
     */
    private int dip2px(int dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
