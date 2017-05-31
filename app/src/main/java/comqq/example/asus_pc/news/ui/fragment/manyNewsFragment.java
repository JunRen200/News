package comqq.example.asus_pc.news.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import comqq.example.asus_pc.news.R;
import comqq.example.asus_pc.news.adapter.Myadapter;
import comqq.example.asus_pc.news.impl.RecyclerViewItemonclick;
import comqq.example.asus_pc.news.gson.XinWen;
import comqq.example.asus_pc.news.ui.activity.TbsActivity;
import okhttp3.Call;

/**
 * Created by asus-pc on 2017/5/15.
 */

public class manyNewsFragment extends Fragment implements RecyclerViewItemonclick {
    private RecyclerView recyclerView;
    private Myadapter myadapter;
    private List<XinWen.ResultBean.DataBean> list;
    private TextView txt_load;
    private String title;
    //    private List<String> list;
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                if (myadapter == null) {
                    myadapter = new Myadapter(getContext(), list,manyNewsFragment.this);
                    recyclerView.setAdapter(myadapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    Log.e("AAA","Msg.what=1+"+title);
                } else {
                    myadapter.setList(list);
                    myadapter.notifyDataSetChanged();
                    Log.e("AAA","Msg.what=2"+title);
                }
            }
        }
    };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initData() {
        txt_load.setVisibility(View.VISIBLE);
        String url ="";
        if (title.equals("头条")) {
            url = "http://v.juhe.cn/toutiao/index?type=top&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("社会")) {
            url = "http://v.juhe.cn/toutiao/index?type=shehui&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("国内")) {
            url = "http://v.juhe.cn/toutiao/index?type=guonei&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("国际")) {
            url = "http://v.juhe.cn/toutiao/index?type=guoji&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("娱乐")) {
            url = "http://v.juhe.cn/toutiao/index?type=yule&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("体育")) {
            url = "http://v.juhe.cn/toutiao/index?type=tiyu&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("军事")) {
            url = "http://v.juhe.cn/toutiao/index?type=junshi&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("科技")) {
            url = "http://v.juhe.cn/toutiao/index?type=keji&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("财经")) {
            url = "http://v.juhe.cn/toutiao/index?type=caijing&key=ac4b52242dde38eaa2e50760dd596587";
        } else if (title.equals("时尚")) {
            url = "http://v.juhe.cn/toutiao/index?type=shishang&key=ac4b52242dde38eaa2e50760dd596587";
        }
        Log.e("AAA", url);

        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        txt_load.setVisibility(View.GONE);
                        Gson gson = new Gson();
                        XinWen data = gson.fromJson(response, XinWen.class);
                        list = data.getResult().getData();
                        Message message = new Message();
                        message.what = 1;
                        handler.sendMessage(message);
                    }
                });
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);
        txt_load= (TextView) view.findViewById(R.id.txt_load);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void onItemClick(View view, int position, String html) {
        Intent intent=new Intent(getContext(),TbsActivity.class);
        intent.putExtra("html",html);
        startActivity(intent);
    }
}
