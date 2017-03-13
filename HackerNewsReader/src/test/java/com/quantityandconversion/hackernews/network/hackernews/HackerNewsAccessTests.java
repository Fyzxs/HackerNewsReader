package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class HackerNewsAccessTests extends MockWebServerTestClass {

    @Test
    public void topStories() throws Exception {
        final List<Long> storyIds = hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new HackerNewsAccess().topStories(new Callback<Items>(){
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                assertThat(response.isSuccessful()).isTrue();
                assertThat(response.body()).isNotNull();
                final Items items = response.body();
                assertThat(items.size()).isEqualTo(2);
                assertThat(items.contains(ItemId.createStoryId(storyIds.get(0)))).isTrue();
                assertThat(items.contains(ItemId.createStoryId(storyIds.get(1)))).isTrue();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }

    @Test
    public void story() throws Exception{
        final Item builtStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new HackerNewsAccess().item(builtStory.itemId(), new Callback<Item>(){
            @Override
            public void onResponse(final Call<Item> call, final Response<Item> response) {
                assertThat(response.isSuccessful()).isTrue();
                assertThat(response.body()).isNotNull();
                latch.countDown();
            }

            @Override
            public void onFailure(final Call<Item> call, final Throwable t) {
                assertThat(call).isNull();
            }
        });

        assertThat(latch.await(5, TimeUnit.SECONDS)).isTrue();
    }
}
