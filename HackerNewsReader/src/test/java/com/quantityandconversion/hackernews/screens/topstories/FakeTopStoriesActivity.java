package com.quantityandconversion.hackernews.screens.topstories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivity extends TopStoriesActivity{
    private final CountDownLatch latch;

    public FakeTopStoriesActivity(final CountDownLatch latch) {
        this.latch = latch;
    }

    //@Override
    /* package */ void loadStories(final TopStoriesAdapter adapter) {
        latch.countDown();
    }

}
