package com.photo.picth.utils.ui;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import kotlin.jvm.internal.Intrinsics;

public final class HorizontalMarginItemDecoration extends RecyclerView.ItemDecoration {
    private final int horizontalMarginInPx;

    public HorizontalMarginItemDecoration(Context context, int i) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.horizontalMarginInPx = (int) context.getResources().getDimension(i);
    }

    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        Intrinsics.checkNotNullParameter(rect, "outRect");
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(recyclerView, "parent");
        Intrinsics.checkNotNullParameter(state, "state");
        rect.right = this.horizontalMarginInPx;
        rect.left = this.horizontalMarginInPx;
    }
}
