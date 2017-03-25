package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.dialog.AlertDialogBuilder;

/* package */ class TopStoriesActivityBridge implements TopItemsActivityMediator.Bridge{

    private final TopStoriesActivity topStoriesActivity;
    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ TopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
        this.topStoriesActivity = topStoriesActivity;
        this.topItemsActivityMediator = new TopItemsActivityMediator(this);
    }


    @Override
    public Runnable dataError() {
        return () -> new AlertDialogBuilder<>()
                .init(TopStoriesActivityBridge.this.topStoriesActivity)
                .setTitle(R.string.top_stories_strings_alert_dialog_failure_title)
                .setMessage(R.string.top_stories_strings_alert_dialog_failure_message)
                .show();
    }


    @Override
    public Runnable dataChanged() {
        return TopStoriesActivityBridge.this.topStoriesActivity::notifyTopStoriesChanged;
    }

    @Override
    public void notifyTopStoryChanged(final int index){
        topStoriesActivity.notifyTopStoryChanged(index);
    }

    /* package */ void loadData() {
        topItemsActivityMediator.loadTopStoriesData();
    }


    /* package */  TopItemsAdapter createTopStoriesAdapter() {
        return new TopItemsAdapter(topItemsActivityMediator);
    }
}
