package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HackerNewsNetworkTests extends MockWebServerTestClass {


    @Test
    public void constructorHandlesNull(){
        new HackerNewsNetwork(null);
        new HackerNewsNetwork();
    }
    @Test
    public void constructorHandlesMockUrl(){
        new HackerNewsNetwork(mockWebServer.url("/"));
        new HackerNewsNetwork();
    }

    @Test
    public void topStories() throws IOException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final Call<Stories> topStoriesCall = new HackerNewsNetwork().topStories();
        final Stories stories = topStoriesCall.execute().body();
        assertNotNull(stories);
        assertEquals(2, stories.size());
    }

    @Test
    public void item() throws IOException{
        final Story builtStory = hackerNewsNetworkTestResponses.simpleStory(mockWebServer);

        final Call<Item> itemCall = new HackerNewsNetwork().item(ItemId.createStoryId(1L));
        final Item story = itemCall.execute().body();

        assertThat(story).isEqualTo(builtStory);
    }
}
