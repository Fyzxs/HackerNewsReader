package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TopStoriesActivityBridgeTests extends MockWebServerTestClass {

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new TopStoriesActivityBridge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("topStoriesActivity");

        new TopStoriesActivityBridge(new TopStoriesActivity());
    }

    @Test
    public void loadData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopStoriesActivity fakeTopStoriesActivity = new FakeTopStoriesActivity(latch);
        final TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(fakeTopStoriesActivity);

        topStoriesActivityBridge.loadData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void loadDataError() throws InterruptedException {
        hackerNewsNetworkTestResponses.emptyBodyDataError(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopStoriesActivity fakeTopStoriesActivity = new FakeTopStoriesActivity(latch);
        final TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(fakeTopStoriesActivity);

        topStoriesActivityBridge.loadData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void notifyTopStoriesChanged() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopStoriesActivity fakeTopStoriesActivity = new FakeTopStoriesActivity(latch);
        final TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(fakeTopStoriesActivity);

        topStoriesActivityBridge.notifyTopStoriesChanged(TopStoriesActivityBridge.DataLoadStrategy.DataChanged);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void notifyTopStoryChanged() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        final FakeTopStoriesActivity fakeTopStoriesActivity = new FakeTopStoriesActivity(latch);
        final TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(fakeTopStoriesActivity);

        topStoriesActivityBridge.notifyTopStoryChanged(0);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
    @Test
    public void createTopStoriesAdapter(){
        final FakeTopStoriesActivity fakeTopStoriesActivity = new FakeTopStoriesActivity(null);
        final TopStoriesActivityBridge topStoriesActivityBridge = new TopStoriesActivityBridge(fakeTopStoriesActivity);

        final TopStoriesAdapter adapter = topStoriesActivityBridge.createTopStoriesAdapter();

        assertThat(adapter).isNotNull();
    }
}
