package com.quantityandconversion.hackernews.screens.topitems;

import android.content.Context;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.utils.dialog.AlertDialogBuilder;

/* package */ class TopItemsActivityBridge implements TopItemsActivityMediator.Bridge{

    /* package */ interface UiView {
        void notifyTopStoriesChanged();

        void notifyTopStoryChanged(int index);

        Context asContext();
    }


    private UiView uiView;
    private final TopItemsActivityMediator topItemsActivityMediator;


    /* package */ TopItemsActivityBridge(final UiView uiView) {
        if (uiView == null) {
            throw new IllegalArgumentException("topItemsActivity can not be null");
        }
        this.uiView = uiView;
        this.topItemsActivityMediator = new TopItemsActivityMediator(this);
    }

    @Override
    public Runnable dataError() {
        return () -> new AlertDialogBuilder<>()
                .init(uiView.asContext())
                .setTitle(R.string.top_stories_strings_alert_dialog_failure_title)
                .setMessage(R.string.top_stories_strings_alert_dialog_failure_message)
                .show();
    }


    @Override
    public Runnable dataChanged() {
        return uiView::notifyTopStoriesChanged;
    }

    @Override
    public void notifyTopStoryChanged(final int index){
        uiView.notifyTopStoryChanged(index);
    }

    /* package */ void loadData() {
        topItemsActivityMediator.loadTopStoriesData();
    }


    /* package */  TopItemsAdapter createTopStoriesAdapter() {
        return new TopItemsAdapter(topItemsActivityMediator);
    }
}
