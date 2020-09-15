package com.example.wisdomleaftest.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ShowImagesPresenter showImagesPresenter = new ShowImagesPresenterImpl(this, new ShowImagesProviderImpl());
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
        Toast.makeText(this, "" + imagesResponseData.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();

    }
}
