package com.foamtrace.photopicker.intent;

import android.content.Context;
import android.content.Intent;

import com.foamtrace.photopicker.PhotoPreviewActivity;
import com.foamtrace.photopicker.WebViewActivity;

import java.util.ArrayList;

/**
 * 预览照片
 * Created by foamtrace on 2015/8/25.
 */
public class WebViewIntent extends Intent {

    public WebViewIntent(Context packageContext) {
        super(packageContext, WebViewActivity.class);
    }

    /**
     * 照片地址
     *
     * @param paths
     */
    public void setPhotoPaths(ArrayList<String> paths) {
        this.putStringArrayListExtra(PhotoPreviewActivity.EXTRA_PHOTOS, paths);
    }

    /**
     * 当前照片的下标
     *
     * @param currentItem
     */
    public void setCurrentItem(int currentItem) {
        this.putExtra(PhotoPreviewActivity.EXTRA_CURRENT_ITEM, currentItem);
    }
}
