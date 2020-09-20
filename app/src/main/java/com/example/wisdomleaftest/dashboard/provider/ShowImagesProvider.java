package com.example.wisdomleaftest.dashboard.provider;

import com.example.wisdomleaftest.dashboard.ImagesCallback;

public interface ShowImagesProvider {

    void getImages(int page, int limit, ImagesCallback imagesCallback);
}
