package com.quantityandconversion.hackernews.screens.topstories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivityBridge extends TopStoriesActivityBridge {

    private final TopStoriesAdapter topStoriesAdapter;
    private TopStoriesActivity topStoriesActivity;
    private CountDownLatch latch;

    /* package */ FakeTopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity, final TopStoriesAdapter topStoriesAdapter) {
        super(topStoriesActivity);
        this.topStoriesAdapter = topStoriesAdapter;
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
    /* package */ TopStoriesAdapter createTopStoriesAdapter() {
        countDownLatch();
        return topStoriesAdapter;
    }

    private void countDownLatch(){
        if(latch != null) latch.countDown();
    }
}
