package com.example.lenovo.eventbusdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button btn;
    UrlHandler handler=new UrlHandler();
    public final String url="http://101.200.164.87:8080/visa/xml/version.json";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
        tv=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        handler.get(url);
                    }
                }).start();
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void onEventMainThread(EventMessage message){
        if(message.isFlag()){
            tv.setText(message.getContent());
        }
        else {
            Log.e("Event", "failed");
        }
    }



}
