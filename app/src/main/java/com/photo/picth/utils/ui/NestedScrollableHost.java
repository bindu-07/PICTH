package com.photo.picth.utils.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.viewpager2.widget.ViewPager2;

import kotlin.jvm.internal.Intrinsics;

public final class NestedScrollableHost extends FrameLayout {
    private float initialX;
    private float initialY;
    private int touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NestedScrollableHost(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NestedScrollableHost(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x001e A[EDGE_INSN: B:13:0x001e->B:10:0x001e ?: BREAK
    EDGE_INSN: B:15:0x001e->B:10:0x001e ?: BREAK  ] */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0013  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final androidx.viewpager2.widget.ViewPager2 getParentViewPager() {
        /*
            r3 = this;
            android.view.ViewParent r0 = r3.getParent()
            boolean r1 = r0 instanceof android.view.View
            r2 = 0
            if (r1 == 0) goto L_0x000c
            android.view.View r0 = (android.view.View) r0
            goto L_0x000d
        L_0x000c:
            r0 = r2
        L_0x000d:
            if (r0 == 0) goto L_0x001e
            boolean r1 = r0 instanceof androidx.viewpager2.widget.ViewPager2
            if (r1 != 0) goto L_0x001e
            android.view.ViewParent r0 = r0.getParent()
            boolean r1 = r0 instanceof android.view.View
            if (r1 == 0) goto L_0x000c
            android.view.View r0 = (android.view.View) r0
            goto L_0x000d
        L_0x001e:
            boolean r1 = r0 instanceof androidx.viewpager2.widget.ViewPager2
            if (r1 == 0) goto L_0x0025
            r2 = r0
            androidx.viewpager2.widget.ViewPager2 r2 = (androidx.viewpager2.widget.ViewPager2) r2
        L_0x0025:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.app95.client.utils.ui.NestedScrollableHost.getParentViewPager():androidx.viewpager2.widget.ViewPager2");
    }

    private final View getChild() {
        if (getChildCount() > 0) {
            return getChildAt(0);
        }
        return null;
    }

    private final boolean canChildScroll(int i, float f) {
        int i2 = -((int) Math.signum(f));
        if (i == 0) {
            View child = getChild();
            if (child != null) {
                return child.canScrollHorizontally(i2);
            }
            return false;
        } else if (i == 1) {
            View child2 = getChild();
            if (child2 != null) {
                return child2.canScrollVertically(i2);
            }
            return false;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "e");
        handleInterceptTouchEvent(motionEvent);
        return super.onInterceptTouchEvent(motionEvent);
    }

    private final void handleInterceptTouchEvent(MotionEvent motionEvent) {
        ViewPager2 parentViewPager = getParentViewPager();
        if (parentViewPager != null) {
            int orientation = parentViewPager.getOrientation();
            float f = 1.0f;
            if (!canChildScroll(orientation, -1.0f) && !canChildScroll(orientation, 1.0f)) {
                return;
            }
            if (motionEvent.getAction() == 0) {
                this.initialX = motionEvent.getX();
                this.initialY = motionEvent.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
            } else if (motionEvent.getAction() == 2) {
                float x = motionEvent.getX() - this.initialX;
                float y = motionEvent.getY() - this.initialY;
                boolean z = orientation == 0;
                float abs = Math.abs(x) * (z ? 0.5f : 1.0f);
                float abs2 = Math.abs(y);
                if (!z) {
                    f = 0.5f;
                }
                float f2 = abs2 * f;
                int i = this.touchSlop;
                if (abs > ((float) i) || f2 > ((float) i)) {
                    if (z == (f2 > abs)) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                        return;
                    }
                    if (!z) {
                        x = y;
                    }
                    if (canChildScroll(orientation, x)) {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
            }
        }
    }
}