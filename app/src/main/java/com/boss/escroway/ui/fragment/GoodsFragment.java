package com.boss.escroway.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.escroway.data.retrofit.models.Good;
import com.boss.escroway.data.viewmodels.GoodsViewModel;
import com.boss.escroway.utils.adapters.GoodsCategoryRecyclerAdapter;
import com.boss.escroway.R;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.List;

public class GoodsFragment extends Fragment {

    public static final int GOODS_FRAGMENT_TYPE = 0;
    private Activity mContext;
    private View mView;
    private GoodsCategoryRecyclerAdapter mGoodsCategoryRecyclerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_categories, container, false);
        setupRecyclerView();
        setupViewModel();
        return mView;
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(mContext);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setAlignItems(AlignItems.STRETCH);
        flexboxLayoutManager.setJustifyContent(JustifyContent.FLEX_START);

        mGoodsCategoryRecyclerAdapter = new GoodsCategoryRecyclerAdapter(mContext);

        recyclerView.setLayoutManager(flexboxLayoutManager);
        recyclerView.setAdapter(mGoodsCategoryRecyclerAdapter);
    }


    private void setupViewModel() {
        GoodsViewModel goodsViewModel = ViewModelProviders.of(this).get(GoodsViewModel.class);
        goodsViewModel.getGoodsCategoryLiveData().observe(this, new Observer<List<Good>>() {
            @Override
            public void onChanged(List<Good> goods) {
                mGoodsCategoryRecyclerAdapter.onDataChanged(goods);
            }
        });
    }
}
