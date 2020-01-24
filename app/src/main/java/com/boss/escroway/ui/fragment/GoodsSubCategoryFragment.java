package com.boss.escroway.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.escroway.R;
import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.data.viewmodels.GoodsSubcategoryViewModel;
import com.boss.escroway.utils.ActivateNextButton;
import com.boss.escroway.utils.adapters.GoodsSubcategoryRecyclerAdapter;
import com.boss.escroway.utils.OnClickNextBtnListener;

import java.util.HashMap;
import java.util.List;

public class GoodsSubCategoryFragment extends Fragment implements OnClickNextBtnListener {

    private static String xCategoryKey;
    private static String xCategoryName;
    private View mView;
    private Activity mContext;
    private boolean mActivateNextBtn;
    private OnFragmentInteractionListener mListener;
    private GoodsSubcategoryRecyclerAdapter mSubcategoryRecyclerAdapter;

    public static GoodsSubCategoryFragment newInstance(String categoryKey, String categoryName) {
        xCategoryName = categoryName;
        xCategoryKey = categoryKey;
        GoodsSubCategoryFragment fragment = new GoodsSubCategoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_subcat, container, false);
        TextView textViewSubcatTitle = mView.findViewById(R.id.text_subcategory_title);
        textViewSubcatTitle.setText(xCategoryName);

        setupRecyclerView();
        setupViewModel();
        setListenerOnBackButton();

        return mView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof GoodsSubCategoryFragment.OnFragmentInteractionListener) {
            mListener = (GoodsSubCategoryFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    private void setupRecyclerView(){
        RecyclerView recyclerView = mView.findViewById(R.id.recycler_view_sub_cat);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);

        mSubcategoryRecyclerAdapter = new GoodsSubcategoryRecyclerAdapter(xCategoryKey, this);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(mSubcategoryRecyclerAdapter);
    }

    private void setupViewModel() {
        GoodsSubcategoryViewModel.mCategoryKey.setValue(xCategoryKey);

        GoodsSubcategoryViewModel goodsSubcategoryViewModel = ViewModelProviders.of(this).get(GoodsSubcategoryViewModel.class);

        goodsSubcategoryViewModel.getGoodsSubcategoryLiveData().observe(this, new Observer<List<Subcategory>>() {
            @Override
            public void onChanged(List<Subcategory> subcategories) {
                Toast.makeText(mContext, "changed", Toast.LENGTH_LONG).show();
                mSubcategoryRecyclerAdapter.onDataChanged(subcategories);
            }
        });
    }

    private void setListenerOnBackButton(){
        View backButton = mView.findViewById(R.id.image_view_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "tapped back button", Toast.LENGTH_LONG).show();
                mContext.onBackPressed();
            }
        });
    }

    @Override
    public void onClickFormatNextBtn(HashMap<Integer, Boolean> itemClickedStates) {
        for(Boolean item : itemClickedStates.values()){
            if(item){
                mActivateNextBtn = true;
                mListener.activateNextButton();
                return;
            }
            mListener.deactivateNextButton();
            mActivateNextBtn = false;
        }
    }

    @Override
    public void onClickFormatNextBtn(boolean activateNextBtn) {

    }

    public interface OnFragmentInteractionListener extends ActivateNextButton {
        void onFragmentInteraction();
    }
}
