package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.hackernews.network.hackernews.Items;

class MainActivityBridge {
    private final MainActivity mainActivity;

    public MainActivityBridge(final MainActivity mainActivity) {
        if(mainActivity == null) throw new IllegalArgumentException("mainActivity cannot be null");

        this.mainActivity = mainActivity;
    }

    /* package */ void loadData() {
        new MainActivityMediator(this).loadItemData();
    }


    /* package */ void loadedItemData(final Items body) {
        mainActivity.setTopStoryCount(Integer.toString(body.size()));
    }
}
