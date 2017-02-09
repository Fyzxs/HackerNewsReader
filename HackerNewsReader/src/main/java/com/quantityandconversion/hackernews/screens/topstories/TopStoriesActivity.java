package com.quantityandconversion.hackernews.screens.topstories;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.quantityandconversion.hackernews.R;

public class TopStoriesActivity extends AppCompatActivity {

    private TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(this);

    private RecyclerView rvTopStories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_stories_activity);

        bindViews();

        topStoriesActivityBridge.loadData();
    }

    private void bindViews() {
        rvTopStories = (RecyclerView)findViewById(R.id.rv_top_stories);
    }

}
