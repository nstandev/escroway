package com.boss.escroway.data;

import android.app.Activity;
import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.boss.escroway.data.retrofit.EscrowayAPI;
import com.boss.escroway.data.retrofit.models.Good;
import com.boss.escroway.data.retrofit.models.Service;
import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.data.retrofit.models.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {

    private Application mApplication;
    private MutableLiveData<List<Good>> mGoodList = new MutableLiveData<>();
    private MutableLiveData<List<Service>> mServicesList = new MutableLiveData<>();
    private MutableLiveData<List<Subcategory>> mServicesSubcatList = new MutableLiveData<>();
    private MutableLiveData<List<Subcategory>> mGoodsSubcatList = new MutableLiveData<>();
    private Retrofit mRetrofit;
    private Activity mContext;

    public Repository(Application application) {
        mApplication = application;
        mRetrofit = new  Retrofit.Builder()
                .baseUrl("https://escroway-api.herokuapp.com/api/v1/constants/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Repository(Activity context) {
        mContext = context;
        mApplication = null;
        mRetrofit = new  Retrofit.Builder()
                .baseUrl("https://escroway-api.herokuapp.com/api/v1/constants/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public MutableLiveData<List<Good>> getGoodsList() {
        getGoodsCategories();
        return mGoodList;
    }

    public MutableLiveData<List<Service>> getServicesList() {
        getServicesCategories();
        return mServicesList;
    }

    public MutableLiveData<List<Subcategory>> getServicesSubcatList(String type, String category) {
        if(type == null && category == null){
            mServicesSubcatList = new MutableLiveData<>();
            return mServicesSubcatList;
        }
        getServicesSubcategories(type, category);
        return mServicesSubcatList;
    }

    public MutableLiveData<List<Subcategory>> getGoodsSubcatList(String type, String category) {
        if(type == null && category == null){
            mGoodsSubcatList = new MutableLiveData<>();
        }
        getGoodsSubcategories(type, category);
        return mGoodsSubcatList;
    }

    private void getGoodsCategories() {
        EscrowayAPI escrowayAPI = mRetrofit.create(EscrowayAPI.class);
        Call<Wrapper> wrapper = escrowayAPI.getWrapper();

        wrapper.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Wrapper wrapper = response.body();
                List<Good> goodsList = wrapper.getCategories().getGoods();
                mGoodList.setValue(goodsList);
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                System.out.println("Failed");
            }
        });

    }


    private void getServicesCategories() {
        EscrowayAPI escrowayAPI = mRetrofit.create(EscrowayAPI.class);
        Call<Wrapper> wrapper = escrowayAPI.getWrapper();

        wrapper.enqueue(new Callback<Wrapper>() {
            @Override
            public void onResponse(Call<Wrapper> call, Response<Wrapper> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                Wrapper wrapper = response.body();
                List<Service> servicesList = wrapper.getCategories().getServices();
                mServicesList.setValue(servicesList);
            }

            @Override
            public void onFailure(Call<Wrapper> call, Throwable t) {
                System.out.println("Failed");
            }
        });

    }


    public void getServicesSubcategories(String type, String category) {
        EscrowayAPI escrowayAPI = mRetrofit.create(EscrowayAPI.class);
        Call<List<Subcategory>> subcategories = escrowayAPI.getGoodsSubcategories(type, category);
        subcategories.enqueue(new Callback<List<Subcategory>>() {
            @Override
            public void onResponse(Call<List<Subcategory>> call, Response<List<Subcategory>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(mApplication, "response code: " + response.code(), Toast.LENGTH_LONG)
                            .show();
                    return;
                }
                List<Subcategory> subcategoryList = response.body();
                mServicesSubcatList.setValue(subcategoryList);
            }

            @Override
            public void onFailure(Call<List<Subcategory>> call, Throwable t) {
                Toast.makeText(mContext, "failed to get services subcat with error: " + t.getMessage(), Toast.LENGTH_LONG)
                        .show();
            }
        });

    }

    public void getGoodsSubcategories(String type, String category) {
        EscrowayAPI escrowayAPI = mRetrofit.create(EscrowayAPI.class);
        Call<List<Subcategory>> subcategories = escrowayAPI.getGoodsSubcategories(type, category);
        subcategories.enqueue(new Callback<List<Subcategory>>() {
            @Override
            public void onResponse(Call<List<Subcategory>> call, Response<List<Subcategory>> response) {
                if (!response.isSuccessful()) {
                    return;
                }
                List<Subcategory> subcategoryList = response.body();
                mGoodsSubcatList.setValue(subcategoryList);
            }

            @Override
            public void onFailure(Call<List<Subcategory>> call, Throwable t) {
                Toast.makeText(mApplication, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
