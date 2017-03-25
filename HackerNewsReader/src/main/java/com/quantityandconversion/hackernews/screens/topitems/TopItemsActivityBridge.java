package com.quantityandconversion.hackernews.screens.topitems;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.dialog.AlertDialogBuilder;

/* package */ class TopItemsActivityBridge implements TopItemsActivityMediator.Bridge{

    private final TopItemsActivity topItemsActivity;
    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ TopItemsActivityBridge(final TopItemsActivity topItemsActivity) {
        if (topItemsActivity == null) {
            throw new IllegalArgumentException("topItemsActivity can not be null");
        }
        this.topItemsActivity = topItemsActivity;
        this.topItemsActivityMediator = new TopItemsActivityMediator(this);
    }


    @Override
    public Runnable dataError() {
        return () -> new AlertDialogBuilder<>()
                .init(TopItemsActivityBridge.this.topItemsActivity)
                .setTitle(R.string.top_stories_strings_alert_dialog_failure_title)
                .setMessage(R.string.top_stories_strings_alert_dialog_failure_message)
                .show();
    }


    @Override
    public Runnable dataChanged() {
        return TopItemsActivityBridge.this.topItemsActivity::notifyTopStoriesChanged;
    }

    @Override
    public void notifyTopStoryChanged(final int index){
        topItemsActivity.notifyTopStoryChanged(index);
    }

    /* package */ void loadData() {
        topItemsActivityMediator.loadTopStoriesData();
    }


    /* package */  TopItemsAdapter createTopStoriesAdapter() {
        return new TopItemsAdapter(topItemsActivityMediator);
    }
}
