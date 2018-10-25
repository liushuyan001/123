package com.bwie.liu1025.sort.view;

import com.bwie.liu1025.sort.model.bean.LeftCategory;
import com.bwie.liu1025.sort.model.bean.RightCategory;

import java.util.List;

public interface ISortView {
    void getLeftData(List<LeftCategory.DataBean> list);
    void getRightData(List<RightCategory.DataBean> list);
    void onFailed(Exception e);
}
