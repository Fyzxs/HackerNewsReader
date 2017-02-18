package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertNotNull;

public class CommentIdAdapterTests {
    @Test
    public void storyIdFromJson() {
        assertNotNull(new CommentIdAdapter().commentIdFromJson(1L).equals(new CommentId(1)));
    }

    @Test
    public void storyIdToJson() {
        assertThatThrownBy(() -> new CommentIdAdapter().commentIdToJson(null))
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("serializing to json not supported");
    }
}
