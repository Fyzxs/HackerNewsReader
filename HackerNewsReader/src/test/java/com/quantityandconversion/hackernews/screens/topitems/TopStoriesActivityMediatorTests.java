package com.quantityandconversion.hackernews.screens.topitems;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetworkTestResponses;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Item.NullItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;

public class TopStoriesActivityMediatorTests extends MockWebServerTestClass {
    @Mock TopItemsActivityMediator.Bridge mockBridge;

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopItemsActivityMediator(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivityBridge");

        new TopItemsActivityMediator(mockBridge);
    }

    @Test
    public void loadItemData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        Mockito.when(mockBridge.dataChanged()).thenReturn(latch::countDown);

        new TopItemsActivityMediator(mockBridge).loadTopStoriesData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void topStoriesSizeDefaultIsZero(){
        assertThat(new TopItemsActivityMediator(mockBridge).topStoriesSize()).isEqualTo(0);
    }

    @Test
    public void storyAtShouldReturnNullStoryForInvalidIndex() throws InterruptedException {
        assertThat(new TopItemsActivityMediator(mockBridge).itemAt(-1)).isEqualTo(NullItem);
    }
    @Test
    public void itemAtShouldReturnStory() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Item simpleStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer, builder);

        final Item actualItem = itemAtShouldBe(simpleStory);
        assertThat(actualItem.isStory()).isTrue();
    }

    @Test
    public void itemAtShouldReturnJobType() throws InterruptedException {

        final HackerNewsNetworkTestResponses.Builder builder = new HackerNewsNetworkTestResponses.Builder();
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer, builder);

        final Item simpleJob = hackerNewsNetworkTestResponses.simpleJob(mockWebServer, builder);

        final Item actualItem = itemAtShouldBe(simpleJob);
        assertThat(actualItem.isJob()).isTrue();
    }

    private Item itemAtShouldBe(Item simpleStory) throws InterruptedException {
        final CountDownLatch listLatch = new CountDownLatch(1);
        final CountDownLatch itemLatch = new CountDownLatch(1);
        final TopItemsActivityMediator mediator = new TopItemsActivityMediator(mockBridge);
        Mockito.when(mockBridge.dataChanged()).thenReturn(new Runnable() {
            @Override
            public void run() {
                listLatch.countDown();
            }
        });
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                itemLatch.countDown();
                return null;
            }
        }).when(mockBridge).notifyTopStoryChanged(anyInt());

        mediator.loadTopStoriesData();

        assertThat(listLatch.await(1, TimeUnit.SECONDS)).isTrue();
        assertThat(mediator.itemAt(0)).isEqualTo(NullItem);

        assertThat(itemLatch.await(1, TimeUnit.SECONDS)).isTrue();

        final Item actualItem = mediator.itemAt(0);
        assertThat(actualItem).isEqualTo(simpleStory);
        return actualItem;
    }

}
