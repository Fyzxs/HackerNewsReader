package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class StoryAdapter {
    @FromJson
    /* package */ Story storyFromJson(final StoryJson storyJson) {
        if(storyJson == null) { throw new IllegalArgumentException("storyJson can not be null"); }

        return new Story(storyJson.title);
    }
    @ToJson
    /* package */ long storyToJson(final Story story) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
