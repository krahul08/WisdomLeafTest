package com.example.wisdomleaftest.provider;

import com.example.wisdomleaftest.ImagesCallback;

public interface ShowImagesProvider {

    void getImages(int page, int limit, ImagesCallback imagesCallback);
}
