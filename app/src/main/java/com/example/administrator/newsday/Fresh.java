package com.example.administrator.newsday;
import java.util.List;
/**
 * Created by Administrator on 2016/9/9.
 */
public interface Fresh {
    void setdata(List<GsonObject.ResultBean.DataBean> mlist);
    void hide();
    void show();
    void showeror();
}
