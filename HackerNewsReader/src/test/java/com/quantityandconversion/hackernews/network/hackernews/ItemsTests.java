package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static com.quantityandconversion.hackernews.network.hackernews.Item.NullItem;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemsTests extends MockWebServerTestClass {

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new Items(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemIds cannot be null");

        assertThatThrownBy(() -> new Items(new ItemId[1]))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemId cannot be null");
    }

    @Test
    public void size(){
        assertThat(new Items(new ItemId[]{}).size()).isEqualTo(0);
        assertThat(new Items(new ItemId[]{ItemId.createStoryId(12345L)}).size()).isEqualTo(1);
    }

    @Test
    public void storyAt() throws InterruptedException {
        assertThatThrownBy(() -> new Items(new ItemId[0]).itemAt(0, null))
                .isInstanceOf(IndexOutOfBoundsException.class);

        assertThat(new Items(new ItemId[]{ItemId.createStoryId(12345L)}).itemAt(0, null)).isEqualTo(NullItem);
    }

    @Test
    public void storyAtRefresh() throws InterruptedException {
        final Item builtStory = this.hackerNewsNetworkTestResponses.simpleStory(mockWebServer);
        final CountDownLatch latch = new CountDownLatch(1);
        final Items items = new Items(new ItemId[]{builtStory.itemId()});
        assertThat(items.itemAt(0, new Items.ItemRefreshCallback(){

            @Override
            public void itemRefreshed(int index) {
                assertThat(index).isEqualTo(0);
                latch.countDown();
            }
        })).isEqualTo(NullItem);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();

        assertThat(items.itemAt(0, null)).isEqualTo(builtStory);
    }
}
