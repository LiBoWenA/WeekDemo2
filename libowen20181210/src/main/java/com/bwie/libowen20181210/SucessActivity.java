package com.bwie.libowen20181210;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.bwie.libowen20181210.fragment.NullFragment;
import com.bwie.libowen20181210.fragment.ShowFragment;

import java.util.ArrayList;
import java.util.List;

public class SucessActivity extends AppCompatActivity {

    private ViewPager pager;
    private List<Fragment> list;
    private RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);
        //获取资源ID
        pager = findViewById(R.id.pager);
        group = findViewById(R.id.group);
        list = new ArrayList<>();
        list.add(new ShowFragment());
        list.add(new NullFragment());
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return list.get(i);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        //点击按钮切换页面
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radio1:
                        pager.setCurrentItem(0);
                        break;
                    case R.id.radio2:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio3:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio4:
                        pager.setCurrentItem(1);
                        break;
                    case R.id.radio5:
                        pager.setCurrentItem(1);
                        break;
                        default:
                            break;
                }
            }
        });
    }
}
