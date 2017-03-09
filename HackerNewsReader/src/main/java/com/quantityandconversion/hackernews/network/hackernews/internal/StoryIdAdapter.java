package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class StoryIdAdapter {
    @FromJson
    /* package */ ItemId storyIdFromJson(final long itemId) {
        return ItemId.createStoryId(itemId);
    }

    @ToJson
    /* package */ long storyIdToJson(final ItemId itemId) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
