package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetworkTestResponses;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesActivityMediatorTests extends MockWebServerTestClass {
    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopItemsActivityMediator(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityBridge");

        new TopItemsActivityMediator(new TopStoriesActivityBridge(new TopStoriesActivity()));
    }

    @Test
    public void loadItemData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new TopItemsActivityMediator(
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(latch),
                        Mockito.mock(TopStoriesAdapter.class))).loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
    @Test
    public void topStoriesSize(){
        assertThat(new TopItemsActivityMediator(
                new TopStoriesActivityBridge(new TopStoriesActivity()))
                .topStoriesSize()).isEqualTo(0);
    }

    @Test
    public void storyAtShouldReturnNullStoryForInvalidIndex() throws InterruptedException {
        assertThat(new TopItemsActivityMediator(
                new TopStoriesActivityBridge(new TopStoriesActivity()))
                .itemAt(-1)).isEqualTo(NullStory);
    }
    @Test
    public void itemAtShouldReturnStory() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Story simpleStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer, builder);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityMediator mediator = new TopItemsActivityMediator(
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(latch),
                        Mockito.mock(TopStoriesAdapter.class)));
        mediator.loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(mediator.itemAt(0)).isEqualTo(Story.NullStory);
        Thread.sleep(500);//Sleeping so the fake network can update
        final Item actualItem = mediator.itemAt(0);
        assertThat(actualItem).isEqualTo(simpleStory);
        assertThat(actualItem.getClass()).isEqualTo(Story.class);
    }

    @Test
    public void itemAtShouldReturnJob() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Job simpleJob = hackerNewsNetworkTestResponses.simpleJob(mockWebServer, builder);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityMediator mediator = new TopItemsActivityMediator(
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(latch),
                        Mockito.mock(TopStoriesAdapter.class)));
        mediator.loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(mediator.itemAt(0)).isEqualTo(Story.NullStory);
        Thread.sleep(500);//Sleeping so the fake network can update
        final Item actualItem = mediator.itemAt(0);
        assertThat(actualItem).isEqualTo(simpleJob);
        assertThat(actualItem.getClass()).isEqualTo(Job.class);
    }
}
