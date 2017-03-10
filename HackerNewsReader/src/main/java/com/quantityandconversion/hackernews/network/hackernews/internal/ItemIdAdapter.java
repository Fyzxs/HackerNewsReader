package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemIdAdapter {
    @FromJson
    /* package */ ItemId itemIdFromJson(final long itemId) {
        return ItemId.createUnknownId(itemId);
    }

    @ToJson
    /* package */ long itemIdToJson(final ItemId itemId) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
