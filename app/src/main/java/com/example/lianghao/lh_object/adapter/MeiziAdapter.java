package com.example.lianghao.lh_object.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

import com.android.databinding.library.baseAdapters.BR;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.lianghao.lh_object.R;
import com.example.lianghao.lh_object.entity.MeiziBean;

import java.util.List;

import static com.example.lianghao.lh_object.adapter.MeiziAdapter.*;

/**
 * Created by LiangHao on 2016/11/30.
 */




public class MeiziAdapter extends BaseQuickAdapter<MeiziBean, MeiziAdapter.MeiziBeanViewHolder> {


    public MeiziAdapter(int layoutResId, List<MeiziBean> data) {
        super(layoutResId, data);

    }

    @Override
    protected void convert(MeiziBeanViewHolder helper, MeiziBean item) {
        System.out.println("内容数显");
        ViewDataBinding binding = helper.getBinding();
//        binding.setVariable(, item);
        binding.setVariable(BR.meizi,item);

        binding.executePendingBindings();
    }

    @Override
    protected MeiziBeanViewHolder createBaseViewHolder(View view) {
        return new MeiziBeanViewHolder(view);
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent, false);
        if (binding == null) {
            return super.getItemView(layoutResId, parent);
        }
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }

    public class MeiziBeanViewHolder extends BaseViewHolder {

        public MeiziBeanViewHolder(View view) {
            super(view);
        }

        public ViewDataBinding getBinding() {
            return (ViewDataBinding)getConvertView().getTag(R.id.BaseQuickAdapter_databinding_support);
        }
    }
}