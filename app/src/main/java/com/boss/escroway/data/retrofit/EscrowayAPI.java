package com.boss.escroway.data.retrofit;

import com.boss.escroway.data.retrofit.models.Subcategory;
import com.boss.escroway.data.retrofit.models.Wrapper;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface EscrowayAPI {

    @Headers({"Content-Type: application/json"})
    @GET("5e07129efa8bf100219579c9")
    Call<Wrapper> getWrapper();

    @Headers({"Content-Type: application/json"})
    @GET("category/5e07129efa8bf100219579c9")
    Call<List<Subcategory>> getGoodsSubcategories(@Query("productType") String type, @Query("category") String category);

}
