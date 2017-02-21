package com.example.hasee.eventbustest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
/**
 * 1.在发消息页面，执行EventBus发消息方法post();
 * 2.在接收消息页面注册EventBus,方法register
 * 3.处理消息，在接收页面(注解的方式)
 * 4.用完销毁，反注册EventBus方法（unregister）
* */
public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //注册EventBus
        EventBus.getDefault().register(this);
        btn= (Button) findViewById(R.id.btn_go);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        textView= (TextView) findViewById(R.id.tv);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EventBusTt evt){
        String msg="收到消息："+evt.getMsg();
        Toast.makeText(this, evt.getMsg(), Toast.LENGTH_SHORT).show();
        textView.setText(msg);
    }

}
