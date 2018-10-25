package com.bwie.liu1025.sort.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.liu1025.R;
import com.bwie.liu1025.sort.model.bean.RightCategory;

import java.util.List;

public class ChildAdapter extends RecyclerView.Adapter<ChildAdapter.ViewHolder> {
    private Context context;
    private List<RightCategory.DataBean.ListBean> list;

    public ChildAdapter(Context context, List<RightCategory.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_category_child, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtChildName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getIcon()).into(holder.imgChildLogo);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgChildLogo;
        private TextView txtChildName;
        public ViewHolder(View itemView) {
            super(itemView);
            imgChildLogo = itemView.findViewById(R.id.img_child_logo);
            txtChildName = itemView.findViewById(R.id.txt_child_name);
        }
    }
}
