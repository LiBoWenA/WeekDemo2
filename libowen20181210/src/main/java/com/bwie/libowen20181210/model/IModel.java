package com.bwie.libowen20181210.model;

import com.bwie.libowen20181210.callback.MyCallBack;

import java.util.Map;

public interface IModel {
    void requestData(String path, Map<String, String> params , Class clazz, MyCallBack myCallBack);
}
