package com.foamtrace.photopicker;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/3/13.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {

    private Activity mActivity;
    private List<String> mlist = new ArrayList<>();
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public RecyAdapter(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setList(List<String> list) {
        this.mlist.clear();
        this.mlist.addAll(list);
        notifyDataSetChanged();
    }

    public String getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rec, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Glide.with(mActivity).load(mlist.get(position)).into(holder.imgIcon);
        holder.imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(holder.imgIcon, position);
                }
            }
        });
        holder.item_image_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取数据
                    mOnItemClickListener.onItemClick(holder.item_image_delete, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgIcon, item_image_delete;

        public ViewHolder(View view) {
            super(view);
            imgIcon = (ImageView) view.findViewById(R.id.rec_image);
            item_image_delete = (ImageView) view.findViewById(R.id.item_image_delete);
        }
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(ImageView view, int tag);
    }

    public OnRecyclerViewItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnRecyclerViewItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
