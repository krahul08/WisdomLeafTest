package com.example.wisdomleaftest.api;

import com.example.wisdomleaftest.data.ImagesResponseData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagesApi {

    @GET("v2/list?")
    Call<ImagesResponseData> getImages(
            @Query("page") int page,
            @Query("limit") int limit
    );
}
