package com.example.administrator.newsday;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
public class MainActivity extends AppCompatActivity {
    @InjectView(R.id.tablayout)
    TabLayout tablayout;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.navigationview)
    NavigationView navigationview;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawerlayout)
    DrawerLayout drawerlayout;
    private List<Fragment> list;
    private List<String> title;
    String[] tabtitle = {"头条", "社会", "国内", "国际", "娱乐",
            "体育", "军事", "科技", "财经", "时尚"};
    String[] name = {"top", "shehui", "guonei", "guoji", "yule",
            "tiyu", "junshi", "keji", "caijing", "shishang"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        tablayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.colorAccent));//下划线的颜色
        tablayout.setTabTextColors(ContextCompat.getColor(this, R.color.colorPrimaryDark), ContextCompat.getColor(this, R.color.colorAccent));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(// 构建抽屉的监听
                this, drawerlayout, toolbar, R.string.open, R.string.close);
        toggle.syncState();// 根据drawerlayout同步其当前状态
        // 设置抽屉监听
        drawerlayout.addDrawerListener(toggle);
    }
    @Override
    protected void onStart() {
        super.onStart();
        initdata();
        ViewAdapter viewadapter = new ViewAdapter(getSupportFragmentManager(), list, title);
        viewpager.setAdapter(viewadapter);
        tablayout.setupWithViewPager(viewpager);
    }
    private void initdata() {
        title = new ArrayList<>();
        list = new ArrayList<>();
        for (int i = 0; i < tabtitle.length; i++) {
            title.add(tabtitle[i]);
            list.add(ViewpagerFragment.getInstance(name[i]));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
}
