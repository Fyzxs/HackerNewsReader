package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.StoryId;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class StoriesAdapterTests {
    @Test
    public void itemsFromJson() {
        assertNotNull(new StoriesAdapter().itemsFromJson(new StoryId[]{}));
    }

    @Test
    public void itemsToJson() {
        assertThatThrownBy(() -> new StoriesAdapter().itemsToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
