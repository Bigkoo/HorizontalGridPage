package com.bigkoo.horizontalgridpage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * 横向网格布局适配器
 *
 * @param <T> 数据源类型
 * @author 吴昊
 */
public class PageGridAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<T> data;//转换后的数据
    private PageCallBack callBack;//回调接口
    private int itemWidth;//元素的宽度
    private int row, column;//行列数
    private int pageMargin;//页面间距

    public PageGridAdapter(PageCallBack callBack) {
        this(null, callBack);
    }

    public PageGridAdapter(ArrayList<T> data, PageCallBack callBack) {
        this.data = convert(data);
        this.callBack = callBack;
    }

    /**
     * 初始化参数
     *
     * @param pageBuilder 参数构建器
     */
    public void init(PageBuilder pageBuilder) {
        row = pageBuilder.getGrid()[0];
        column = pageBuilder.getGrid()[1];
        pageMargin = pageBuilder.getPageMargin();
    }

    /**
     * 横向布局下，默认的排列顺序是从上到下从左到右。这里需要转换成从左到右从上到下。
     * <p>
     * 例如2行4列的原始数据：
     * 1、3、5、7
     * 2、4、6、8
     * <p>
     * 转换后的数据
     * 1、2、3、4
     * 5、6、7、8
     *
     * @param origin 原始数据
     * @return 转换排列后的数据
     */
    private ArrayList<T> convert(ArrayList<T> origin) {
        if (origin == null) {
            return new ArrayList<>();
        }
        ArrayList<T> dest = new ArrayList<>();

        int pageSize = row * column;
        int pageCount = (int) Math.ceil(origin.size() / (double) pageSize);
        for (int p = 0; p < pageCount; p++) {
            for (int c = 0; c < column; c++) {
                for (int r = 0; r < row; r++) {
                    int index = c + r * column + p * pageSize;
                    if (index < origin.size()) {
                        dest.add(origin.get(index));
                    } else {
                        dest.add(null);//填充虚位做交换，保证数据满屏，后面过滤处理
                    }
                }
            }
        }
        return dest;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (itemWidth <= 0) {
            // 计算Item的宽度
            itemWidth = (parent.getMeasuredWidth() - pageMargin * 2) / column;
        }


        RecyclerView.ViewHolder holder = callBack.onCreateViewHolder(parent, viewType);
        holder.itemView.measure(0, 0);
        holder.itemView.getLayoutParams().width = itemWidth;
        holder.itemView.getLayoutParams().height = holder.itemView.getMeasuredHeight();
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (column == 1) {
            // 每个Item距离左右两侧各pageMargin
            holder.itemView.getLayoutParams().width = itemWidth + pageMargin * 2;
            holder.itemView.setPadding(pageMargin, 0, pageMargin, 0);
        } else {
            int m = position % (row * column);
            if (m < row) {
                // 每页左侧的Item距离左边pageMargin
                holder.itemView.getLayoutParams().width = itemWidth + pageMargin;
                holder.itemView.setPadding(pageMargin, 0, 0, 0);
            } else if (m >= row * column - row) {
                // 每页右侧的Item距离右边pageMargin
                holder.itemView.getLayoutParams().width = itemWidth + pageMargin;
                holder.itemView.setPadding(0, 0, pageMargin, 0);
            } else {
                // 中间的正常显示
                holder.itemView.getLayoutParams().width = itemWidth;
                holder.itemView.setPadding(0, 0, 0, 0);
            }
        }

        holder.itemView.setTag(position);
        setListener(holder);//设置监听

        if (position < data.size() && data.get(position) != null) {
            //将要显示的item交给回调处理
            holder.itemView.setVisibility(View.VISIBLE);
            callBack.onBindViewHolder(holder, position);
        } else {
            holder.itemView.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    /**
     * 设置监听
     *
     * @param holder 监听对象
     */
    private void setListener(RecyclerView.ViewHolder holder) {
        // 设置监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callBack.onItemClickListener(v, (Integer) v.getTag());
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                callBack.onItemLongClickListener(v, (Integer) v.getTag());
                return true;
            }
        });
    }

    /**
     * 获取数据源
     *
     * @return 数据源
     */
    public ArrayList<T> getData() {
        return data;
    }

    /**
     * 设置数据源
     *
     * @param data 数据源
     */
    public void setData(ArrayList<T> data) {
        this.data.clear();
        this.data.addAll(convert(data));
        notifyDataSetChanged();
    }

}
