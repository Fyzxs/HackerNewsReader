package com.quantityandconversion.hackernews.screens.topstories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivityBridge extends TopStoriesActivityBridge {

    private final TopItemsAdapter topItemsAdapter;
    private TopStoriesActivity topStoriesActivity;
    private CountDownLatch latch;

    /* package */ FakeTopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity, final TopItemsAdapter topItemsAdapter) {
        super(topStoriesActivity);
        this.topItemsAdapter = topItemsAdapter;
    }

    /* package */ void setActualTopStoriesActivity(final TopStoriesActivity topStoriesActivity){
        this.topStoriesActivity = topStoriesActivity;
    }

    /* package */ void setCountDownLatch(final CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    /* package */ void loadData() {
        countDownLatch();
        if(topStoriesActivity != null) topStoriesActivity.notifyTopStoriesChanged();
    }

    @Override
    /* package */ TopItemsAdapter createTopStoriesAdapter() {
        countDownLatch();
        return topItemsAdapter;
    }

    private void countDownLatch(){
        if(latch != null) latch.countDown();
    }
}
