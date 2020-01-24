package com.boss.escroway.data.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.boss.escroway.data.Repository;
import com.boss.escroway.data.retrofit.models.Service;

import java.util.List;

public class ServicesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Service>> mServicesListLiveData;

    public ServicesViewModel(@NonNull Application application) {
        super(application);
        Repository repository = new Repository(application);
        mServicesListLiveData = repository.getServicesList();
    }

    public MutableLiveData<List<Service>> getServicesListLiveData() {
        return mServicesListLiveData;
    }
}
