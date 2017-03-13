package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Items;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemsAdapter {

    @FromJson
    /* package */ Items itemsFromJson(final ItemId[] itemIds) {
        return new Items(itemIds);
    }

    @ToJson
    /* package */ ItemId[] itemsToJson(final Items items) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
