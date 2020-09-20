package com.example.wisdomleaftest.presenter;


import com.example.wisdomleaftest.ImagesCallback;
import com.example.wisdomleaftest.data.ImagesResponseData;
import com.example.wisdomleaftest.provider.ShowImagesProviderImpl;
import com.example.wisdomleaftest.view.ShowImagesView;

import java.util.ArrayList;
import java.util.List;

public class ShowImagesPresenterImpl implements ShowImagesPresenter {

    private ShowImagesView showImagesView;
    private ShowImagesProviderImpl showImagesProvider;

    public ShowImagesPresenterImpl(ShowImagesView showImagesView, ShowImagesProviderImpl showImagesProvider) {
        this.showImagesView = showImagesView;
        this.showImagesProvider = showImagesProvider;
    }

    @Override
    public void getImages(int page, int limit) {
        showImagesView.showProgress(true);
        showImagesProvider.getImages(page, limit, new ImagesCallback() {
            @Override
            public void onSuccess(ArrayList<ImagesResponseData> imagesResponseData) {
                showImagesView.showProgress(false);
                showImagesView.showMovieDetails(imagesResponseData);
            }

            @Override
            public void onFailure() {
                showImagesView.showProgress(false);
                showImagesView.showError("Something went wrong..");
            }
        });
    }
}
