package com.quantityandconversion.hackernews.screens.topitems;

import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.app.QacActivity;

public class TopItemsActivity extends QacActivity {

    private final TopItemsActivityBridge topItemsActivityBridge;

    private TopItemsRecyclerView topItemsRecyclerView;

    public TopItemsActivity(){
        topItemsActivityBridge = new TopItemsActivityBridge(this);
    }
    public TopItemsActivity(final TopItemsActivityBridge topItemsActivityBridge,
                            final TopItemsRecyclerView topItemsRecyclerView) {
        this.topItemsActivityBridge = topItemsActivityBridge;
        this.topItemsRecyclerView = topItemsRecyclerView;
    }

    @Override
    protected void onCreateFunctionality() {
        setContentView(R.layout.top_stories_activity);

        bindViews();

        loadData();
    }

    /* package */ void loadData() {
        topItemsActivityBridge.loadData();
    }

    /* package */ void bindViews() {
        topItemsRecyclerView = new TopItemsRecyclerView(
                (RecyclerView)findViewById(R.id.rv_top_stories),
                topItemsActivityBridge.createTopStoriesAdapter());
    }

    /* package */ void notifyTopStoriesChanged(){
        topItemsRecyclerView.notifyTopStoriesChanged();
    }
    /* package */ void notifyTopStoryChanged(final int position){
        topItemsRecyclerView.notifyTopStoryChanged(position);
    }
}
