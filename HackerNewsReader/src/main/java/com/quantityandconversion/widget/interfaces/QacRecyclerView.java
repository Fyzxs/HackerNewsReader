package com.quantityandconversion.widget.interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

public interface QacRecyclerView {
    void setAdapter(RecyclerView.Adapter adapter);
    void setLayoutManager(RecyclerView.LayoutManager layout);
    void addItemDecoration(RecyclerView.ItemDecoration decor);
    Context getContext();

    interface QacAdapter<VH extends QacViewHolder>{

    }

    interface QacViewHolder{

    }
}
