package com.quantityandconversion.hackernews.network.item;

import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemAccessTests extends MockWebServerTestClass {

    @Test
    public void topStories() throws Exception {
        itemNetworkTestResponses.simpleItemIdList(mockWebServer);

        final CountDownLatch latch = new CountDownLatch(1);
        new ItemAccess().topStories(new Callback<Items>(){
            @Override
            public void onResponse(Call<Items> call, Response<Items> response) {
                assertThat(response.isSuccessful()).isTrue();
                assertThat(response.body()).isNotNull();
                final Items items = response.body();
                assertThat(items.size()).isEqualTo(2);
                assertThat(items.contains(new ItemId(10000))).isTrue();
                assertThat(items.contains(new ItemId(2))).isTrue();
                latch.countDown();
            }

            @Override
            public void onFailure(Call<Items> call, Throwable t) {

            }
        });

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
