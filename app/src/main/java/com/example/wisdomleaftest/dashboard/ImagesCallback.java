package com.example.wisdomleaftest.dashboard;

import com.example.wisdomleaftest.dashboard.data.ImagesResponseData;

import java.util.ArrayList;

public interface ImagesCallback {
    void onSuccess(ArrayList<ImagesResponseData> imagesResponseData);

    void onFailure();
}
