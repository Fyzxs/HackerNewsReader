package com.quantityandconversion.hackernews;

import java.util.concurrent.CountDownLatch;

class FakeMainActivityBridge extends MainActivityBridge {
    private final CountDownLatch latch;

    /* package */ FakeMainActivityBridge(final CountDownLatch latch, final MainActivity mainActivity) {
        super(mainActivity);
        this.latch = latch;
    }

    @Override
    /* package */ void loadedItemData() {
        latch.countDown();
    }

}
