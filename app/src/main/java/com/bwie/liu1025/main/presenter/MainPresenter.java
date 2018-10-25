package com.bwie.liu1025.main.presenter;

import com.bwie.liu1025.inter.ICallback;
import com.bwie.liu1025.main.model.MainModel;
import com.bwie.liu1025.main.model.bean.Banner;
import com.bwie.liu1025.main.view.IMainView;
import com.bwie.liu1025.utils.URLValue;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainPresenter {
    private IMainView iMainView;
    private MainModel mainModel;

    public void attach(IMainView iMainView) {
        this.iMainView = iMainView;
        mainModel = new MainModel();
    }

    public void getBanner(){
        Type type = new TypeToken<Banner>(){}.getType();
        mainModel.getData(URLValue.BANNER_URL, new ICallback() {
            @Override
            public void onSuccess(Object obj) {
                Banner banner = (Banner) obj;
                if (banner != null) {
                    List<Banner.DataBean> data = banner.getData();
                    iMainView.onSuccess(data);
                }
            }

            @Override
            public void onFailed(Exception e) {
                iMainView.onFailed(e);
            }
        },type);
    }

    public void dettach() {
        if (iMainView != null) {
            iMainView = null;
        }
    }
}
