package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetworkTestResponses;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoriesTests extends MockWebServerTestClass {

    private final static Stories zeroStory = new Stories(new StoryId[]{});
    private final static Stories singleStory = new Stories(new StoryId[]{new StoryId(1234L)});

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new Stories(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("storyIds cannot be null");

        assertThatThrownBy(() -> new Stories(new StoryId[1]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("storyId cannot be null");
    }

    @Test
    public void size(){
        assertThat(zeroStory.size()).isEqualTo(0);
        assertThat(singleStory.size()).isEqualTo(1);
    }

    @Test
    public void storyAt() throws InterruptedException {
        assertThatThrownBy(() -> new Stories(new StoryId[0]).storyAt(0, null))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThat(singleStory.storyAt(0, null)).isEqualTo(NullStory);

        storyAtRefresh();
    }

    private void storyAtRefresh() throws InterruptedException {
        this.hackerNewsNetworkTestResponses.simpleStory(mockWebServer);
        final CountDownLatch latch = new CountDownLatch(1);
        assertThat(singleStory.storyAt(0, new Stories.StoryRefreshCallback(){

            @Override
            public void storyRefreshed(int index) {
                assertThat(index).isEqualTo(0);
                latch.countDown();
            }
        })).isEqualTo(NullStory);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(singleStory.storyAt(0, null)).isEqualTo(HackerNewsNetworkTestResponses.SimpleStory);
    }
}
