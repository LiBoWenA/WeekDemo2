package com.bwie.libowen20181210.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bwie.libowen20181210.R;
import com.bwie.libowen20181210.SysActivity;
import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;
import java.util.List;

public class ShowFragment extends Fragment {

    private Button btn_sys;
    private FlyBanner flyBanner;
    private TextView text;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.showfragment,container,false);
        //获取资源ID
        btn_sys = v.findViewById(R.id.btn_sys);
        flyBanner = v.findViewById(R.id.flyb);
        text = v.findViewById(R.id.text);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        List<String> list = new ArrayList<>();
        list.add("http://www.zhaoapi.cn/images/quarter/ad1.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad2.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad3.png");
        list.add("http://www.zhaoapi.cn/images/quarter/ad4.png");
        flyBanner.setImagesUrl(list);

        //点击按钮进行跳转
        btn_sys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SysActivity.class);
                startActivity(intent);
            }
        });
    }
}
