package com.example.lianghao.lh_object;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by LiangHao on 2016/11/3.
 */

public class WebView extends AppCompatActivity {
    private View view;
    public android.webkit.WebView webview;
    private SwipeRefreshLayout swipeLayout;
    String number = "18";
    String numberafter ="&direction=1&from=ls";
    String numberone = "http://58.58.179.21:8084/api/detail.asp?cityId=001&Type=LineDetail&lineNo=00";
    String url = numberone+number+numberafter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewone);
        initAdater();
        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //重新刷新页面
                webview.loadUrl(webview.getUrl());
            }
        });
        swipeLayout.setColorSchemeResources(R.color.holo_blue_bright,
                R.color.holo_green_light, R.color.holo_orange_light,
                R.color.holo_red_light);
        initWebview( number);

    }

    private void initWebview(String number) {
        webview = (android.webkit.WebView)findViewById(R.id.webview);

        webview.loadUrl(url);
        //添加javaScript支持
        webview.getSettings().setJavaScriptEnabled(true);
        //取消滚动条
        webview.setScrollBarStyle(android.webkit.WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //触摸焦点起作用
        webview.requestFocus();
        //点击链接继续在当前browser中响应
        webview.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置进度条
        webview.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(android.webkit.WebView view, int newProgress) {
                if (newProgress == 100) {
                    //隐藏进度条
                    swipeLayout.setRefreshing(false);
                } else {
                    if (!swipeLayout.isRefreshing())
                        swipeLayout.setRefreshing(true);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

    }

    private static final String[] m_Countries = { "18", "11", "34", "42", }; //定义数组

    private ArrayAdapter adapter; //存放数据
    private Spinner spinnerCardNumber; //下拉框
    private void initAdater() {
        spinnerCardNumber = (Spinner)findViewById(R.id.spinner);
        //将可选内容与ArrayAdapter连接，
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, m_Countries);
        //将adapter添加到m_Spinner中
        spinnerCardNumber.setAdapter(adapter);
//        到这里，就完成了下拉框的绑定数据，下拉框中已经有我们想要选择的值了。下面获取选择的值。
//        //添加Spinner事件监听
//
        spinnerCardNumber.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerCardNumber.setSelection(position);
                number = m_Countries[position];
                webview.loadUrl(numberone+number+numberafter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

}
