package com.example.wisdomleaftest;

import com.example.wisdomleaftest.data.ImagesResponseData;

import java.util.List;

public interface ImagesCallback {
    void onSuccess(List<ImagesResponseData> imagesResponseData);

    void onFailure();
}
