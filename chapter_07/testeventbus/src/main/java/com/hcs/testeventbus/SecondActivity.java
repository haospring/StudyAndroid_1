package com.hcs.testeventbus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;

import com.hcs.testeventbus.databinding.ActivitySecondBinding;
import com.hcs.testeventbus.event.MessageUpdateText;

import org.greenrobot.eventbus.EventBus;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding mDataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_second);
    }

    public void publishMessage(View view) {
        EventBus.getDefault().post(new MessageUpdateText("事件总线"));
        finish();
    }

    public void publishStickyMessage(View view) {
        EventBus.getDefault().postSticky(new MessageUpdateText("黏性事件总线"));
        finish();
    }
}