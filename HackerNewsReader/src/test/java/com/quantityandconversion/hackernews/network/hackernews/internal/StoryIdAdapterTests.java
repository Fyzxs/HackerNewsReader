package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class StoryIdAdapterTests {
    @Test
    public void storyIdFromJson() {
        assertNotNull(new StoryIdAdapter().storyIdFromJson(1L).equals(ItemId.createStoryId(1)));
    }

    @Test
    public void storyIdToJson() {
        assertThatThrownBy(() -> new StoryIdAdapter().storyIdToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
