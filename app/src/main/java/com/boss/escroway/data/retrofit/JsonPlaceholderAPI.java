package com.boss.escroway.data.retrofit;

import com.boss.escroway.data.retrofit.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JsonPlaceholderAPI {

    @GET("posts")
    Call<List<Post>> getPosts();

    @GET("posts")
    Call<List<Post>> getPosts(
            @Query("userId") Integer[] uid,
            @Query("_sort") String sortee,
            @Query("_order") String orderee
    );
}
