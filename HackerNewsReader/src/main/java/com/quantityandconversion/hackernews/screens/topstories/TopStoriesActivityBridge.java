package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.dialog.AlertDialogBuilder;

/* package */ class TopStoriesActivityBridge {

    private final TopStoriesActivity topStoriesActivity;
    private final TopItemsActivityMediator topItemsActivityMediator;

    /* package */ TopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
        this.topStoriesActivity = topStoriesActivity;
        this.topItemsActivityMediator = new TopItemsActivityMediator(this);
    }

    /* package */ void loadData() {
        topItemsActivityMediator.loadTopStoriesData();
    }

    /* package */ void notifyTopStoryChanged(final int index){
        topStoriesActivity.notifyTopStoryChanged(index);
    }

    /* package */  TopStoriesAdapter createTopStoriesAdapter() {
        return new TopStoriesAdapter(topItemsActivityMediator);
    }

    /* package */ static abstract class DataLoadStrategy{
        /* package */ static final DataLoadStrategy DataChanged = new DataLoadStrategy(){
            @Override
            public void run(final TopStoriesActivityBridge bridge) {
                bridge.topStoriesActivity.notifyTopStoriesChanged();
            }
        };

        /* package */ static final DataLoadStrategy DataError = new DataLoadStrategy() {
            @Override
            public void run(final TopStoriesActivityBridge bridge) {

                new AlertDialogBuilder<>()
                        .init(bridge.topStoriesActivity)
                        .setTitle(R.string.top_stories_strings_alert_dialog_failure_title)
                        .setMessage(R.string.top_stories_strings_alert_dialog_failure_message)
                        .show();
            }
        };

        private DataLoadStrategy(){}
        public abstract void run(final TopStoriesActivityBridge bridge);
    }
}
