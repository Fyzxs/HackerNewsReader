package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.hackernews.network.hackernews.Stories;

import java.util.concurrent.CountDownLatch;

class FakeMainActivityBridge extends MainActivityBridge {
    private final CountDownLatch latch;

    /* package */ FakeMainActivityBridge(final CountDownLatch latch, final MainActivity mainActivity) {
        super(mainActivity);
        this.latch = latch;
    }

    @Override
    /* package */ void loadedItemData(Stories body) {
        latch.countDown();
    }

}
