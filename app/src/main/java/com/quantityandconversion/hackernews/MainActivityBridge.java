package com.quantityandconversion.hackernews;

class MainActivityBridge {
//    private final MainActivity mainActivity;

    public MainActivityBridge(final MainActivity mainActivity) {
        if(mainActivity == null) throw new IllegalArgumentException("mainActivity cannot be null");

//        this.mainActivity = mainActivity;
    }

    /* package */ void loadData() {
    }


    /* package */ void loadedItemData() {

    }
}
