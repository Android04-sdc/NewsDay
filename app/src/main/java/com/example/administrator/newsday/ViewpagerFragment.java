package com.example.administrator.newsday;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
/**
 * A simple {@link Fragment} subclass.
 */
public class ViewpagerFragment extends Fragment {
    private static final String TAG = "ViewpagerFragment";
    private ArrayList<GsonObject.ResultBean.DataBean> list;//定义一个集合,一定要初始化
    Mythread mythread;
    Myhandler myhandler;
    private static final String URL_BEFORE = "http://v.juhe.cn/toutiao/index?type=";
    private static final String URL_AFTER = "&key=61568ca11034d25c104c2f1f8e10d0d0";
    @InjectView(R.id.recyclerview)
    RecyclerView recyclerview;
    private RecyclerView.LayoutManager layoutManager;//recyclerview的布局管理器，直接new 一个linearlayout等的
    private String type;//
    private Utils utils;//工具类  用于网络加载解析，还有就是gson的对象的获得
    private PtrClassicFrameLayout ptrFrameLayout;
    private Myadapter adapter;
    public ViewpagerFragment() {
        // Required empty public constructor
    }
    public static ViewpagerFragment getInstance(String type){//单例模式，一个是为了返回fragment对象，一个是为了传一个值给下变得进行；联网
        Bundle bundle=new Bundle();//利用bundle对象来传值
        bundle.putString("type",type);//利用bundle传值
        ViewpagerFragment viewpagerFragment=new ViewpagerFragment();
        viewpagerFragment.setArguments(bundle);//利用fragment来穿值
        return viewpagerFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {//最后返回视图
         View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
         ButterKnife.inject(this, view);
         layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
         recyclerview.setLayoutManager(layoutManager);

        type = getArguments().getString("type");//得到传过来的值
        myhandler=new Myhandler();
        mythread=new Mythread();
        utils = new Utils(getContext());
        mythread.start();

         return view;
    }

    class Mythread extends Thread{//开启一个线程进行网络加载
       @Override
       public void run() {
           super.run();
           String json = utils.getJson(URL_BEFORE + type + URL_AFTER);//进行网络加载，得到json字符串
           Log.d(TAG, "run: "+json);
           GsonObject gsonObject = utils.getGsonObject(json);//通过json字符串得到gson对象
           Message message = Message.obtain();
           message.obj=gsonObject;
           myhandler.sendMessage(message);//通过message把gson对象传出去
       }
   }
    class Myhandler extends Handler {//进行UI更新
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final GsonObject obj = (GsonObject) msg.obj;//接收传过来的对象
            list = (ArrayList<GsonObject.ResultBean.DataBean>) obj.getResult().getData();//利用对象里面的方法得到集合,并且赋值给list
            //设置适配器，将list集合再通过构造方法传给myadapter,进行相关的数据操作
            adapter = new Myadapter(list, getContext());
            recyclerview.setAdapter(adapter);//recyclerview 进行适配器的设置
            recyclerview.addItemDecoration(new MyDecoration(getContext(), MyDecoration.VERTICAL_LIST));
        }
    }
}
