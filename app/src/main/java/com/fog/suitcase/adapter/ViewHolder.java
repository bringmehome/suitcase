package com.fog.suitcase.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Custom common ViewHolder class
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private int position;
    private View convertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.position = position;
        mViews = new SparseArray<>();
        convertView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        convertView.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutId, int position) {
        if (convertView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        } else {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            holder.position = position;
            return holder;
        }
    }

    /**
     * Get view by viewID
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public int getPosition() {
        return position;
    }

    public View getConvertView() {
        return convertView;
    }

    /**
     * Set show text for TextView.
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * Get widget text content by widget id.
     *
     * @param viewId
     * @return
     */
    public String getText(int viewId) {
        TextView textView = getView(viewId);
        return textView.getText().toString();
    }

    /**
     * Set image resource for ImageView.
     *
     * @param viewId
     * @param resId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(resId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bm);
        return this;
    }

    public ViewHolder setImageURI(int viewId, Uri uri) {
        ImageView iv = getView(viewId);
        iv.setImageURI(uri);//ImageLoader.getInstance().loadImg(view,url);
        return this;
    }
}
