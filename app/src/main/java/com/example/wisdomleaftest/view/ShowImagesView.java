package com.example.wisdomleaftest.view;

import com.example.wisdomleaftest.data.ImagesResponseData;

import java.util.ArrayList;
import java.util.List;

public interface ShowImagesView {

    void showProgress(boolean show);

    void showMovieDetails(ArrayList<ImagesResponseData> imagesResponseData);

    void showError(String error);
}
