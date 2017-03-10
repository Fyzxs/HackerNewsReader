package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemsAdapter {

    @FromJson
    /* package */ Stories itemsFromJson(final ItemId[] itemIds) {
        return new Stories(itemIds);
    }

    @ToJson
    /* package */ ItemId[] itemsToJson(final Stories stories) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
