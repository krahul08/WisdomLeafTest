package com.example.wisdomleaftest.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wisdomleaftest.R;
import com.example.wisdomleaftest.data.ImagesResponseData;
import com.example.wisdomleaftest.presenter.ShowImagesPresenter;
import com.example.wisdomleaftest.presenter.ShowImagesPresenterImpl;
import com.example.wisdomleaftest.provider.ShowImagesProviderImpl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements ShowImagesView {


    @BindView(R.id.progress)
    ProgressBar progressBar;

    @BindView(R.id.imagesList)
    RecyclerView imagesList;

    private ImagesListAdapter imagesListAdapter;
    ShowImagesPresenter showImagesPresenter;
    private boolean loading = true;
    GridLayoutManager layoutManager;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        layoutManager = new GridLayoutManager(getApplicationContext(), 2);

        imagesListAdapter = new ImagesListAdapter(this);
        showImagesPresenter = new ShowImagesPresenterImpl(this, new ShowImagesProviderImpl());
        callApi();

        imagesList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            Log.d("hjfjjjfjfjfjfjfjf", "Last Item Wow !");
                            // Do pagination.. i.e. fetch new data
                            callApi();
                        }
                    }
                }
            }
        });
    }

    private void callApi() {
        showImagesPresenter.getImages(2, 20);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMovieDetails(List<ImagesResponseData> imagesResponseData) {
        if (imagesResponseData.size() != 0) {
            imagesListAdapter.setData(imagesResponseData);
            imagesList.setLayoutManager(layoutManager);
            imagesList.setHasFixedSize(true);
            imagesList.setAdapter(imagesListAdapter);
            imagesListAdapter.notifyDataSetChanged();

        }

    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
