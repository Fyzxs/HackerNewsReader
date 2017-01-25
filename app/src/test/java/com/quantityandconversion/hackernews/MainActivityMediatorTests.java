package com.quantityandconversion.hackernews;

import com.quantityandconversion.hackernews.network.item.internal.ItemNetworkTestResponses;

import org.junit.Rule;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.mockwebserver.MockWebServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MainActivityMediatorTests {
    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new MainActivityMediator(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("mainActivityBridge");

        new MainActivityMediator(new MainActivityBridge(new MainActivity()));
    }

    @Test
    public void loadItemData() throws InterruptedException {
        new ItemNetworkTestResponses().simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new MainActivityMediator(new FakeMainActivityBridge(latch, new MainActivity())).loadItemData();

        assertThat(latch.await(1000, TimeUnit.SECONDS)).isTrue();
    }
}