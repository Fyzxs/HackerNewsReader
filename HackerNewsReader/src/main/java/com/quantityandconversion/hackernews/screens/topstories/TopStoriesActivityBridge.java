package com.quantityandconversion.hackernews.screens.topstories;

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

    /* package */  TopStoriesAdapter createTopStoriesAdapter() {
        return new TopStoriesAdapter(topStoriesActivityMediator);
    }
}
