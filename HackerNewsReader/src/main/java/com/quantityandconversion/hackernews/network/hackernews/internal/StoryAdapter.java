package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.util.zip.DataFormatException;

/* package */ class StoryAdapter {
    @FromJson
    /* package */ Story storyFromJson(final StoryJson storyJson) {
        if(storyJson == null) { throw new IllegalArgumentException("storyJson can not be null"); }
        //if(!"story".equals(storyJson.type)) { throw new IllegalArgumentException("data type is not a story"); }
        return new Story(new StoryId(storyJson.id), new Title(storyJson.title));
    }
    @ToJson
    /* package */ long storyToJson(final Story story) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
