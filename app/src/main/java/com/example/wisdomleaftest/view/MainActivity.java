package com.example.wisdomleaftest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.wisdomleaftest.R;
import com.example.wisdomleaftest.data.ImagesResponseData;
import com.example.wisdomleaftest.presenter.ShowImagesPresenter;
import com.example.wisdomleaftest.presenter.ShowImagesPresenterImpl;
import com.example.wisdomleaftest.provider.ShowImagesProviderImpl;
import com.example.wisdomleaftest.utils.PaginationListener;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.wisdomleaftest.utils.PaginationListener.PAGE_START;

public class MainActivity extends AppCompatActivity implements ShowImagesView, SwipeRefreshLayout.OnRefreshListener {


    @BindView(R.id.imagesList)
    RecyclerView imagesList;

    @BindView(R.id.progress)
    ProgressBar progress_bar;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout refresh;

    private ImagesListAdapter imagesListAdapter;
    ShowImagesPresenter showImagesPresenter;
    private boolean loading = true;
    StaggeredGridLayoutManager layoutManager;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private int currentPage = PAGE_START;
    private boolean isLastPage = false;
    private int pageLimit = 10;
    private boolean isLoading = false;
    int itemCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        refresh.setOnRefreshListener(this);

        showImagesPresenter = new ShowImagesPresenterImpl(this, new ShowImagesProviderImpl());

        layoutManager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        imagesListAdapter = new ImagesListAdapter(this, new ArrayList<>());
        imagesList.setLayoutManager(layoutManager);
        imagesList.setHasFixedSize(true);
        imagesList.setAdapter(imagesListAdapter);
        callApi();
        imagesList.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage++;
                callApi();
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    private void callApi() {
        showImagesPresenter.getImages(currentPage, pageLimit);
    }

    @Override
    public void showProgress(boolean show) {
        if (show) {
            if (!isLoading)
                progress_bar.setVisibility(View.VISIBLE);
        } else {
            progress_bar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMovieDetails(ArrayList<ImagesResponseData> imagesResponseData) {
        if (imagesResponseData.size() != 0) {
            itemCount++;
            refresh.setRefreshing(false);
            final ArrayList<ArrayList<ImagesResponseData>> items = new ArrayList<ArrayList<ImagesResponseData>>(Collections.singleton(imagesResponseData));
            items.add(imagesResponseData);

            if (currentPage != PAGE_START) imagesListAdapter.removeLoading();
            imagesListAdapter.addItems(imagesResponseData);

            if (currentPage < pageLimit) {
                imagesListAdapter.addLoading();
            } else {
                isLastPage = true;
            }
            isLoading = false;
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        itemCount = 0;
        currentPage = PAGE_START;
        isLastPage = false;
        imagesListAdapter.clear();
        callApi();
    }
}
