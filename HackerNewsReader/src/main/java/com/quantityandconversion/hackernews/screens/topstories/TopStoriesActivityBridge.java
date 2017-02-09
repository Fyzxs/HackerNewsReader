package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.Stories;

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
        topStoriesActivityMediator.loadTopStoriesData();
    }

    /* package */ void loadedTopStoriesData(final Stories stories){
        topStoriesActivity.loadStories(new TopStoriesAdapter(topStoriesActivityMediator));
    }
}
