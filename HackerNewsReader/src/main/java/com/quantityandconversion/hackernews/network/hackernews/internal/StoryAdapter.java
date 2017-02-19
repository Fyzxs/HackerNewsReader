package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class StoryAdapter {
    @FromJson
    /* package */ Story storyFromJson(final StoryJson storyJson) {
        if(storyJson == null) { throw new IllegalArgumentException("storyJson can not be null"); }
        if(!"story".equals(storyJson.type)) { throw new IllegalArgumentException("data type is not a story"); }
        final StoryId storyId = new StoryId(storyJson.id);
        return new Story(storyId,
                new Title(storyJson.title),
                new Author(storyJson.by),
                new StoryComments(storyJson.descendants),
                new StoryScore(storyJson.score),
                new PostTime(storyJson.time));
    }

    @ToJson
    /* package */ long storyToJson(final Story story) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
