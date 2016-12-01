package com.example.lianghao.lh_object;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.lianghao.lh_object.adapter.MeiziAdapter;
import com.example.lianghao.lh_object.api.ApiManage;
import com.example.lianghao.lh_object.base.BaseBean;
import com.example.lianghao.lh_object.databinding.LayoutRecyBinding;
import com.example.lianghao.lh_object.entity.MeiziBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by LiangHao on 2016/11/3.
 */

public class ViewActivity extends AppCompatActivity implements BaseQuickAdapter.RequestLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {
    LayoutRecyBinding mLayoutRecyBinding;
    MeiziAdapter mMeiziAdapter;
    private List<MeiziBean> datas;

    private static final int TOTAL_COUNTER = 2000;


    private int delayMillis = 1000;

    private int mCurrentCounter = 0;
    int page = 1;

    private boolean isErr;
    private boolean mLoadMoreEndGone = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mLayoutRecyBinding =  DataBindingUtil.setContentView(this,R.layout.layout_recy);

        mLayoutRecyBinding.swipeLayout.setRefreshing(true);
        mLayoutRecyBinding.recyclerview.setLayoutManager(new LinearLayoutManager(ViewActivity.this));

//       initData(1);
    }

    private void initData(int page) {


        Call<BaseBean<MeiziBean>> meizhiData = ApiManage.getInstence().getGankService().getMeizhiData(page);
         meizhiData.enqueue(new Callback<BaseBean<MeiziBean>>() {
             @Override
             public void onResponse(Call<BaseBean<MeiziBean>> call, Response<BaseBean<MeiziBean>> response) {
                 BaseBean<MeiziBean> body = response.body();
                 System.out.println("內容："+body.getResults().toString());

                 initAdapter(body.getResults());
//                 mMeiziAdapter.setOnLoadMoreListener(ViewActivity.this);
//                 mMeiziAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//                 mLayoutRecyBinding.swipeLayout.setRefreshing(true);

//                 mLayoutRecyBinding.recyclerview.setAdapter(mMeiziAdapter);
             }

             @Override
             public void onFailure(Call<BaseBean<MeiziBean>> call, Throwable t) {
                 System.out.println("错误："+t);

             }
         });


//        mLayoutRecyBinding.recyclerview

    }

    @Override
    public void onRefresh() {
        System.out.println("刷新開始");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initData(1);
                isErr = false;
                mCurrentCounter = 10;

                mLayoutRecyBinding.swipeLayout.setEnabled(false);
                mMeiziAdapter.setEnableLoadMore(true);
            }
        }, delayMillis);

    }

    @Override
    public void onLoadMoreRequested() {
        mLayoutRecyBinding.swipeLayout.setEnabled(false);
        mLayoutRecyBinding.recyclerview.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mCurrentCounter >= TOTAL_COUNTER) {
//                    mMeiziAdapter.loadMoreEnd();//default visible
                    //加载结束
                    mMeiziAdapter.loadMoreEnd(mLoadMoreEndGone);//true is gone,false is visible
                } else {
                    if (isErr) {
                        page++;
                        initData(page);
                        mCurrentCounter = mMeiziAdapter.getData().size();
                        mMeiziAdapter.loadMoreComplete();
                        //加载更多
                    } else {
                        //加载出错
                        isErr = true;
                        Toast.makeText(ViewActivity.this, "加载出错", Toast.LENGTH_LONG).show();
                        mMeiziAdapter.loadMoreFail();

                    }
                }
                mLayoutRecyBinding.swipeLayout.setEnabled(true);
            }

        },delayMillis);
    }

    private void initAdapter(List<MeiziBean> results) {
        mMeiziAdapter = new MeiziAdapter(R.layout.item_meizi,results);
        mMeiziAdapter.setOnLoadMoreListener(this);
        mMeiziAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
//        mQuickAdapter.setAutoLoadMoreSize(3);
        mLayoutRecyBinding.recyclerview.setAdapter(mMeiziAdapter);
        mCurrentCounter = mMeiziAdapter.getData().size();

//        mLayoutRecyBinding.recyclerview.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void SimpleOnItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(PullToRefreshUseActivity.this, Integer.toString(position), Toast.LENGTH_LONG).show();
//            }
//        });
    }
}
