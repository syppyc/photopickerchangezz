package com.foamtrace.photopicker;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by donglua on 15/6/21.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private PhotoPickerActivity mActivity;

    public interface PhotoViewClickListener {
        void OnPhotoTapListener(View view, float v, float v1);
    }

    public PhotoViewClickListener listener;

    private List<String> paths = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public ImagePagerAdapter(PhotoPickerActivity mActivity, Context mContext, List<String> paths) {
        this.mActivity = mActivity;
        this.mContext = mContext;
        this.paths = paths;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    public void setPhotoViewClickListener(PhotoViewClickListener listener) {
        this.listener = listener;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_imagepreview, container, false);

        PhotoView imageView = (PhotoView) itemView.findViewById(R.id.iv_pager);
        TextView itme_setup = (TextView) itemView.findViewById(R.id.itme_setup);
        final ImageView itme_flags = (ImageView) itemView.findViewById(R.id.itme_flags);

        final String path = paths.get(position);
        Glide.with(mContext)
                .load(path)
                .error(R.mipmap.default_error)
                .crossFade()
                .into(imageView);

        if (mActivity.getData().contains(paths.get(position))) {
            // 设置选中状态
            itme_flags.setImageResource(R.mipmap.btn_selected);
        }

        imageView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                if (listener != null) {
                    listener.OnPhotoTapListener(view, v, v1);
                }
            }
        });
        itme_flags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image image = new Image();
                image.path = paths.get(position);
                mActivity.SelectPhotos(position + 1, image);
                notifyDataSetChanged();
            }
        });
        itme_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Image image = new Image();
                image.path = paths.get(position);
                mActivity.SelectPhoto(true, position, image);
                notifyDataSetChanged();
            }
        });

        container.addView(itemView);

        return itemView;
    }


    @Override
    public int getCount() {
        return paths.size();
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

}
