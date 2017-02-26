package com.quantityandconversion.hackernews.screens.topstories;

import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.app.QacActivity;

public class TopStoriesActivity extends QacActivity {

    private TopStoriesActivityBridge topStoriesActivityBridge;

    private TopStoriesRecyclerView topStoriesRecyclerView;

    public TopStoriesActivity(){
        topStoriesActivityBridge = new TopStoriesActivityBridge(this);
    }
    public TopStoriesActivity(final TopStoriesActivityBridge topStoriesActivityBridge,
                              final TopStoriesRecyclerView topStoriesRecyclerView) {
        this.topStoriesActivityBridge = topStoriesActivityBridge;
        this.topStoriesRecyclerView = topStoriesRecyclerView;
    }

    @Override
    protected void onCreateFunctionality() {
        setContentView(R.layout.top_stories_activity);

        bindViews();

        loadData();
    }

    /* package */ void loadData() {
        topStoriesActivityBridge.loadData();
    }

    /* package */ void bindViews() {
        topStoriesRecyclerView = new TopStoriesRecyclerView(
                (RecyclerView)findViewById(R.id.rv_top_stories),
                topStoriesActivityBridge.createTopStoriesAdapter());
    }

    /* package */ void notifyTopStoriesChanged(){
        topStoriesRecyclerView.notifyTopStoriesChanged();
    }
    /* package */ void notifyTopStoryChanged(final int position){
        topStoriesRecyclerView.notifyTopStoryChanged(position);
    }
}
