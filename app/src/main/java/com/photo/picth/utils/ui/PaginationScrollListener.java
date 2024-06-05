package com.photo.picth.utils.ui;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import kotlin.jvm.internal.Intrinsics;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {
    private final GridLayoutManager layoutManager;

    public abstract boolean isLastPage();

    public abstract boolean isLoading();

    /* access modifiers changed from: protected */
    public abstract void loadMoreItems();

    public PaginationScrollListener(GridLayoutManager gridLayoutManager) {
        Intrinsics.checkNotNullParameter(gridLayoutManager, "layoutManager");
        this.layoutManager = gridLayoutManager;
    }

    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        super.onScrolled(recyclerView, i, i2);
        if (i2 > 0) {
            this.layoutManager.getChildCount();
            int itemCount = this.layoutManager.getItemCount();
            this.layoutManager.findFirstVisibleItemPosition();
            this.layoutManager.findLastVisibleItemPosition();
            int findLastCompletelyVisibleItemPosition = this.layoutManager.findLastCompletelyVisibleItemPosition() + 1;
            if (!isLoading() && !isLastPage() && findLastCompletelyVisibleItemPosition == itemCount) {
                loadMoreItems();
            }
        }
    }
}
