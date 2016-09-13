package com.example.administrator.newsday;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.util.List;
/**
 * Created by Administrator on 2016/9/10.
 */
public class Presenter {
    private static final String TAG = "Presenter";
    private static final String URL_BEFORE = "http://v.juhe.cn/toutiao/index?type=";
    private static final String URL_AFTER = "&key=61568ca11034d25c104c2f1f8e10d0d0";
    String type;
    List<GsonObject.ResultBean.DataBean> plist;
    Fresh fresh;
    public Presenter(String type, Fresh fresh) {
        this.type = type;
        this.fresh = fresh;
    }
    String url=URL_BEFORE+type+URL_AFTER;
    public void refresh(){
        new mysyn().execute();
    }
    class mysyn extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... params) {
            String json = Utils.getJson(url);
            Log.d(TAG, "doInBackground: "+json);
            return json;
        }
        @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Gson gson=new Gson();
//                plist=gson.fromJson(s,new TypeToken<List<GsonObject.ResultBean.DataBean>>(){}.getType());
             GsonObject gsonObject = gson.fromJson(s, GsonObject.class);
             plist= gsonObject.getResult().getData();
             fresh.setdata(plist);
        }
    }
}
