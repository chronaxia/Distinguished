package com.miaxis.distinguished.view.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MotionEvent;

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 一非 on 2018/4/8.
 */

public abstract class BaseActivity extends RxAppCompatActivity {

    private Unbinder bind;
    private MyBaseActiviyBroadcastReceiver myBaseActivityBroad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        bind = ButterKnife.bind(this);
        initData();
        initView();
        myBaseActivityBroad = new MyBaseActiviyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter("com.miaxis.distinguished.view.activity.BaseActivity");
        registerReceiver(myBaseActivityBroad, intentFilter);
    }

    protected abstract int setContentView();

    protected abstract void initData();

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public class MyBaseActiviyBroadcastReceiver extends BroadcastReceiver {

        public void onReceive(Context arg0, Intent intent) {
            //接收发送过来的广播内容
            int closeAll = intent.getIntExtra("closeAll", 0);
            if (closeAll == 1) {
                finish();//销毁BaseActivity
            }
        }
    }
}
