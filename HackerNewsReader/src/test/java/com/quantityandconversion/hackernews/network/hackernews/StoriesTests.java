package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoriesTests {


    @Test
    public void size(){
        assertThatThrownBy(() -> new Stories(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("storyIds");
        assertThat(new Stories(new StoryId[0]).size()).isEqualTo(0);
        assertThat(new Stories(new StoryId[1]).size()).isEqualTo(0);
        assertThat(new Stories(new StoryId[]{new StoryId(1L)}).size()).isEqualTo(1);
    }

    @Test
    public void itemIdAt(){
        assertThatThrownBy(() -> new Stories(new StoryId[0]).itemIdAt(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThat(new Stories(new StoryId[]{new StoryId(1L)}).itemIdAt(0)).isEqualTo(new StoryId(1L));
    }
}
