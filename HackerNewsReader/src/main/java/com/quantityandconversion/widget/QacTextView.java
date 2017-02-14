package com.quantityandconversion.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class QacTextView extends AppCompatTextView implements com.quantityandconversion.widget.interfaces.QacTextView {
    public QacTextView(Context context) {
        super(context);
    }

    public QacTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public QacTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
