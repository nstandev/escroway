package com.boss.escroway.utils;

import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.boss.escroway.R;
import com.boss.escroway.ui.fragment.GoodsSubCategoryFragment;

public class GoodsItemListener implements View.OnClickListener {

    private String mCategoryKey;
    private String mCategoryName;
    private AppCompatActivity mContext;

    public GoodsItemListener(View itemView, String categoryKey, String categoryName) {
        mContext = (AppCompatActivity)itemView.getContext();
        mCategoryKey = categoryKey;
        mCategoryName = categoryName;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(mContext, "goods item listener at work", Toast.LENGTH_LONG).show();
        mContext.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, GoodsSubCategoryFragment.newInstance(mCategoryKey, mCategoryName))
                .addToBackStack(null)
                .commit();
    }
}
