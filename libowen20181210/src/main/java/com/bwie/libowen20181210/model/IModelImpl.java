package com.bwie.libowen20181210.model;

import android.annotation.SuppressLint;
import android.os.AsyncTask;

import com.bwie.libowen20181210.callback.MyCallBack;
import com.bwie.libowen20181210.httpnet.IcallBack;
import com.bwie.libowen20181210.httpnet.NetUtils;
import com.bwie.libowen20181210.httpnet.OkHttpUtils;
import com.google.gson.Gson;

import java.util.Map;

public class IModelImpl implements IModel {
    private MyCallBack myCallBack;
   /* @SuppressLint("StaticFieldLeak")
    @Override
    public void requestData(String path, final Class clazz, final MyCallBack myCallBack) {
       *//* this.myCallBack = myCallBack;
        //解析
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {
                String json = NetUtils.getJson(strings[0]);
                return new Gson().fromJson(json,clazz);
            }

            @Override
            protected void onPostExecute(Object o) {
                myCallBack.sucess(o);
            }
        }.execute(path);*//*

    }*/

    @Override
    public void requestData(String path, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OkHttpUtils.getInstance().postEnqueue(path, params, clazz, new IcallBack() {
            @Override
            public void fails(Exception e) {
                myCallBack.sucess(e.getMessage());
            }

            @Override
            public void sucess(Object data) {
                myCallBack.sucess(data);
            }
        });
    }
}
