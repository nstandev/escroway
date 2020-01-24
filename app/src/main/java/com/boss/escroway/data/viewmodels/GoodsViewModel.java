package com.boss.escroway.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Good;

import java.util.List;

public class GoodsViewModel extends AndroidViewModel {

    private MutableLiveData<List<Good>> mGoodsCategoryLiveData;

    public GoodsViewModel(@NonNull Application application) {
        super(application);
        Repository repository = new Repository(application);
        mGoodsCategoryLiveData = repository.getGoodsList();
    }

    public MutableLiveData<List<Good>> getGoodsCategoryLiveData() {
        return mGoodsCategoryLiveData;
    }
}
