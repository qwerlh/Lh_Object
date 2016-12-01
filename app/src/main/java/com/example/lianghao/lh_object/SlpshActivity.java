package com.example.lianghao.lh_object;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by LiangHao on 2016/11/3.
 */

public class SlpshActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splsh);
        //隐藏状态栏
        getSupportActionBar().hide();
        //定义全屏参数
        int flag= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //获得当前窗体对象
        Window window= SlpshActivity.this.getWindow();
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
        getWindow().getDecorView().setSystemUiVisibility(View.INVISIBLE);

        initView();
    }

    private void initView() {
// 闪屏的核心代码
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SlpshActivity.this,
                        MainActivity.class); // 从启动动画ui跳转到主ui
                startActivity(intent);
//                overridePendingTransition(R.anim.in_from_right,
//                R.anim.out_to_left);
//                SlpshActivity.this.finish(); // 结束启动动画界面
                finish();
            }
        }, 4000); // 启动动画持续3秒钟

    }
}
