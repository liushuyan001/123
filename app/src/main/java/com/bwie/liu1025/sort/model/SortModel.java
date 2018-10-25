package com.bwie.liu1025.sort.model;

import com.bwie.liu1025.inter.ICallback;
import com.bwie.liu1025.utils.HttpUtils;

import java.lang.reflect.Type;

public class SortModel {
    public void getData(String url , ICallback callback , Type type){
        HttpUtils.getInstance().get(url,callback,type);
    }
}
