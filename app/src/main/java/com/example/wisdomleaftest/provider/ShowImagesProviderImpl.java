package com.example.wisdomleaftest.provider;

import android.util.Log;

import com.example.wisdomleaftest.ImagesCallback;
import com.example.wisdomleaftest.api.ImagesApi;
import com.example.wisdomleaftest.data.ImagesResponseData;
import com.example.wisdomleaftest.utils.RetrofitServiceProvider;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowImagesProviderImpl implements ShowImagesProvider {

    private ImagesApi imagesApi;

    public ShowImagesProviderImpl() {
        RetrofitServiceProvider serviceProvider = new RetrofitServiceProvider();
        imagesApi = serviceProvider.getRetrofit().create(ImagesApi.class);
    }

    @Override
    public void getImages(int page, int limit, final ImagesCallback imagesCallback) {
        Call<List<ImagesResponseData>> imagesResponseDataCall = imagesApi.getImages(page, limit);
        imagesResponseDataCall.enqueue(new Callback<List<ImagesResponseData>>() {
            @Override
            public void onResponse(Call<List<ImagesResponseData>> call, Response<List<ImagesResponseData>> response) {
                imagesCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ImagesResponseData>> call, Throwable t) {
                imagesCallback.onFailure();
                t.getMessage();

            }
        });

    }
}
