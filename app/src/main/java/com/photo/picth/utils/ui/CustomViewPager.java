package com.photo.picth.utils.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.core.app.NotificationCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.Iterator;

import kotlin.jvm.internal.Intrinsics;

public final class CustomViewPager extends ViewPager {
    private final ArrayList<MotionEvent> touchEventTrack = new ArrayList<>();
    private int touchSlop;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public CustomViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attrs");
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Intrinsics.checkNotNull(motionEvent);
        int action = motionEvent.getAction();
        if (action == 0) {
            this.touchEventTrack.clear();
            this.touchEventTrack.add(MotionEvent.obtain(motionEvent));
        } else if (action == 1) {
            this.touchEventTrack.add(MotionEvent.obtain(motionEvent));
            if (isClick(motionEvent)) {
                for (int i = 0; i < getChildCount(); i++) {
                    View childAt = getChildAt(i);
                    Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(i)");
                    if (isInView(motionEvent, childAt)) {
                        Iterator<MotionEvent> it = this.touchEventTrack.iterator();
                        boolean z = false;
                        while (it.hasNext()) {
                            MotionEvent next = it.next();
                            Intrinsics.checkNotNullExpressionValue(next, NotificationCompat.CATEGORY_EVENT);
                            z = dispatchTransformedTouchEvent(next, childAt);
                            if (!z) {
                                break;
                            }
                        }
                        if (z) {
                            MotionEvent obtain = MotionEvent.obtain(motionEvent);
                            obtain.setAction(3);
                            return onTouchEvent(obtain);
                        }
                    }
                }
            }
        } else if (action == 2) {
            this.touchEventTrack.add(MotionEvent.obtain(motionEvent));
        }
        return onTouchEvent(motionEvent);
    }

    private final boolean isInView(MotionEvent motionEvent, View view) {
        return new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()).contains((int) motionEvent.getX(), (int) motionEvent.getY());
    }

    private final boolean isClick(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.touchEventTrack.get(0);
        Intrinsics.checkNotNullExpressionValue(motionEvent2, "this.touchEventTrack.get(0)");
        MotionEvent motionEvent3 = motionEvent2;
        if (Math.abs(motionEvent3.getX() - motionEvent.getX()) >= ((float) this.touchSlop) || Math.abs(motionEvent3.getY() - motionEvent.getY()) >= ((float) this.touchSlop)) {
            return false;
        }
        return true;
    }

    private final boolean dispatchTransformedTouchEvent(MotionEvent motionEvent, View view) {
        float scrollX = ((float) getScrollX()) - ((float) view.getLeft());
        float scaleY = getScaleY() - ((float) view.getTop());
        motionEvent.offsetLocation(scrollX, scaleY);
        boolean dispatchTouchEvent = view.dispatchTouchEvent(motionEvent);
        if (!dispatchTouchEvent) {
            motionEvent.offsetLocation(-scrollX, -scaleY);
        }
        return dispatchTouchEvent;
    }
}