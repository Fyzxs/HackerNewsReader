package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/* package */ class TopStoriesRecyclerView{
    private final RecyclerView recyclerView;
    private final RecyclerView.Adapter adapter;

    /* package */ TopStoriesRecyclerView(final RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        if(recyclerView == null) { throw new IllegalArgumentException("recyclerView can not be null"); }
        if(adapter == null) { throw new IllegalArgumentException("adapter can not be null"); }

        this.recyclerView = recyclerView;
        this.adapter = adapter;

        configureRecyclerView();
    }

    private void configureRecyclerView() {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
    }

    /* package */ void notifyTopStoriesChanged() {
        adapter.notifyDataSetChanged();
    }

    /* package */ void notifyTopStoryChanged(final int position) {
        adapter.notifyItemChanged(position);
    }
}
