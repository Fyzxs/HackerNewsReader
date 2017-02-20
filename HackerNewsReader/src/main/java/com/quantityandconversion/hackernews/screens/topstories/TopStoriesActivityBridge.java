package com.quantityandconversion.hackernews.screens.topstories;

import android.app.AlertDialog;

/* package */ class TopStoriesActivityBridge {

    private final TopStoriesActivity topStoriesActivity;
    private final TopStoriesActivityMediator topStoriesActivityMediator;

    /* package */ TopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
        this.topStoriesActivity = topStoriesActivity;
        this.topStoriesActivityMediator = new TopStoriesActivityMediator(this);
    }

    /* package */ void loadData() {
        topStoriesActivityMediator.loadTopStoriesData();
    }

    /* package */ void notifyTopStoriesChanged(final DataLoadStrategy dataLoadStrategy){
        dataLoadStrategy.run(topStoriesActivity);
    }

    /* package */ void notifyTopStoryChanged(final int index){
        topStoriesActivity.notifyTopStoryChanged(index);
    }

    /* package */  TopStoriesAdapter createTopStoriesAdapter() {
        return new TopStoriesAdapter(topStoriesActivityMediator);
    }

    /* package */ static abstract class DataLoadStrategy{
        public final static DataLoadStrategy DataChanged = new DataLoadStrategy(){
            @Override
            public void run(final TopStoriesActivity activity) {
                activity.notifyTopStoriesChanged();
            }
        };

        public final static DataLoadStrategy DataError = new DataLoadStrategy() {
            @Override
            public void run(TopStoriesActivity activity) {
                new AlertDialog.Builder(activity)
                        .setTitle("OMG, DIALOG")
                        .setMessage("Exciting").show();
            }
        };

        private DataLoadStrategy(){}
        public abstract void run(final TopStoriesActivity activity);
    }

    /*
        Required Strategies on data update
            Refresh data
            Display alert dialog on network issue

        Required Strategies on story update
            Update Story
            Display Toast


     */
}
