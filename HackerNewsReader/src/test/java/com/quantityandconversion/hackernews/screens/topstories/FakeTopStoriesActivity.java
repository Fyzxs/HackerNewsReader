package com.quantityandconversion.hackernews.screens.topstories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivity extends TopStoriesActivity{
    private final CountDownLatch latch;

    public FakeTopStoriesActivity(final CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    /* package */ void notifyTopStoriesChanged() {
        latch.countDown();
    }

    @Override
    /* package */ void notifyTopStoryChanged(final int index){
        latch.countDown();
    }
}
