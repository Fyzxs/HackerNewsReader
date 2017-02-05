package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.ItemId;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemIdAdapter {
    @FromJson
    /* package */ ItemId itemIdFromJson(final long itemId) {
        return new ItemId(itemId);
    }

    @ToJson
    /* package */ long itemIdToJson(final ItemId itemId) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
