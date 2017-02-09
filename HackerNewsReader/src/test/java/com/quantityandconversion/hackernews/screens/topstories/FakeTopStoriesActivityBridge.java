package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.Stories;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivityBridge extends TopStoriesActivityBridge {
    private final CountDownLatch latch;

    /* package */ FakeTopStoriesActivityBridge(final CountDownLatch latch,
                                               final TopStoriesActivity topStoriesActivity) {
        super(topStoriesActivity);
        this.latch = latch;
    }

    @Override
    /* package */ void loadedTopStoriesData(final Stories stories) {
        latch.countDown();
    }

}
