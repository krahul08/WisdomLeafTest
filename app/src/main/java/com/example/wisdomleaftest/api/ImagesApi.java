package com.example.wisdomleaftest.api;

import com.example.wisdomleaftest.data.ImagesResponseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagesApi {

    @GET("?")
    Call<List<ImagesResponseData>> getImages(
            @Query("page") int page,
            @Query("limit") int limit
    );
}
