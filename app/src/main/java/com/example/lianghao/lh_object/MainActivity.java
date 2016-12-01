package com.example.lianghao.lh_object;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.example.lianghao.lh_object.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
        ActivityMainBinding mActivityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mActivityMainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
           initData();
    }



    private void initView() {
        mActivityMainBinding.meizi.setOnClickListener(this);


        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Home"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Books"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Music"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Movies & TV"))
                .addItem(new BottomNavigationItem(R.mipmap.ic_launcher, "Games"))
                .initialise();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                System.out.println("onTabSelected:"+position);

            }
            @Override
            public void onTabUnselected(int position) {
                System.out.println("onTabUnselected:"+position);
            }
            @Override
            public void onTabReselected(int position) {
                System.out.println("onTabReselected:"+position);
            }
        });
    }
    private void initData() {

    }

    @Override
    public void onClick(View v) {
         switch (v.getId()){
             case R.id.meizi:
                 startActivity(new Intent(this,ViewActivity.class));
                 break;
         }
    }
}
