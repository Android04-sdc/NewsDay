package com.example.administrator.newsday;
import android.content.Context;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import java.io.IOException;
/**
 * Created by Administrator on 2016/9/1.
 */
/**
 * 写一个工具类，来加载网络并解析
 */
public class Utils {
    private static final String TAG = "Utils";
    private static String json;
    Context mcontext;
    public Utils(Context context) {
        mcontext = context;
    }
    public static String getJson(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public  GsonObject getGsonObject(String json){//gson解析，把json字符串解析到GsonObject，也可以直接解析成集合
        Gson gson=new Gson();
        GsonObject object = gson.fromJson(json, GsonObject.class);
        return object;//返回gson的对象，在viewpagerframent中用这个对象来调用里边的方法，来实现集合
    }
}
