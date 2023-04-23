package com.hcs.testeventbus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.hcs.testeventbus.databinding.ActivityMainBinding;
import com.hcs.testeventbus.event.MessageUpdateText;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mDataBinding.btnRegisterEventBus.setOnClickListener(this);
        mDataBinding.btnJumpSecondActivity.setOnClickListener(this);
        mDataBinding.btnUnregisterEventBus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mDataBinding.btnRegisterEventBus) {
            if (EventBus.getDefault().isRegistered(this)) {
                return;
            }
            EventBus.getDefault().register(this);
        } else if (view == mDataBinding.btnJumpSecondActivity) {
            Intent intent = new Intent();
            intent.setAction("com.hcs.testeventbus.SecondActivity");
            intent.setDataAndType(Uri.parse("http://www.baidu.com:8080/test/1"), "text/html");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else if (view == mDataBinding.btnUnregisterEventBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateTextEvent(MessageUpdateText messageUpdateText) {
        mDataBinding.tvTextFromEventBus.setText(messageUpdateText.getMessage());
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void updateTextStickyEvent(MessageUpdateText messageUpdateText) {
        mDataBinding.tvTextFromEventBus.setText(messageUpdateText.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}