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
        assertThat(new Stories(new StoryId[]{}).size()).isEqualTo(0);
        assertThat(new Stories(new StoryId[]{new StoryId(12345L)}).size()).isEqualTo(1);
    }

    @Test
    public void storyAt() throws InterruptedException {
        assertThatThrownBy(() -> new Stories(new StoryId[0]).storyAt(0, null))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThat(new Stories(new StoryId[]{new StoryId(12345L)}).storyAt(0, null)).isEqualTo(NullStory);

        storyAtRefresh();
    }

    private void storyAtRefresh() throws InterruptedException {
        this.hackerNewsNetworkTestResponses.simpleStory(mockWebServer);
        final CountDownLatch latch = new CountDownLatch(1);
        final Stories stories = new Stories(new StoryId[]{new StoryId(12345L)});
        assertThat(stories.storyAt(0, new Stories.StoryRefreshCallback(){

            @Override
            public void storyRefreshed(int index) {
                assertThat(index).isEqualTo(0);
                latch.countDown();
            }
        })).isEqualTo(NullStory);

        assertThat(latch.await(5, TimeUnit.HOURS)).isTrue();

        assertThat(stories.storyAt(0, null)).isEqualTo(HackerNewsNetworkTestResponses.SimpleStory);
    }
}
