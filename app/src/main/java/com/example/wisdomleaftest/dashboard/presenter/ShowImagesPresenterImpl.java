package com.example.wisdomleaftest.dashboard.presenter;


import com.example.wisdomleaftest.dashboard.ImagesCallback;
import com.example.wisdomleaftest.dashboard.data.ImagesResponseData;
import com.example.wisdomleaftest.dashboard.provider.ShowImagesProviderImpl;
import com.example.wisdomleaftest.dashboard.view.ShowImagesView;

import java.util.ArrayList;

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
