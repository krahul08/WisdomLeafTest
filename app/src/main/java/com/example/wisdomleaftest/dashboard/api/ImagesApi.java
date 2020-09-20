package com.example.wisdomleaftest.dashboard.api;

import com.example.wisdomleaftest.dashboard.data.ImagesResponseData;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImagesApi {

    @GET("?")
    Call<ArrayList<ImagesResponseData>> getImages(
            @Query("page") int page,
            @Query("limit") int limit
    );
}
