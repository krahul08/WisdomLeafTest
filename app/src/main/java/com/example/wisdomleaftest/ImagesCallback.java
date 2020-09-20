package com.example.wisdomleaftest;

import com.example.wisdomleaftest.data.ImagesResponseData;

import java.util.ArrayList;
import java.util.List;

public interface ImagesCallback {
    void onSuccess(ArrayList<ImagesResponseData> imagesResponseData);

    void onFailure();
}
