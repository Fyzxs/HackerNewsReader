package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.Story;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;

public class TopStoriesActivityMediator {
    public TopStoriesActivityMediator(final TopStoriesActivityBridge topStoriesActivityBridge) {
        if(topStoriesActivityBridge == null) { throw new IllegalArgumentException("topStoriesActivityBridge can not be null"); }
    }

    public int topStoriesSize() {
        return 0;
    }

    public Story storyAt(final int index) {
        return NullStory;
    }
}
