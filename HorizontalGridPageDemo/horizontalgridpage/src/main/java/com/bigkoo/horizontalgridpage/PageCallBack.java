package com.bigkoo.horizontalgridpage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 回调处理
 *
 * @author 吴昊
 */
public interface PageCallBack {

    /**
     * 创建ViewHolder
     *
     * @param parent   容器
     * @param viewType view类型
     * @return ViewHolder
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定数据到ViewHolder
     *
     * @param holder   绑定的holder
     * @param position holder的位置
     */
    void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * 点击事件
     *
     * @param view     点击的view
     * @param position view的位置
     */
    void onItemClickListener(View view, int position);

    /**
     * 长按事件
     *
     * @param view     长按的view
     * @param position view的位置
     */
    void onItemLongClickListener(View view, int position);
}
