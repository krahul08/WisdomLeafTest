package com.example.wisdomleaftest.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public abstract class PaginationListener extends RecyclerView.OnScrollListener {

    public static final int PAGE_START = 1;
    @NonNull
    private StaggeredGridLayoutManager layoutManager;

    public PaginationListener(@NonNull StaggeredGridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if (!isLoading() && !isLastPage()) {

            visibleItemCount = layoutManager.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            int[] firstVisibleItems = null;
            firstVisibleItems = layoutManager.findFirstVisibleItemPositions(firstVisibleItems);
            if (firstVisibleItems != null && firstVisibleItems.length > 0) {
                visibleItemCount = firstVisibleItems[0];
            }
            if ((visibleItemCount + visibleItemCount) >= totalItemCount) {
                isLoading();
                loadMoreItems();
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();


}
