package com.example.administrator.newsday;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.ButterKnife;
import butterknife.InjectView;
public class WebviewActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {
    @InjectView(R.id.webviewtoolbar)
    Toolbar webviewtoolbar;
    @InjectView(R.id.webview)
    WebView webview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        setSupportActionBar(webviewtoolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        webview.loadUrl(url);
        webview.setFocusable(true);
        webview.setFocusableInTouchMode(true);
        webviewtoolbar.setOnMenuItemClickListener(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()){
            case R.id.fenxiang:

        }
        return false;
    }
}
