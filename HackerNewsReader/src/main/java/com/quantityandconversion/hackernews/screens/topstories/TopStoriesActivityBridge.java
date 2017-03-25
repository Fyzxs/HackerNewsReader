package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.dialog.AlertDialogBuilder;

/* package */ class Bridge {

    private final TopStoriesActivity topStoriesActivity;
    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ Bridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
        this.topStoriesActivity = topStoriesActivity;
        this.topItemsActivityMediator = new TopItemsActivityMediator(this);
    }


    /* package */ Runnable dataError() {
        return () -> new AlertDialogBuilder<>()
                .init(Bridge.this.topStoriesActivity)
                .setTitle(R.string.top_stories_strings_alert_dialog_failure_title)
                .setMessage(R.string.top_stories_strings_alert_dialog_failure_message)
                .show();
    }


    /* package */ Runnable dataChanged() {
        return Bridge.this.topStoriesActivity::notifyTopStoriesChanged;
    }

    /* package */ void loadData() {
        topItemsActivityMediator.loadTopStoriesData();
    }

    /* package */ void notifyTopStoryChanged(final int index){
        topStoriesActivity.notifyTopStoryChanged(index);
    }

    /* package */  TopItemsAdapter createTopStoriesAdapter() {
        return new TopItemsAdapter(topItemsActivityMediator);
    }
}
