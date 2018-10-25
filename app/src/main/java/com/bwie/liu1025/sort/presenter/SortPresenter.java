package com.bwie.liu1025.sort.presenter;

import com.bwie.liu1025.inter.ICallback;
import com.bwie.liu1025.sort.model.SortModel;
import com.bwie.liu1025.sort.model.bean.LeftCategory;
import com.bwie.liu1025.sort.model.bean.RightCategory;
import com.bwie.liu1025.sort.view.ISortView;
import com.bwie.liu1025.utils.URLValue;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.security.auth.callback.Callback;

public class SortPresenter {
    private ISortView iSortView;
    private SortModel sortModel;

    public void attach(ISortView iSortView) {
        this.iSortView = iSortView;
        sortModel = new SortModel();
    }

    public void getLeftData() {
        Type type = new TypeToken<LeftCategory>() {
        }.getType();
        sortModel.getData(URLValue.SORT_LEFT, new ICallback() {
            @Override
            public void onSuccess(Object obj) {
                LeftCategory leftCategory = (LeftCategory) obj;
                if (leftCategory != null) {
                    List<LeftCategory.DataBean> data = leftCategory.getData();
                    if (data != null) {
                        iSortView.getLeftData(data);
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {
                iSortView.onFailed(e);
            }
        }, type);
    }

    //获取右边分类的数据
    public void getRightData(int cid) {
        Type type = new TypeToken<RightCategory>() {
        }.getType();
        sortModel.getData(URLValue.SORT_RIGHT + cid, new ICallback() {
            @Override
            public void onSuccess(Object obj) {
                RightCategory rightCategory = (RightCategory) obj;
                if (rightCategory != null) {
                    List<RightCategory.DataBean> data = rightCategory.getData();
                    if (data != null) {
                        iSortView.getRightData(data);
                    }
                }
            }

            @Override
            public void onFailed(Exception e) {
                iSortView.onFailed(e);
            }
        }, type);
    }

    public void detach() {
        if (iSortView != null) {
            iSortView = null;
        }
    }
}
