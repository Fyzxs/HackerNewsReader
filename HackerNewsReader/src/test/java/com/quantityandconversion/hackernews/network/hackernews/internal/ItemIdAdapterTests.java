package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class ItemIdAdapterTests {
    @Test
    public void itemIdFromJson() {
        assertNotNull(new ItemIdAdapter().itemIdFromJson(1L).equals(ItemId.createUnknownId(1)));
    }

    @Test
    public void itemIdToJson() {
        assertThatThrownBy(() -> new ItemIdAdapter().itemIdToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
