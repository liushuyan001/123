package com.bwie.liu1025.main.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.bwie.liu1025.R;
import com.bwie.liu1025.main.model.bean.Banner;
import com.bwie.liu1025.main.presenter.MainPresenter;
import com.bwie.liu1025.main.view.IMainView;
import com.bwie.liu1025.main.view.adapter.BannerAdapter;
import com.bwie.liu1025.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment implements IMainView {
    private static final int BANNER_WHAT = 123;

    private ViewPager vpBanner;
    private EditText etSearch;
    private List<Banner.DataBean> bannerList;
    private BannerAdapter adapter;
    private MainPresenter mainPresenter;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == BANNER_WHAT) {
                int currentItem = vpBanner.getCurrentItem();
                if (currentItem < bannerList.size() - 1) {
                    currentItem++;
                } else {
                    currentItem = 0;
                }
                vpBanner.setCurrentItem(currentItem,false);
                handler.sendEmptyMessageDelayed(BANNER_WHAT,4000);
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_main, container, false);
        vpBanner = v.findViewById(R.id.vp_banner);
        etSearch = v.findViewById(R.id.et_search);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bannerList = new ArrayList<>();
        adapter = new BannerAdapter(getActivity(), bannerList);
        //给ViewPager设置设配器
        vpBanner.setAdapter(adapter);
        //初始化P层
        mainPresenter = new MainPresenter();
        //绑定
        mainPresenter.attach(this);
        //获取轮播图数据
        mainPresenter.getBanner();
        //轮播图4秒更换一次
        handler.sendEmptyMessageDelayed(BANNER_WHAT,4000);
        setListener();
    }

    private void setListener() {
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccess(List<Banner.DataBean> list) {
        if (list != null) {
            bannerList.clear();
            bannerList.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.dettach();
        handler.removeCallbacksAndMessages(null);
    }
}
