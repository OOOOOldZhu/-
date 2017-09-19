package com.z.microd.api;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;


import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by z on 2017/9/19.
 */

public class MyHttpManager {
    private OkHttpClient okHttpClient;
    private Gson gson;
    private Handler handler;
    private static MyHttpManager myHttpManager;

    private MyHttpManager() {
        okHttpClient = new OkHttpClient();
        gson = new Gson();
        handler = new Handler(Looper.getMainLooper());
    }

    public static MyHttpManager getInstance() {
        if (myHttpManager == null) {
            synchronized (MyHttpManager.class) {
                myHttpManager = new MyHttpManager();
            }
        }
        return myHttpManager;
    }

    private void doRequest(Request request, final MyHttpCallBack callback) {
        callback.onBeforeRequest(request);
        okHttpClient.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, final IOException e) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                callback.onFailure(call, e);
                            }
                        });
                    }

                    @Override
                    public void onResponse(final Call call, final Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String json = response.body().string();
                            final Object o = gson.fromJson(json, callback.type);
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onSuccess(call, o);
                                }
                            });
                        } else {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    callback.onError(response, "service internal error !");
                                }
                            });
                        }
                    }
                });
    }

    public void doGet(String url, MyHttpCallBack callback) {
        Request request = new Request.Builder().url(url).get().build();
        doRequest(request, callback);
    }

    public void doPost(String url, Map<String,String> params, MyHttpCallBack callback) {
        // 创建表单
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String,String> entry:params.entrySet()) {
            builder.add(entry.getKey(),entry.getValue());
        }
        RequestBody body =builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        doRequest(request,callback);
    }

}
