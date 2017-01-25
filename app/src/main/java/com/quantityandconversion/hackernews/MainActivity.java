package com.quantityandconversion.hackernews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MainActivityBridge mainActivityBridge = new MainActivityBridge(this);

    private TextView topStoryCount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        bindViews();
    }

    private void bindViews() {
        topStoryCount = (TextView)findViewById(R.id.top_story_count);
    }

    /* package */ void setTopStoryCount(final String topStoriesCount){
        topStoryCount.setText(topStoriesCount);
    }
}
