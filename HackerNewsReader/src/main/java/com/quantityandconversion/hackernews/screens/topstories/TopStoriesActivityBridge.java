package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

    /* package */ void notifyTopStoriesChanged(){
        topStoriesActivity.notifyTopStoriesChanged();
    }

    /* package */ void notifyTopStoryChanged(final int index){
        topStoriesActivity.notifyTopStoryChanged(index);
    }

    /* package */  void configureTopStoriesListing(final RecyclerView topStories) {
        topStories.setAdapter(new TopStoriesAdapter(topStoriesActivityMediator));
        topStories.setLayoutManager(new LinearLayoutManager(topStoriesActivity));
        topStories.addItemDecoration(new DividerItemDecoration(topStories.getContext(), DividerItemDecoration.VERTICAL));
    }
}
