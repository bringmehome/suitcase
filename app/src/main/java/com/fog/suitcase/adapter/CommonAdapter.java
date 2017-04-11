package com.fog.suitcase.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected List<T> datas;
    protected Context context;
    protected LayoutInflater mInflater;
    protected int layoutId;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Returns the number of elements in this {@code List}.
     */
    @Override
    public int getCount() {
        return datas.size();
    }

    /**
     * Returns the element at the specified location in this {@code List}.
     */
    @Override
    public T getItem(int position) {
        return datas.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get show view in item.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context, convertView, parent,
                layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    /**
     * @return 返回false可禁止ListView中item被点击
     */
    @Override
    public boolean areAllItemsEnabled() {
        return super.areAllItemsEnabled();
//		return false;
    }

    /**
     * @param position
     * @return 返回false可禁止ListView中item被点击
     */
    @Override
    public boolean isEnabled(int position) {
        return super.isEnabled(position);
//		return false;
    }


    public abstract void convert(ViewHolder holder, T t);
}

