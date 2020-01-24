package com.boss.escroway.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Subcategory;

import java.util.List;

public class ServicesSubcategoryViewModel extends AndroidViewModel {
    private MutableLiveData<List<Subcategory>> mGoodsSubcategoryLiveData;
    private MutableLiveData<List<Subcategory>> mServicesSubcategoryLiveData;
    public static MutableLiveData<String> mCategoryKey = new MutableLiveData<>();


    public ServicesSubcategoryViewModel(@NonNull Application application) {
        super(application);
        Repository repository = new Repository(application);
        mGoodsSubcategoryLiveData = repository.getGoodsSubcatList("Goods", mCategoryKey.getValue());
        mServicesSubcategoryLiveData = repository.getServicesSubcatList("Services", mCategoryKey.getValue());
    }

    public MutableLiveData<List<Subcategory>> getGoodsSubcategoryLiveData() {
        return mGoodsSubcategoryLiveData;
    }

    public MutableLiveData<List<Subcategory>> getServicesSubcategoryLiveData() {
        return mServicesSubcategoryLiveData;
    }

}
