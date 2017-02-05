package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.ItemId;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class ItemAdapterTests {
    @Test
    public void itemsFromJson() {
        assertNotNull(new ItemIdAdapter().itemIdFromJson(1L).equals(new ItemId(1)));
    }

    @Test
    public void itemsToJson() {
        assertThatThrownBy(() -> new ItemIdAdapter().itemIdToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
