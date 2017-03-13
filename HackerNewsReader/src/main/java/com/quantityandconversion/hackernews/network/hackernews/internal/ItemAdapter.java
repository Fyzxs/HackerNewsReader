package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.NonNull;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemAdapter {
    @FromJson
    /* package */ Item itemFromJson(final ItemJson itemJson) {
        if(itemJson == null) { throw new IllegalArgumentException("itemJson can not be null"); }

        if("story".equals(itemJson.type)) {
            return createStory(itemJson);
        }
        if("job".equals(itemJson.type)) {
            return createJob(itemJson);
        }

        throw new IllegalArgumentException("data type is not a recognized type");
    }

    private Item createJob(final ItemJson itemJson) {

        final ItemId itemId = ItemId.createJobId(itemJson.id);
        return new Job(itemId,
                new Title(itemJson.title),
                new Author(itemJson.by),
                new PostTime(itemJson.time));
    }

    @NonNull
    private Story createStory(final ItemJson itemJson) {
        final ItemId itemId = ItemId.createStoryId(itemJson.id);
        return new Story(itemId,
                new Title(itemJson.title),
                new Author(itemJson.by),
                new StoryComments(itemJson.descendants),
                new StoryScore(itemJson.score),
                new PostTime(itemJson.time));
    }


    @ToJson
    /* package */ long itemToJson(final Item item) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
