package com.hoooopa.hoopa.hoopa.views.main.gank.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
    @BindView(R.id.activity_gank_detail_Title)
    TextView tvTitle;
    @BindView(R.id.activity_gank_detail_toolbar)
    Toolbar toolbar;

    private String url;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_detail);
        ButterKnife.bind(this);

        initData();
        initView();
        initListener();
    }

    private void initData(){
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
    }

    private void initView(){
        tvTitle.setText(title);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        webView.loadUrl(url);

    }

    private void initListener(){
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}
