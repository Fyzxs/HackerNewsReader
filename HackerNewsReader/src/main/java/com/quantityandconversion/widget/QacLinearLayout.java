package com.quantityandconversion.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import com.quantityandconversion.widget.interfaces.QacView;

public class QacLinearLayout extends LinearLayout implements QacView{
    public QacLinearLayout(Context context) {
        super(context);
    }

    @Override
    public View asView() {
        return this;
    }
}
