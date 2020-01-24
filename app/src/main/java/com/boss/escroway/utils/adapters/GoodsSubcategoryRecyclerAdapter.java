package com.boss.escroway.utils.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.boss.escroway.R;
import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Good;
import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.utils.OnClickNextBtnListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GoodsSubcategoryRecyclerAdapter extends RecyclerView.Adapter<GoodsSubcategoryRecyclerAdapter.ViewHolder>{

//    private final Activity mContext;
    private String mCategoryParam;
    private OnClickNextBtnListener mOnClickNextBtnListener;
    private List<Subcategory> mSubcategoriesList = new ArrayList<>();
    private Repository mRepository;
    private MutableLiveData<List<Subcategory>> mDataList;
    private Observer<List<Subcategory>> mObserver;
    private HashMap<Integer, Boolean> itemsClickedState = new HashMap<>();


    public GoodsSubcategoryRecyclerAdapter(String category, OnClickNextBtnListener onClickNextBtnListener) {
//        mContext = context;
        mCategoryParam = category;
        mOnClickNextBtnListener = onClickNextBtnListener;
    }

    public void setItemsClickedState(List<Subcategory> subcategories) {
        itemsClickedState.clear();
        int index = 0;
        for(Subcategory subcategory : subcategories){
            itemsClickedState.put(index, false);
            ++index;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_subcategory_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mSubcategoriesList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return mSubcategoriesList.size();
    }

    public void onDataChanged(List<Subcategory> subcategories) {
        mSubcategoriesList.clear();
        mSubcategoriesList.addAll(subcategories);
        setItemsClickedState(subcategories);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView mTextViewCategoryName;
        private final ImageView mImageView;
        private View mItemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewCategoryName = itemView.findViewById(R.id.text_category_name);
            mImageView = itemView.findViewById(R.id.image_checkable);
            mItemView = itemView;
            onBindListener();
        }

        public void bind(String categoryName) {
            mTextViewCategoryName.setText(categoryName);
        }

        public void onBindListener(){
            mItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = v.findViewById(R.id.image_checkable);
                    imageView.setSelected(!imageView.isSelected());

                    itemsClickedState.put(getAdapterPosition(), imageView.isSelected());
                    mOnClickNextBtnListener.onClickFormatNextBtn(itemsClickedState);
                }
            });
        }
    }
}
