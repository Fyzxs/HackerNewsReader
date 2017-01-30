package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.hackernews.screens.main.MainActivity;
import com.quantityandconversion.hackernews.MainActivityBridge;
import com.quantityandconversion.hackernews.network.item.Items;

import java.util.concurrent.CountDownLatch;

class FakeMainActivityBridge extends MainActivityBridge {
    private final CountDownLatch latch;

    /* package */ FakeMainActivityBridge(final CountDownLatch latch, final MainActivity mainActivity) {
        super(mainActivity);
        this.latch = latch;
    }

    @Override
    /* package */ void loadedItemData(Items body) {
        latch.countDown();
    }

}
