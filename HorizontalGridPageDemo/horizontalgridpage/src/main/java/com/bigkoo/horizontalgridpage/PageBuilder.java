package com.bigkoo.horizontalgridpage;

import android.view.Gravity;

/**
 * 页面配置文件，参数构建器
 *
 * @author 吴昊
 */
public class PageBuilder {

    private int indicatorSize; // 指示器的大小（dp）
    private int[] indicatorMargins; // 指示器间距（dp）
    private int[] indicatorRes;//指示器非当前页资源
    private int indicatorGravity;//指示器位置
    private boolean showIndicator;//是否显示指示器
    private int[] grid;//网格，即行列数
    private int pageMargin; // 页间距
    private int swipePercent;//滑动有效距离

    private PageBuilder(Builder builder) {
        this.indicatorSize = builder.indicatorSize;
        this.indicatorMargins = builder.indicatorMargins;
        this.indicatorRes = builder.indicatorRes;
        this.indicatorGravity = builder.indicatorGravity;
        this.pageMargin = builder.pageMargin;
        this.grid = builder.grid;
        this.swipePercent = builder.swipePercent;
        this.showIndicator = builder.showIndicator;
    }

    public int getIndicatorSize() {
        return indicatorSize;
    }

    public int[] getIndicatorMargins() {
        return indicatorMargins;
    }

    public int[] getIndicatorRes() {
        return indicatorRes;
    }

    public int getIndicatorGravity() {
        return indicatorGravity;
    }

    public int getPageMargin() {
        return pageMargin;
    }

    public int[] getGrid() {
        return grid;
    }

    public int getSwipePercent() {
        return swipePercent;
    }

    public boolean isShowIndicator() {
        return showIndicator;
    }

    public static class Builder {

        //默认参数
        private int indicatorSize = 10; // 指示器的大小（dp）
        private int[] indicatorMargins = {5, 5, 5, 5}; // 指示器间距（dp）
        private int[] indicatorRes = {android.R.drawable.presence_invisible,
                android.R.drawable.presence_online};//指示器资源
        private int indicatorGravity = Gravity.CENTER;//指示器位置
        private int pageMargin = 0; //页间距
        private int[] grid = {3, 4};//网格，即行列数
        private int swipePercent = 50;//翻页百分比
        private boolean showIndicator = true;//是否显示指示器

        /**
         * 配置翻页指示器大小
         *
         * @param size 翻页指示器大小
         * @return 构建器
         */
        public Builder setIndicatorSize(int size) {
            indicatorSize = size;
            return this;
        }

        /**
         * 配置翻页指示器间距
         *
         * @param left   左间距
         * @param top    上间距
         * @param right  有间距
         * @param bottom 下间距
         * @return 构建器
         */
        public Builder setIndicatorMargins(int left, int top, int right, int bottom) {
            indicatorMargins[0] = left;
            indicatorMargins[1] = top;
            indicatorMargins[2] = right;
            indicatorMargins[3] = bottom;
            return this;
        }

        /**
         * 配置翻页指示器资源
         *
         * @param normal 非当前页指示器资源
         * @param focus  当前页指示器资源
         * @return 构建器
         */
        public Builder setIndicatorRes(int normal, int focus) {
            indicatorRes[0] = normal;
            indicatorRes[1] = focus;
            return this;
        }

        /**
         * 配置翻页指示器位置
         *
         * @param gravity 翻页指示器位置
         * @return 构建器
         */
        public Builder setIndicatorGravity(int gravity) {
            indicatorGravity = gravity;
            return this;
        }

        /**
         * 设置页面间距
         *
         * @param pageMargin 页面间距
         * @return 构建器
         */
        public Builder setPageMargin(int pageMargin) {
            this.pageMargin = pageMargin;
            return this;
        }

        /**
         * 设置网格，几行几列
         *
         * @param row    网格行数
         * @param column 网格列数
         * @return 构建器
         */
        public Builder setGrid(int row, int column) {
            grid[0] = row;
            grid[1] = column;
            return this;
        }

        /**
         * 设置翻页滑动距离百分比，达到有效距离后翻页（1-100）
         *
         * @param swipePercent 翻页的有效百分比
         * @return 构建器
         */
        public Builder setSwipePercent(int swipePercent) {
            this.swipePercent = swipePercent;
            return this;
        }

        /**
         * 设置是否使用分页指示器
         *
         * @param showIndicator true为使用，false为不使用
         * @return 构建器
         */
        public Builder setShowIndicator(boolean showIndicator) {
            this.showIndicator = showIndicator;
            return this;
        }

        public PageBuilder build() {
            return new PageBuilder(this);
        }
    }
}
