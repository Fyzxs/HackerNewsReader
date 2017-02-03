package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.ItemId;
import com.quantityandconversion.hackernews.network.item.Items;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class ItemsAdapter {

    @FromJson
    /* package */ Items itemsFromJson(final ItemId[] itemIds) {
        return new Items(itemIds);
    }

    @ToJson
    /* package */ ItemId[] itemsToJson(final Items items) {
        return null;
    }
}
