package com.example.hasee.eventbustest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Main2Activity extends AppCompatActivity {
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        EventBus.getDefault().register(this);
        textView = (TextView) findViewById(R.id.tv2);
        btn = (Button) findViewById(R.id.btn_back);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送消息
                EventBus.getDefault().post(new EventBusTt("欧码噶"));
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.POSTING)//posting在相同进程中
    public void onEvent(EventBusTt evt) {
        String msg = "收到消息：" + evt.getMsg();
        Toast.makeText(this, evt.getMsg() + "2", Toast.LENGTH_SHORT).show();
        textView.setText(msg);
    }
}
