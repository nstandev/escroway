package com.boss.escroway.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Subcategory;

import java.util.List;

public class GoodsSubcategoryViewModel extends AndroidViewModel {
    private MutableLiveData<List<Subcategory>> mGoodsSubcategoryLiveData;
    public static MutableLiveData<String> mCategoryKey = new MutableLiveData<>();


    public GoodsSubcategoryViewModel(@NonNull Application application) {
        super(application);
        Repository repository = new Repository(application);
        mGoodsSubcategoryLiveData = repository.getGoodsSubcatList("Goods", mCategoryKey.getValue());
    }

    public MutableLiveData<List<Subcategory>> getGoodsSubcategoryLiveData() {
        return mGoodsSubcategoryLiveData;
    }

}
