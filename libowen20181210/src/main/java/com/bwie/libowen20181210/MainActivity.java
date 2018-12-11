package com.bwie.libowen20181210;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.libowen20181210.bean.LoginBean;
import com.bwie.libowen20181210.persenter.IPersenterImpl;
import com.bwie.libowen20181210.view.Iview;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Iview,View.OnClickListener {

    private EditText ed_name,ed_pass;
    private IPersenterImpl iPersenter;
    private String path = "http://www.zhaoapi.cn/user/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iPersenter = new IPersenterImpl(this);
        //获取资源ID
        init();
    }

    private void init() {
        ed_name = findViewById(R.id.et_name);
        ed_pass = findViewById(R.id.et_pass);
        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.btn_zc).setOnClickListener(this);
        findViewById(R.id.btn_qq).setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    //点击事件
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            //登录
            case R.id.btn_login:
                //获取登录信息
                String name = ed_name.getText().toString();
                String pass = ed_pass.getText().toString();

                Map<String,String> params=new HashMap<>();
                params.put("mobile",name);
                params.put("password",pass);
                iPersenter.showRequestData(path,params,LoginBean.class);
                break;
            //注册
            case R.id.btn_zc:
                //跳转到注册页面
                Intent intents = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intents);
                finish();
                break;
            //第三方登录
            case R.id.btn_qq:
                UMShareAPI umShareAPI = UMShareAPI.get(MainActivity.this);
                umShareAPI.getPlatformInfo(this, SHARE_MEDIA.QQ, new UMAuthListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {

                    }

                    @Override
                    public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                        //登陆完成进行跳转
                        Intent intent = new Intent(MainActivity.this,SucessActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media, int i) {

                    }
                });
                break;
                default:
                    break;
        }
    }

    @Override
    public void startRequestData(Object data) {
        if (!(data instanceof LoginBean)){
            return;
        }
        LoginBean bean = (LoginBean) data;
        if (bean.getCode().equals("0")){
            Intent intent = new Intent(MainActivity.this,SucessActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(MainActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    //解除绑定
    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();
    }
}
