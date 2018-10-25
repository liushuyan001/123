package com.bwie.liu1025.main.model;

import com.bwie.liu1025.inter.ICallback;
import com.bwie.liu1025.utils.HttpUtils;

import java.lang.reflect.Type;

public class MainModel {
    public void getData(String url , ICallback callback , Type type){
        HttpUtils.getInstance().get(url,callback,type);
    }
}
