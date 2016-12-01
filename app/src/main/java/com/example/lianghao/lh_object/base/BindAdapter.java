package com.example.lianghao.lh_object.base;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.ImageVideoDataLoadProvider;

/**
 * Created by LiangHao on 2016/12/1.
 */

public class BindAdapter {
    @BindingAdapter({"imageUrl"})
    public  static void  ImageVideo(ImageView view,
                                    String url){
        Glide.with(view.getContext())
                .load(url)
                .into(view);
    }


}
