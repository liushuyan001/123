package com.bwie.liu1025.sort.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bwie.liu1025.R;
import com.bwie.liu1025.sort.model.bean.LeftCategory;
import com.bwie.liu1025.sort.model.bean.RightCategory;
import com.bwie.liu1025.sort.presenter.SortPresenter;
import com.bwie.liu1025.sort.view.adapter.LeftAdapter;
import com.bwie.liu1025.sort.view.adapter.RightAdapter;

import java.util.ArrayList;
import java.util.List;

public class SortFragment extends Fragment implements ISortView {

    private RecyclerView rvLeft;
    private RecyclerView rvRight;
    private List<LeftCategory.DataBean> leftList;
    private List<RightCategory.DataBean> rightList;
    private SortPresenter sortPresenter;
    private LeftAdapter leftAdapter;
    private RightAdapter rightAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sort, container, false);
        rvLeft = v.findViewById(R.id.rv_left);
        rvRight = v.findViewById(R.id.rv_right);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leftList = new ArrayList<>();
        rightList = new ArrayList<>();

        sortPresenter = new SortPresenter();
        sortPresenter.attach(this);
        sortPresenter.getLeftData();
        sortPresenter.getRightData(1);

        leftAdapter = new LeftAdapter(getActivity(), leftList);
        RecyclerView.LayoutManager leftManager = new LinearLayoutManager(getActivity());
        rvLeft.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rvLeft.setItemAnimator(new DefaultItemAnimator());
        rvLeft.setLayoutManager(leftManager);
        rvLeft.setAdapter(leftAdapter);

        RecyclerView.LayoutManager rightManager = new LinearLayoutManager(getActivity());
        rvRight.setLayoutManager(rightManager);
        rvRight.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        rightAdapter = new RightAdapter(getActivity(), rightList);
        rvRight.setAdapter(rightAdapter);

        setListener();
    }

    private void setListener() {
        leftAdapter.setOnItemClickListener(new LeftAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int cid) {
                sortPresenter.getRightData(cid);
            }
        });
    }

    @Override
    public void getLeftData(List<LeftCategory.DataBean> list) {
        if (list != null) {
            leftList.clear();
            leftList.addAll(list);
            leftAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getRightData(List<RightCategory.DataBean> list) {
        if (list != null) {
            rightList.clear();
            rightList.addAll(list);
            rightAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailed(Exception e) {
        Toast.makeText(getActivity(),e.getMessage(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sortPresenter.detach();
    }
}
