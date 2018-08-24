package com.miaxis.distinguished.view.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.miaxis.distinguished.R;
import com.miaxis.distinguished.adapter.MainFragmentAdapter;
import com.miaxis.distinguished.model.entity.Config;
import com.miaxis.distinguished.view.fragment.BankCustomersFragment;
import com.miaxis.distinguished.view.fragment.MyCustomersFragment;
import com.miaxis.distinguished.view.fragment.SystemFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    @BindArray(R.array.title)
    String[] titles;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.vp_main)
    ViewPager vpMain;
    @BindView(R.id.bnv_main)
    BottomNavigationView bnvMain;

    private MainFragmentAdapter adapter;

    private MenuItem menuItem;

    public static Intent newInstance(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        MyCustomersFragment myCustomersFragment = new MyCustomersFragment();
        fragmentList.add(myCustomersFragment);
        BankCustomersFragment bankCustomersFragment = new BankCustomersFragment();
        fragmentList.add(bankCustomersFragment);
        SystemFragment systemFragment = new SystemFragment();
        fragmentList.add(systemFragment);
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), fragmentList);
    }

    @Override
    protected void initView() {
        vpMain.setAdapter(adapter);
        vpMain.addOnPageChangeListener(this);
        vpMain.setOffscreenPageLimit(20);
        vpMain.setCurrentItem(0);
        toolbar.setTitle(titles[0]);
        bnvMain.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        toolbar.setTitle(titles[position]);
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            bnvMain.getMenu().getItem(0).setChecked(false);
        }
        menuItem = bnvMain.getMenu().getItem(position);
        menuItem.setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_upload_task:
                vpMain.setCurrentItem(0);
                break;
            case R.id.action_my_task:
                vpMain.setCurrentItem(1);
                break;
            case R.id.action_setting:
                vpMain.setCurrentItem(2);
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        new MaterialDialog.Builder(this)
                .title("退出程序")
                .content("您确认要退出程序?")
                .negativeText("取消")
                .positiveText("确认")
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent intent = new Intent("com.miaxis.distinguished.view.activity.BaseActivity");
                        intent.putExtra("closeAll", 1);
                        sendBroadcast(intent);//发送广播
                    }
                })
                .show();
    }
}
