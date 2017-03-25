package com.quantityandconversion.hackernews.screens.topitems;

import java.util.concurrent.CountDownLatch;

class FakeTopItemsActivity extends TopItemsActivity {
    private final CountDownLatch latch;
    public FakeTopItemsActivity() {
        this(new CountDownLatch(0));
    }
    public FakeTopItemsActivity(final CountDownLatch latch) {
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
