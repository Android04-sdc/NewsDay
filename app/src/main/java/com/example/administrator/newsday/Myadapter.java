package com.example.administrator.newsday;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
/**
 * Created by Administrator on 2016/9/1.
 */
public class Myadapter extends RecyclerView.Adapter<Myadapter.MyviewHolder> {
    private static final String TAG = "Myadapter";
    private ArrayList<GsonObject.ResultBean.DataBean> list;
    Context context;
    public Myadapter(ArrayList<GsonObject.ResultBean.DataBean> list, Context context) {//写构造方法，那边实例化的时候可以把数据传过来
        this.list = list;//把穿过来的集合赋值给现在的list，
        this.context = context;//
    }
    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, null);
        MyviewHolder myviewHolder=new MyviewHolder(view);//这里转入view的话，下边构造方法中就有了view
        return myviewHolder;
    }
    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        final GsonObject.ResultBean.DataBean dataBean = list.get(position);//通过list

        holder.imageView.setLayoutParams(new LinearLayout.LayoutParams(250,250));
        Picasso.with(context).load(dataBean.getThumbnail_pic_s()).into(holder.imageView);//下载图片
        holder.textView.setText(dataBean.getTitle());//设置文字
        Log.d(TAG, "onBindViewHolder: "+dataBean.getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {//设置布局的监听，这样点击的时候就能传值出去
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,WebviewActivity.class);
                intent.putExtra("url",dataBean.getUrl());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        if (list!=null){
            return list.size();
        }
        return 0;
    }
    class MyviewHolder extends RecyclerView.ViewHolder{//根据上边传来的进行初始化，找到所用到的控件
        private final ImageView imageView;
        private final TextView textView;
        private final LinearLayout linearLayout;
        public MyviewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            textView = (TextView) itemView.findViewById(R.id.textview);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearlayout);
        }
    }
}
