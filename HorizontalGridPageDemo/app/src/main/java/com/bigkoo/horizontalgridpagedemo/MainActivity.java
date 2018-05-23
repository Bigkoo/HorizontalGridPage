package com.bigkoo.horizontalgridpagedemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.horizontalgridpage.PageCallBack;
import com.bigkoo.horizontalgridpage.HorizontalGridPage;
import com.bigkoo.horizontalgridpage.PageBuilder;
import com.bigkoo.horizontalgridpage.PageGridAdapter;

import java.util.ArrayList;

/**
 * 横向网格布局演示
 *
 * @author 吴昊
 */
public class MainActivity extends AppCompatActivity implements PageCallBack {

    PageGridAdapter adapter;//适配器
    private ArrayList<String> data;//数据
    int selectPos;//选中位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    /**
     * 初始化视图
     */
    private void initView() {
        PageBuilder pageBuilder = new PageBuilder.Builder()
                .setGrid(3, 4)//设置网格
                .setPageMargin(10)//页面边距
                .setIndicatorMargins(5, 5, 5, 5)//设置指示器间隔
                .setIndicatorSize(10)//设置指示器大小
                .setIndicatorRes(android.R.drawable.presence_invisible,
                        android.R.drawable.presence_online)//设置指示器图片资源
                .setIndicatorGravity(Gravity.CENTER)//设置指示器位置
                .setSwipePercent(50)//设置翻页滑动距离百分比（1-100）
                .setShowIndicator(true)//设置显示指示器
                .build();

        HorizontalGridPage pageView = findViewById(R.id.pageView);
        pageView.init(pageBuilder);

        adapter = new PageGridAdapter<>(this);
        adapter.init(pageBuilder);
        pageView.setAdapter(adapter);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        /**
         * 演示数据
         */
        data = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            data.add(String.valueOf(i));
        }
        adapter.setData(data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new MyHolder(view) {
        };
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //注意：因为data经过转换，所以此处不能使用data.get(position)而要使用adapter.getData().get(position)
        ((MyHolder) holder).tv.setText(adapter.getData().get(position).toString());
        ((MyHolder) holder).update(position);
    }

    @Override
    public void onItemClickListener(View view, int position) {
        updatePosition(position);
    }

    @Override
    public void onItemLongClickListener(View view, int position) {
        //TODO
    }

    /**
     * 自定义ViewHolder来更新item，这里这是演示更新选中项的背景
     */
    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.text);
        }

        void update(int position) {
            if (position == selectPos) {
                tv.setBackgroundColor(Color.BLACK);
            } else {
                tv.setBackgroundResource(R.color.colorPrimary);
            }
        }

    }

    /**
     * 更新选中项位置
     *
     * @param position 选中项位置
     */
    private void updatePosition(int position) {
        adapter.notifyItemChanged(selectPos);
        selectPos = position;
        adapter.notifyItemChanged(position);
    }

}
