package com.bwie.libowen20181210;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.libowen20181210.bean.LoginBean;
import com.bwie.libowen20181210.bean.ZcBean;
import com.bwie.libowen20181210.persenter.IPersenterImpl;
import com.bwie.libowen20181210.view.Iview;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements Iview,View.OnClickListener {

    private EditText ed_name,ed_pass;
    private IPersenterImpl iPersenter;
    String path = "http://www.zhaoapi.cn/user/reg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        iPersenter = new IPersenterImpl(this);
        //获取资源ID
        init();
    }

    private void init() {
        ed_name = findViewById(R.id.et_name);
        ed_pass = findViewById(R.id.et_pass);
        findViewById(R.id.btn_login).setOnClickListener(this);
    }

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
                iPersenter.showRequestData(path,params,ZcBean.class);
                break;

            default:
                break;
        }

    }

    @Override
    public void startRequestData(Object data) {
        if (!(data instanceof ZcBean)){
            return;
        }
        ZcBean bean = (ZcBean) data;
        if (bean.getCode().equals("0")){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            Toast.makeText(LoginActivity.this,bean.getMsg(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPersenter.onDestory();
    }
}
