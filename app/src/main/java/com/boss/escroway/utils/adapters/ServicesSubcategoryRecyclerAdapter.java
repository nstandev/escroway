package com.boss.escroway.utils.adapters;

import android.app.Activity;
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
import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.utils.OnClickNextBtnListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServicesSubcategoryRecyclerAdapter extends RecyclerView.Adapter<ServicesSubcategoryRecyclerAdapter.ViewHolder>{

    private final Activity mContext;
    private OnClickNextBtnListener mOnClickNextBtnListener;
    private String mCategoryKey;
    private List<Subcategory> mSubcategoriesList = new ArrayList<>();
    private Repository mRepository;
    private MutableLiveData<List<Subcategory>> mDataList;
    private Observer<List<Subcategory>> mObserver;
    private HashMap<Integer, Boolean> itemsClickedState = new HashMap<>();


    public ServicesSubcategoryRecyclerAdapter(Activity context, String categoryKey, OnClickNextBtnListener onClickNextBtnListener) {
        mContext = context;
        mCategoryKey = categoryKey;
        mOnClickNextBtnListener = onClickNextBtnListener;
    }

    private void populateList(List<Subcategory> subcategories){
        mSubcategoriesList.clear();
        mSubcategoriesList.addAll(subcategories);
        setItemsClickedState(mSubcategoriesList);
        notifyDataSetChanged();
    }

    public void setItemsClickedState(List<Subcategory> subcategories) {
        itemsClickedState.clear();
        int index = 0;
        for(Subcategory subcategory : subcategories){
            itemsClickedState.put(index, false);
            ++index;
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
//        mRepository = new Repository(mContext);
//        mDataList = mRepository.getServicesSubcatList("Services", mCategoryKey);
//        mObserver = new Observer<List<Subcategory>>() {
//            @Override
//            public void onChanged(List<Subcategory> subcategories) {
//                populateList(subcategories);
//            }
//        };
//        mDataList.observeForever(mObserver);
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
//        mDataList.removeObserver(mObserver);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.layout_subcategory_item, parent, false);
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
