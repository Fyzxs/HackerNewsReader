package com.quantityandconversion.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class QacRecyclerView extends RecyclerView implements com.quantityandconversion.widget.interfaces.QacRecyclerView {

    public QacRecyclerView(Context context) {
        super(context);
    }

    public QacRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public QacRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public static abstract class QacAdapter<VH extends QacViewHolder> extends Adapter<VH> implements com.quantityandconversion.widget.interfaces.QacRecyclerView.QacAdapter<VH> {

    }

    public static abstract class QacViewHolder extends ViewHolder implements com.quantityandconversion.widget.interfaces.QacRecyclerView.QacViewHolder {

        public QacViewHolder(View itemView) {
            super(itemView);
        }
    }
}
