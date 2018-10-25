package com.bwie.liu1025.sort.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.liu1025.R;
import com.bwie.liu1025.sort.model.bean.RightCategory;

import java.util.List;

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {
    private Context context;
    private List<RightCategory.DataBean> list;

    public RightAdapter(Context context, List<RightCategory.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_right_category_layout, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtRightName.setText(list.get(position).getName());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,3);
        holder.rvRightChild.setLayoutManager(layoutManager);
        ChildAdapter childAdapter = new ChildAdapter(context, list.get(position).getList());
        holder.rvRightChild.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtRightName;
        private RecyclerView rvRightChild;
        public ViewHolder(View itemView) {
            super(itemView);
            txtRightName = itemView.findViewById(R.id.txt_right_name);
            rvRightChild = itemView.findViewById(R.id.rv_right_child);
        }
    }
}
