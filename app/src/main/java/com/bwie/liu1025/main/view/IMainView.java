package com.bwie.liu1025.main.view;

import com.bwie.liu1025.main.model.bean.Banner;

import java.util.List;

public interface IMainView {
    void onSuccess(List<Banner.DataBean> list);
    void onFailed(Exception e);
}
