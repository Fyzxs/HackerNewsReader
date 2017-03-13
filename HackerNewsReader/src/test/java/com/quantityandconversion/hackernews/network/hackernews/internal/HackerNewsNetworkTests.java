package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Items;
import com.quantityandconversion.test.MockWebServerTestClass;
import com.quantityandconversion.utils.network.LoggingInterceptor;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HackerNewsNetworkTests extends MockWebServerTestClass {


    @Test
    public void constructorHandlesNull(){
        new HackerNewsNetwork(null, new LoggingInterceptor());
        new HackerNewsNetwork();
    }
    @Test
    public void constructorHandlesMockUrl(){
        new HackerNewsNetwork(mockWebServer.url("/"), new LoggingInterceptor());
        new HackerNewsNetwork();
    }

    @Test
    public void topStories() throws IOException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final Call<Items> topStoriesCall = new HackerNewsNetwork().topStories();
        final Items items = topStoriesCall.execute().body();
        assertNotNull(items);
        assertEquals(2, items.size());
    }

    @Test
    public void item() throws IOException{
        final Item builtStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer);

        final Call<Item> itemCall = new HackerNewsNetwork().item(ItemId.createStoryId(1L));
        final Item story = itemCall.execute().body();

        assertThat(story).isEqualTo(builtStory);
    }
}
