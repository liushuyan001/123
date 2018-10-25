package com.bwie.liu1025.cart.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.liu1025.R;
import com.bwie.liu1025.cart.model.bean.Cart;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private Context context;
    private List<Cart.DataBean> list;

    public CartAdapter(Context context, List<Cart.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface onItemShopperClickListener {
        void onItemShopperClick(int position, boolean isChecked);
    }

    private onItemShopperClickListener listener;

    public void setOnItemShopperClickListener(onItemShopperClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = View.inflate(context, R.layout.item_cart_shopper, null);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.txtShopperName.setText(list.get(position).getSellerName());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        holder.rvCartProduct.setLayoutManager(layoutManager);
        final ProductAdapter productAdapter = new ProductAdapter(context, list.get(position).getList());
        holder.rvCartProduct.setAdapter(productAdapter);
        holder.cbCartShopper.setChecked(list.get(position).isChecked());
        productAdapter.setOnProductClickListener(new ProductAdapter.onProductClickListener() {
            @Override
            public void onProductClick(int position, boolean isChecked) {
                if (!isChecked) {
                    list.get(position).setChecked(false);
                    listener.onItemShopperClick(position, false);
                } else {
                    boolean isAllProductSelected = true;
                    for (Cart.DataBean.ListBean listBean : list.get(position).getList()) {
                        if (!listBean.isChecked()) {
                            isAllProductSelected = false;
                            break;
                        }
                    }
                    list.get(position).setChecked(isAllProductSelected);
                    listener.onItemShopperClick(position,true);
                }
                notifyDataSetChanged();
            }
        });

        holder.cbCartShopper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).setChecked(holder.cbCartShopper.isChecked());

                for (Cart.DataBean.ListBean listBean : list.get(position).getList()) {
                    listBean.setChecked(holder.cbCartShopper.isChecked());
                }
                productAdapter.notifyDataSetChanged();
                if (listener != null) {
                    listener.onItemShopperClick(position,holder.cbCartShopper.isChecked());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox cbCartShopper;
        private final RecyclerView rvCartProduct;
        private final TextView txtShopperName;

        public ViewHolder(View itemView) {
            super(itemView);
            txtShopperName = itemView.findViewById(R.id.txt_shopper_name);
            cbCartShopper = itemView.findViewById(R.id.cb_cart_shopper);
            rvCartProduct = itemView.findViewById(R.id.rv_cart_product);
        }
    }
}
