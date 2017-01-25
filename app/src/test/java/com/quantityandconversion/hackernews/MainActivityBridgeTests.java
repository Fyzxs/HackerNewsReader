package com.quantityandconversion.hackernews;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MainActivityBridgeTests {

    @Test
    public void constructor(){

        assertThatThrownBy(() -> new MainActivityBridge(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("mainActivity");

        new MainActivityBridge(new MainActivity());
    }

//    @Test
//    public void loadData() throws InterruptedException {
//
//        final CountDownLatch latch = new CountDownLatch(1);
//        final FakeMainActivity fakeMainActivity = new FakeMainActivity(latch);
//        final MainActivityBridge mainActivityBridge = new MainActivityBridge(fakeMainActivity);
//        mainActivityBridge.loadData();
//
//        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
//    }
}
