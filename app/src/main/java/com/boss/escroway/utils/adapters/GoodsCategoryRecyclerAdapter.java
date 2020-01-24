package com.boss.escroway.utils.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.escroway.R;
import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Good;
import com.boss.escroway.utils.GoodsItemListener;

import java.util.ArrayList;
import java.util.List;

public class GoodsCategoryRecyclerAdapter extends RecyclerView.Adapter<GoodsCategoryRecyclerAdapter.ViewHolder> {

    private final Activity mContext;
    private List<Good> mCategoriesList = new ArrayList<>();
    private int mResource;
//    private int mFragmentType;
//    private Repository mRepository;
//    private MutableLiveData<List<Good>> mDataList = new MutableLiveData<>();
//    private Observer<List<Good>> mObserver;

    public GoodsCategoryRecyclerAdapter(Activity context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mResource = R.layout.layout_badge_category_item;
        View itemView = LayoutInflater.from(mContext).inflate(mResource, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Good good = mCategoriesList.get(position);
        holder.bind(good.getKey(), good.getValue());
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public void onDataChanged(List<Good> goods) {
        mCategoriesList.clear();
        mCategoriesList.addAll(goods);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        private final TextView mTextViewCategoryName;
        private String mCategoryName;
        private String mKey;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewCategoryName = itemView.findViewById(R.id.text_category_name);
        }

        public void bind(String key, String categoryName) {
            mCategoryName = categoryName;
            mTextViewCategoryName.setText(categoryName);
            mKey = key;
            bindListener();
        }

        public void bindListener() {
            mItemView.setOnClickListener(new GoodsItemListener(mItemView, mKey, mCategoryName));
        }
    }

}
