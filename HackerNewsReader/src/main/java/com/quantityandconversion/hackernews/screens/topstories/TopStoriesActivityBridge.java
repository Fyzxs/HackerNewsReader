package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.utils.log.FyzLog;

/* package */ class TopStoriesActivityBridge {

    private final TopStoriesActivity topStoriesActivity;
    private final TopStoriesActivityMediator topStoriesActivityMediator;

    /* package */ TopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
        this.topStoriesActivity = topStoriesActivity;
        this.topStoriesActivityMediator = new TopStoriesActivityMediator(this);
    }

    /* package */ void loadData() {
        final RecyclerView topStoriesListing = topStoriesActivity.topStoriesListing();
        topStoriesListing.setAdapter(new TopStoriesAdapter(topStoriesActivityMediator));
        topStoriesListing.setLayoutManager(new LinearLayoutManager(topStoriesActivity));
        topStoriesActivityMediator.loadTopStoriesData();
    }

    /* package */ void loadedTopStoriesData(final Stories stories){
//        final TopStoriesAdapter adapter = new TopStoriesAdapter(topStoriesActivityMediator);
//        final RecyclerView topStoriesListing = topStoriesActivity.topStoriesListing();
//        topStoriesListing.swapAdapter(adapter, true);
//        topStoriesListing.invalidate();
//        adapter.notifyDataSetChanged();
        FyzLog.v("Change Notification Attempted");

    }
}
