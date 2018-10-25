package com.bwie.liu1025.cart.view;

import com.bwie.liu1025.cart.model.bean.Cart;

import java.util.List;

public interface ICartView {
    void onSuccess(List<Cart.DataBean> list);
    void onFailed(Exception e);
}
