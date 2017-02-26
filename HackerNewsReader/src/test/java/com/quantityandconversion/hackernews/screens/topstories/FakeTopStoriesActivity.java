package com.quantityandconversion.hackernews.screens.topstories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivity extends TopStoriesActivity{
    private final CountDownLatch latch;
    public FakeTopStoriesActivity() {
        this(new CountDownLatch(0));
    }
    public FakeTopStoriesActivity(final CountDownLatch latch) {
        super(null, null);
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
