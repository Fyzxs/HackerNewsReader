package com.quantityandconversion.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.quantityandconversion.widget.interfaces.QacView;

public class QacLinearLayout extends LinearLayout implements QacView{

    public QacLinearLayout(Context context) {
        super(context);
    }

    public QacLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QacLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public View asView() {
        return this;
    }
}
