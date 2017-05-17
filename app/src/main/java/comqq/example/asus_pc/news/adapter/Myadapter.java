package comqq.example.asus_pc.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import comqq.example.asus_pc.news.R;
import comqq.example.asus_pc.news.impl.RecyclerViewItemonclick;
import comqq.example.asus_pc.news.json.XinWen;

/**
 * Created by asus-pc on 2017/5/16.
 */

public class Myadapter extends RecyclerView.Adapter<Myadapter.ViewHolder> {
    private Context context;
    private List<XinWen.ResultBean.DataBean> list;
    private RecyclerViewItemonclick recyclerViewItemonclick;
//private List<String> list;
    public  Myadapter(Context context, List<XinWen.ResultBean.DataBean> list){
        this.context=context;
        this.list=list;
        this.recyclerViewItemonclick= (RecyclerViewItemonclick) context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_new, parent, false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_new,parent);//错误写法会出现异常

        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        list.get(position).getThumbnail_pic_s();
        Glide.with(context).load(list.get(position).getThumbnail_pic_s()).into(holder.img);
        holder.txt.setText(list.get(position).getTitle());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(recyclerViewItemonclick!=null){
                    recyclerViewItemonclick.onItemClick(v,position,list.get(position).getUrl());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView img;
        TextView txt;
        public ViewHolder(View itemView) {
            super(itemView);
            linearLayout= (LinearLayout) itemView.findViewById(R.id.line);
            img= (ImageView) itemView.findViewById(R.id.img);
            txt= (TextView) itemView.findViewById(R.id.txt);
        }
    }
    public void setList(List<XinWen.ResultBean.DataBean> list){
        this.list=list;
    }
}
