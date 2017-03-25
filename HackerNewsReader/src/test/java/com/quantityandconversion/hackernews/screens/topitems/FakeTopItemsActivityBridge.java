package com.quantityandconversion.hackernews.screens.topitems;

import java.util.concurrent.CountDownLatch;

class FakeTopItemsActivityBridge extends TopItemsActivityBridge {

    private final TopItemsAdapter topItemsAdapter;
    private TopItemsActivity topItemsActivity;
    private CountDownLatch latch;

    /* package */ FakeTopItemsActivityBridge(final TopItemsActivity topItemsActivity, final TopItemsAdapter topItemsAdapter) {
        super(topItemsActivity);
        this.topItemsAdapter = topItemsAdapter;
    }

    /* package */ void setActualTopStoriesActivity(final TopItemsActivity topItemsActivity){
        this.topItemsActivity = topItemsActivity;
    }

    /* package */ void setCountDownLatch(final CountDownLatch latch){
        this.latch = latch;
    }

    @Override
    /* package */ void loadData() {
        countDownLatch();
        if(topItemsActivity != null) topItemsActivity.notifyTopStoriesChanged();
    }

    @Override
    /* package */ TopItemsAdapter createTopStoriesAdapter() {
        countDownLatch();
        return topItemsAdapter;
    }

    private void countDownLatch(){
        if(latch != null) latch.countDown();
    }
}
