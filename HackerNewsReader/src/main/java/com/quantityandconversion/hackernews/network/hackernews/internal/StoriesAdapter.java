package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.StoryId;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class StoriesAdapter {

    @FromJson
    /* package */ Stories storiesFromJson(final StoryId[] storyIds) {
        return new Stories(storyIds);
    }

    @ToJson
    /* package */ StoryId[] storiesToJson(final Stories stories) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}