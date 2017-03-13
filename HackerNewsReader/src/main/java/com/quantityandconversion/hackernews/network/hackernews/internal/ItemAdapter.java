package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.NonNull;

import com.quantityandconversion.hackernews.network.hackernews.Item;
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
        return Item.createJob(ItemId.createJobId(itemJson.id),
                new Title(itemJson.title),
                new Author(itemJson.by),
                new PostTime(itemJson.time));
    }

    @NonNull
    private Item createStory(final ItemJson itemJson) {
        return Item.createStory(ItemId.createStoryId(itemJson.id),
                new Title(itemJson.title),
                new Author(itemJson.by),
                new ItemComments(itemJson.descendants),
                new ItemScore(itemJson.score),
                new PostTime(itemJson.time));
    }


    @ToJson
    /* package */ long itemToJson(final Item item) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
