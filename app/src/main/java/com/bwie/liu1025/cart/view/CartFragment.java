package com.bwie.liu1025.cart.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.liu1025.R;
import com.bwie.liu1025.cart.model.bean.Cart;
import com.bwie.liu1025.cart.presenter.CartPresenter;
import com.bwie.liu1025.cart.view.adapter.CartAdapter;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements ICartView {

    private Button btnClear;
    private CheckBox cbTotalAll;
    private TextView txtNum;
    private RecyclerView rvCart;
    private List<Cart.DataBean> cartList;
    private CartPresenter cartPresenter;
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cart, container, false);
        btnClear = v.findViewById(R.id.btn_clear);
        rvCart = v.findViewById(R.id.rv_cart);
        cbTotalAll = v.findViewById(R.id.cb_total_all);
        txtNum = v.findViewById(R.id.txt_num);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        cartList = new ArrayList<>();
        cartPresenter = new CartPresenter();
        cartPresenter.attach(this);
        cartPresenter.getCart();

        cartAdapter = new CartAdapter(getActivity(), cartList);

        cartAdapter.setOnItemShopperClickListener(new CartAdapter.onItemShopperClickListener() {
            @Override
            public void onItemShopperClick(int position, boolean isChecked) {
                if (!isChecked) {
                    cbTotalAll.setChecked(false);
                }else{
                    boolean isAllShopperChecked = true;
                    for (Cart.DataBean dataBean : cartList) {
                        if (!dataBean.isChecked()) {
                            isAllShopperChecked = false;
                            break;
                        }
                    }
                    cbTotalAll.setChecked(isAllShopperChecked);
                }
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvCart.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvCart.setLayoutManager(layoutManager);
        rvCart.setAdapter(cartAdapter);

        setListener();
    }

    private void setListener() {
        cbTotalAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = cbTotalAll.isChecked();
                for (Cart.DataBean dataBean : cartList) {
                    dataBean.setChecked(checked);
                    List<Cart.DataBean.ListBean> list = dataBean.getList();
                    for (Cart.DataBean.ListBean listBean : list) {
                        listBean.setChecked(checked);
                    }
                }
                cartAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSuccess(List<Cart.DataBean> list) {
        if (list != null) {
            cartList.clear();
            cartList.addAll(list);
            cartAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
    }
}
