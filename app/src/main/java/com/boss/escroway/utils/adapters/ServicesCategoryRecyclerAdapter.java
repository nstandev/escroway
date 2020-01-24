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
import com.boss.escroway.data.retrofit.models.Service;
import com.boss.escroway.utils.ServicesItemListener;

import java.util.ArrayList;
import java.util.List;

public class ServicesCategoryRecyclerAdapter extends RecyclerView.Adapter<ServicesCategoryRecyclerAdapter.ViewHolder> {

    private final Activity mContext;
    private List<Service> mCategoriesList = new ArrayList<>();
//    private Repository mRepository;
//    private MutableLiveData<List<Service>> mDataList = new MutableLiveData<>();
//    private Observer<List<Service>> mObserver;

    public ServicesCategoryRecyclerAdapter(Activity context) {
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_badge_category_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Service service = mCategoriesList.get(position);
        holder.bind(service.getKey(), service.getValue());
    }

    @Override
    public int getItemCount() {
        return mCategoriesList.size();
    }

    public void onDataChanged(List<Service> serviceList) {
        mCategoriesList.clear();
        mCategoriesList.addAll(serviceList);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mItemView;
        private final TextView mTextViewCategoryName;
        private String mCategoryName;
        private String mCategoryKey;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mItemView = itemView;
            mTextViewCategoryName = itemView.findViewById(R.id.text_category_name);
        }

        public void bind(String categoryKey, String categoryName) {
            mCategoryName = categoryName;
            mTextViewCategoryName.setText(categoryName);
            mCategoryKey = categoryKey;
            bindListener();
        }

        public void bindListener() {
            mItemView.setOnClickListener(new ServicesItemListener(mItemView, mCategoryKey, mCategoryName));
        }
    }

}
