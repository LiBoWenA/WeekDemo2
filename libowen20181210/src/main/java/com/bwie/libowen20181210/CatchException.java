package com.bwie.libowen20181210;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CatchException implements Thread.UncaughtExceptionHandler {

    private static CatchException instans;
    private Context context;

    public static CatchException getInstans(Context context){
        if (instans == null){
            synchronized (CatchException.class){
                instans = new CatchException(context);
            }
        }
        return instans;
    }

    public CatchException(Context context) {
        init(context);
    }

    public void init(Context context) {
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        try{
            savaSD(e);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void savaSD(Throwable throwable) throws Exception {
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            //彻底退出
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
            return;
        }
        //获取手机信息
        PackageManager pm = context.getPackageManager();
        PackageInfo inFo = pm.getPackageInfo(context.getPackageName(), PackageManager.GET_ACTIVITIES);
        String versionName = inFo.versionName;
        int versionCode = inFo.versionCode;
        int version_code = Build.VERSION.SDK_INT;
        String release = Build.VERSION.RELEASE;
        String mobile = Build.MODEL;
        String mobileName = Build.MANUFACTURER;
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        SimpleDateFormat times = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String time = times.format(new Date());

        //找到文件夹路径，创建exception文件夹
        File f = new File(path, "exception");
        f.mkdirs();

        //找到文件夹路径，查找exception + time的txt文件夹
        File file = new File(f.getAbsolutePath(), "exception" + time + ".txt");

        //如果文件没有，则创建
        if (!file.exists()) {
            file.createNewFile();
        }

        String data = mobile + mobileName +  version_code + versionName + versionCode + throwable.toString();

        //写入文件
        byte[] buffer = data.trim().getBytes();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        // 开始写入数据到这个文件。
        fileOutputStream.write(buffer, 0, buffer.length);
        fileOutputStream.flush();
        fileOutputStream.close();

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);

    }
}
