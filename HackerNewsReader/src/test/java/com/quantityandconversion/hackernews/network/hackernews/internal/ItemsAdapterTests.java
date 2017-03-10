package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class ItemsAdapterTests {
    @Test
    public void itemsFromJson() {
        assertNotNull(new ItemsAdapter().itemsFromJson(new ItemId[]{}));
    }

    @Test
    public void itemsToJson() {
        assertThatThrownBy(() -> new ItemsAdapter().itemsToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
