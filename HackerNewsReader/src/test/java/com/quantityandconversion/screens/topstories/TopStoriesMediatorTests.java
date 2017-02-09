package com.quantityandconversion.screens.topstories;

import com.quantityandconversion.hackernews.screens.topstories.TopStoriesActivity;
import com.quantityandconversion.hackernews.screens.topstories.TopStoriesActivityBridge;
import com.quantityandconversion.hackernews.screens.topstories.TopStoriesActivityMediator;

import org.junit.Test;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesMediatorTests {
    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopStoriesActivityMediator(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityBridge");

        new TopStoriesActivityMediator(new TopStoriesActivityBridge(new TopStoriesActivity()));
    }
    @Test
    public void topStoriesSize(){
        assertThat(new TopStoriesActivityMediator(
                new TopStoriesActivityBridge(new TopStoriesActivity()))
                .topStoriesSize()).isEqualTo(0);
    }

    @Test
    public void storyAt(){
        assertThat(new TopStoriesActivityMediator(
                new TopStoriesActivityBridge(new TopStoriesActivity()))
                .storyAt(-1)).isEqualTo(NullStory);
    }
}
