package com.bwie.liu1025.sort.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.liu1025.R;
import com.bwie.liu1025.sort.model.bean.LeftCategory;

import java.util.List;

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {
    private Context context;
    private List<LeftCategory.DataBean> list;

    public LeftAdapter(Context context, List<LeftCategory.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface onItemClickListener{
        void onItemClick(int cid);
    }

    private onItemClickListener listener;

    public void setOnItemClickListener(onItemClickListener listener){
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_left_category_layout, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.txtLeftName.setText(list.get(position).getName());
        holder.txtLeftName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(list.get(position).getCid());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtLeftName;
        public ViewHolder(View itemView) {
            super(itemView);
            txtLeftName = itemView.findViewById(R.id.txt_left_name);
        }
    }

}
