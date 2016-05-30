package com.example.lenovo.eventbusdemo;

import android.util.Log;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import de.greenrobot.event.EventBus;

/**
 * Created by lenovo on 2016/5/30.
 */
public class UrlHandler {

    OkHttpClient client=new OkHttpClient();
    EventMessage message=new EventMessage();


    public void get(String url){
        final Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                message.setFlag(false);
                EventBus.getDefault().post(message);
                Log.e("Connect", "failed");
            }

            @Override
            public void onResponse(Response response) throws IOException {

                if(response.isSuccessful()){
                    message.setFlag(true);
                    message.setContent(response.body().string());
                    Log.e("Connect", "succeed");

                }
                else {
                    message.setFlag(false);
                    Log.e("Connect", "failed");
                }
                EventBus.getDefault().post(message);
            }
        });
    }

}
