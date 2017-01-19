package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.ItemId;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class ItemsAdapterTests {
    @Test
    public void itemsFromJson() {
        assertNotNull(new ItemsAdapter().itemsFromJson(new ItemId[]{}));
    }

    @Test
    public void itemsToJson() {
        assertNull(new ItemsAdapter().itemsToJson(null));
    }
}
