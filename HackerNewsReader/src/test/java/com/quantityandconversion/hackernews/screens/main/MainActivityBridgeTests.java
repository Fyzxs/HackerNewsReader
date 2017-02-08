package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MainActivityBridgeTests extends MockWebServerTestClass {

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new MainActivityBridge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("mainActivity");

        new MainActivityBridge(new MainActivity());
    }

    @Test
    public void loadData() throws InterruptedException {
        hackerNewsNetworkTestResponses.simpleStoryIdList(mockWebServer);
        final CountDownLatch latch = new CountDownLatch(1);
        final FakeMainActivity fakeMainActivity = new FakeMainActivity(latch);
        final MainActivityBridge mainActivityBridge = new MainActivityBridge(fakeMainActivity);
        mainActivityBridge.loadData();

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
