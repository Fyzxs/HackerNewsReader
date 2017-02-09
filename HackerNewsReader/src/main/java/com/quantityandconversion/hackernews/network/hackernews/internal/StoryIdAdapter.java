package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class StoryIdAdapter {
    @FromJson
    /* package */ StoryId storyIdFromJson(final long itemId) {
        return new StoryId(itemId);
    }

    @ToJson
    /* package */ long storyIdToJson(final StoryId storyId) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
