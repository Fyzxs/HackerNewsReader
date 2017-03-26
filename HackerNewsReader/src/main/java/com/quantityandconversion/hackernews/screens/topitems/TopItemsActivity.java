package com.quantityandconversion.hackernews.screens.topitems;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.hackernews.app.QacActivity;

public class TopItemsActivity extends QacActivity implements TopItemsActivityBridge.UiView{

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

    @Override
    public void notifyTopStoriesChanged(){
        topItemsRecyclerView.notifyTopStoriesChanged();
    }
    
    @Override
    public  void notifyTopStoryChanged(final int position){
        topItemsRecyclerView.notifyTopStoryChanged(position);
    }

    @Override
    public Context asContext() {
        return this;
    }
}
