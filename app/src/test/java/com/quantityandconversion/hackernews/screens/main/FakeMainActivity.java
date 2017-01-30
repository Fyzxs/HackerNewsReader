package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.hackernews.screens.main.MainActivity;

import java.util.concurrent.CountDownLatch;

public class FakeMainActivity extends MainActivity {
    private final CountDownLatch latch;

    public FakeMainActivity(final CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    /* package */ void setTopStoryCount(final String topStoriesCount) {
        latch.countDown();
    }
}