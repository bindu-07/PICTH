package com.photo.picth.utils.ui;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatTextView;

import com.photo.picth.R;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
public final class VerticalTextView extends AppCompatTextView {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int ORIENTATION_DOWN_TO_UP = 1;
    public static final int ORIENTATION_LEFT_TO_RIGHT = 2;
    public static final int ORIENTATION_RIGHT_TO_LEFT = 3;
    public static final int ORIENTATION_UP_TO_DOWN = 0;
    private int direction;
    private Rect text_bounds = new Rect();

    public final Rect getText_bounds() {
        return this.text_bounds;
    }

    public final void setText_bounds(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<set-?>");
        this.text_bounds = rect;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerticalTextView(Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public VerticalTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, androidx.appcompat.R.styleable.AppCompatTextView);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttr…yleable.verticaltextview)");
        this.direction = obtainStyledAttributes.getInt(0, 0);
        obtainStyledAttributes.recycle();
        requestLayout();
        invalidate();
    }

    public final void setDirection(int i) {
        this.direction = i;
        requestLayout();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), this.text_bounds);
        int i3 = this.direction;
        if (i3 == 2 || i3 == 3) {
            setMeasuredDimension(measureHeight(i), measureWidth(i2));
        } else if (i3 == 0 || i3 == 1) {
            setMeasuredDimension(measureWidth(i), measureHeight(i2));
        }
    }

    private final int measureWidth(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int height = this.text_bounds.height() + getPaddingTop() + getPaddingBottom();
        return mode == Integer.MIN_VALUE ? Math.min(height, size) : height;
    }

    private final int measureHeight(int i) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        if (mode == 1073741824) {
            return size;
        }
        int width = this.text_bounds.width() + getPaddingLeft() + getPaddingRight();
        return mode == Integer.MIN_VALUE ? Math.min(width, size) : width;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Intrinsics.checkNotNullParameter(canvas, "canvas");
        canvas.save();
        Path path = new Path();
        int i = this.direction;
        if (i == 0) {
            path.moveTo((float) ((getWidth() - this.text_bounds.height()) >> 1), (float) ((getHeight() - this.text_bounds.width()) >> 1));
            path.lineTo((float) ((getWidth() - this.text_bounds.height()) >> 1), (float) ((getHeight() + this.text_bounds.width()) >> 1));
        } else if (i == 1) {
            path.moveTo((float) ((getWidth() + this.text_bounds.height()) >> 1), (float) ((getHeight() + this.text_bounds.width()) >> 1));
            path.lineTo((float) ((getWidth() + this.text_bounds.height()) >> 1), (float) ((getHeight() - this.text_bounds.width()) >> 1));
        } else if (i == 2) {
            path.moveTo((float) ((getWidth() - this.text_bounds.width()) >> 1), (float) ((getHeight() + this.text_bounds.height()) >> 1));
            path.lineTo((float) ((getWidth() + this.text_bounds.width()) >> 1), (float) ((getHeight() + this.text_bounds.height()) >> 1));
        } else if (i == 3) {
            path.moveTo((float) ((getWidth() + this.text_bounds.width()) >> 1), (float) ((getHeight() - this.text_bounds.height()) >> 1));
            path.lineTo((float) ((getWidth() - this.text_bounds.width()) >> 1), (float) ((getHeight() - this.text_bounds.height()) >> 1));
        }
        getPaint().setColor(getCurrentTextColor());
        canvas.drawTextOnPath(getText().toString(), path, 0.0f, 0.0f, getPaint());
        canvas.restore();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/app95/client/utils/ui/VerticalTextView$Companion;", "", "()V", "ORIENTATION_DOWN_TO_UP", "", "ORIENTATION_LEFT_TO_RIGHT", "ORIENTATION_RIGHT_TO_LEFT", "ORIENTATION_UP_TO_DOWN", "app_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* compiled from: VerticalTextView.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}