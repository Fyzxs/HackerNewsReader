package com.quantityandconversion.hackernews.screens.topstories;

public class TopStoriesActivityBridge {

    public TopStoriesActivityBridge(final TopStoriesActivity topStoriesActivity) {
        if (topStoriesActivity == null) {
            throw new IllegalArgumentException("topStoriesActivity can not be null");
        }
    }
}
