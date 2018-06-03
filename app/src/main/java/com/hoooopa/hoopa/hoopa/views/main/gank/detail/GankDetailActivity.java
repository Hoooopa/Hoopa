package com.hoooopa.hoopa.hoopa.views.main.gank.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.hoooopa.hoopa.hoopa.R;
import com.hoooopa.hoopa.hoopa.base.BaseActivity;
import com.hoooopa.hoopa.hoopa.views.main.cook.detail.CookDetailPresenter;
import com.hoooopa.hoopa.hoopa.views.main.gank.IGankView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GankDetailActivity extends AppCompatActivity {

    @BindView(R.id.activity_gank_detail_webview)
    WebView webView;

    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);

        initData();
        initView();
    }

    private void initData(){
        Intent intent = getIntent();
        url =intent.getStringExtra("url");
    }

    private void initView(){

        webView.loadUrl(url);

    }

}
