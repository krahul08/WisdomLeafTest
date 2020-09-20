package com.example.wisdomleaftest.dashboard.view;

import com.example.wisdomleaftest.dashboard.data.ImagesResponseData;

import java.util.ArrayList;

public interface ShowImagesView {

    void showProgress(boolean show);

    void showMovieDetails(ArrayList<ImagesResponseData> imagesResponseData);

    void showError(String error);
}
