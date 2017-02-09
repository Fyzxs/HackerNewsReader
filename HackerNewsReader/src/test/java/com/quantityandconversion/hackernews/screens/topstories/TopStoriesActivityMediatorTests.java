package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesActivityMediatorTests extends MockWebServerTestClass {
    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopStoriesActivityMediator(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityBridge");

        new TopStoriesActivityMediator(new TopStoriesActivityBridge(new TopStoriesActivity()));
    }

    @Test
    public void loadItemData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new TopStoriesActivityMediator(new FakeTopStoriesActivityBridge(latch, new TopStoriesActivity())).loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
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
