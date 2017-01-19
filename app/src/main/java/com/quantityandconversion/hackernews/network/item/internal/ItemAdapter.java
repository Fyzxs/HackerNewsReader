package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.ItemId;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemAdapter {
    @FromJson
    /* package */ ItemId itemFromJson(final long itemId) {
        return new ItemId(itemId);
    }

    @ToJson
    /* package */ long itemsToJson(final ItemId itemId) {
        return -1L;
    }
}
