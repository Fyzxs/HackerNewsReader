package com.quantityandconversion.hackernews.screens.topstories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.quantityandconversion.hackernews.R;
import com.quantityandconversion.widget.QacRecyclerView;

public class TopStoriesActivity extends AppCompatActivity {

    private TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(this);

    private QacRecyclerView rvTopStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_stories_activity);

        bindViews();
        configureViews();
        loadData();
    }

    private void loadData() {
        topStoriesActivityBridge.loadData();
    }

    private void configureViews() {
        configureTopStories();
    }

    private void configureTopStories() {
        rvTopStories.setAdapter(topStoriesActivityBridge.createTopStoriesAdapter());
        rvTopStories.setLayoutManager(new LinearLayoutManager(this));
        rvTopStories.addItemDecoration(new DividerItemDecoration(rvTopStories.getContext(), DividerItemDecoration.VERTICAL));
    }

    private void bindViews() {
        rvTopStories = (QacRecyclerView)findViewById(R.id.rv_top_stories);
    }

    /* package */ void notifyTopStoriesChanged(){
        rvTopStories.getAdapter().notifyDataSetChanged();
    }
    /* package */ void notifyTopStoryChanged(final int index){
        rvTopStories.getAdapter().notifyItemChanged(index);
    }
}
