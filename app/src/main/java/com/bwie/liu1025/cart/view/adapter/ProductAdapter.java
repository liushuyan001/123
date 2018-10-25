package com.bwie.liu1025.cart.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwie.liu1025.R;
import com.bwie.liu1025.cart.model.bean.Cart;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context context;
    private List<Cart.DataBean.ListBean> list;

    public ProductAdapter(Context context, List<Cart.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface onProductClickListener{
        void onProductClick(int position,boolean isChecked);
    }

    private onProductClickListener listener;

    public void setOnProductClickListener(onProductClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_cart_product, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        String images = list.get(position).getImages();
        String replace = images.replace("https", "http");
        String[] split = replace.split("\\|");
        Glide.with(context).load(split[0]).into(holder.imgCartProduct);
        holder.txtCartProductName.setText(list.get(position).getTitle());
        holder.txtCartProductPrice.setText("￥"+list.get(position).getPrice());
        holder.cbCartProduct.setChecked(list.get(position).isChecked());
        holder.cbCartProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onProductClick(position,holder.cbCartProduct.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cbCartProduct;
        private final ImageView imgCartProduct;
        private final TextView txtCartProductName;
        private final TextView txtCartProductPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            cbCartProduct = itemView.findViewById(R.id.cb_cart_product);
            imgCartProduct = itemView.findViewById(R.id.img_cart_product);
            txtCartProductName = itemView.findViewById(R.id.txt_cart_product_name);
            txtCartProductPrice = itemView.findViewById(R.id.txt_cart_product_price);
        }
    }
}
