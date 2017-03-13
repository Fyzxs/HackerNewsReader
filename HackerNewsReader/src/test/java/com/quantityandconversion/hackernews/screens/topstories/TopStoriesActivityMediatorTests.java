package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetworkTestResponses;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Item.NullItem;
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
                        Mockito.mock(TopItemsAdapter.class))).loadTopStoriesData();

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
                .itemAt(-1)).isEqualTo(NullItem);
    }
    @Test
    public void itemAtShouldReturnStory() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Item simpleStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer, builder);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityMediator mediator = new TopItemsActivityMediator(
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(latch),
                        Mockito.mock(TopItemsAdapter.class)));
        mediator.loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(mediator.itemAt(0)).isEqualTo(NullItem);
        Thread.sleep(500);//Sleeping so the fake network can update
        final Item actualItem = mediator.itemAt(0);
        assertThat(actualItem).isEqualTo(simpleStory);
        assertThat(actualItem.isStory()).isTrue();
    }

    @Test
    public void itemAtShouldReturnJobType() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Item simpleJob = hackerNewsNetworkTestResponses.simpleJob(mockWebServer, builder);

        final CountDownLatch latch = new CountDownLatch(1);
        final TopItemsActivityMediator mediator = new TopItemsActivityMediator(
                new FakeTopStoriesActivityBridge(new FakeTopStoriesActivity(latch),
                        Mockito.mock(TopItemsAdapter.class)));
        mediator.loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(mediator.itemAt(0)).isEqualTo(NullItem);
        Thread.sleep(500);//Sleeping so the fake network can update
        final Item actualItem = mediator.itemAt(0);
        assertThat(actualItem).isEqualTo(simpleJob);
        assertThat(actualItem.isJob()).isTrue();
    }
}
