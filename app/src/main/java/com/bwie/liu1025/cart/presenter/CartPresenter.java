package com.bwie.liu1025.cart.presenter;

import com.bwie.liu1025.cart.model.CartModel;
import com.bwie.liu1025.cart.model.bean.Cart;
import com.bwie.liu1025.cart.view.ICartView;
import com.bwie.liu1025.inter.ICallback;
import com.bwie.liu1025.utils.URLValue;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CartPresenter {
    private ICartView iCartView;
    private CartModel cartModel;

    public void attach(ICartView iCartView) {
        this.iCartView = iCartView;
        cartModel = new CartModel();
    }

    public void getCart(){
        Type type = new TypeToken<Cart>(){}.getType();
        cartModel.getData(URLValue.CART_URL + "21244", new ICallback() {
            @Override
            public void onSuccess(Object obj) {
                Cart cart = (Cart) obj;
                if (cart != null) {
                    List<Cart.DataBean> data = cart.getData();
                    iCartView.onSuccess(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                iCartView.onFailed(e);
            }
        },type);
    }

    public void detach() {
        if (iCartView != null) {
            iCartView = null;
        }
    }
}
