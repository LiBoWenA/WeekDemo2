package com.bwie.libowen20181210.persenter;

import com.bwie.libowen20181210.callback.MyCallBack;
import com.bwie.libowen20181210.model.IModel;
import com.bwie.libowen20181210.model.IModelImpl;
import com.bwie.libowen20181210.view.Iview;

import java.util.Map;

public class IPersenterImpl implements IPersenter {
    private Iview iview;
    private IModel iModel;

    public IPersenterImpl(Iview iview) {
        this.iview = iview;
        iModel = new IModelImpl();
    }

   /* @Override
    public void showRequestData(String path, Class clazz) {
        iModel.requestData(path, clazz, new MyCallBack() {
            @Override
            public void sucess(Object data) {
                iview.startRequestData(data);
            }
        });
    }*/

    //接触绑定
    public void onDestory(){
        if (iview != null){
            iview = null;
        }
        if (iModel != null){
            iModel = null;
        }
    }

    @Override
    public void showRequestData(String path, Map<String, String> params, Class clazz) {
        iModel.requestData(path, params, clazz, new MyCallBack() {
            @Override
            public void sucess(Object data) {
                iview.startRequestData(data);
            }
        });
    }
}
