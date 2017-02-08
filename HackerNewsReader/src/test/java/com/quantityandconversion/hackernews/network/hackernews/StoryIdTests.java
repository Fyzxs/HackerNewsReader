package com.quantityandconversion.hackernews.network.hackernews;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryIdTests {

    @Test
    public void equals(){
        final StoryId same = new StoryId(1L);
        assertThat(same).isEqualTo(same);
        assertThat(new StoryId(1L)).isEqualTo(new StoryId(1L));
        assertThat(new StoryId(1L)).isNotEqualTo(new StoryId(2L));
    }


    @Test
    public void hash(){
        assertThat(new StoryId(1L).hashCode()).isEqualTo(new StoryId(1L).hashCode());
        assertThat(new StoryId(1L).hashCode()).isNotEqualTo(new StoryId(2L).hashCode());
    }
}
