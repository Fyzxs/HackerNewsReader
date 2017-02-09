package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.RecyclerView;

import java.util.concurrent.CountDownLatch;

class FakeTopStoriesActivity extends TopStoriesActivity{
    private final CountDownLatch latch;

    public FakeTopStoriesActivity(final CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    /* package */ void loadStories(final RecyclerView.Adapter adapter) {
        latch.countDown();
    }

}
