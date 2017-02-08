package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HackerNewsNetworkTests extends MockWebServerTestClass {

    @Test
    public void topStories() throws IOException {
        hackerNewsNetworkTestResponses.simpleItemIdList(mockWebServer);

        final Call<Stories> topStoriesCall = new HackerNewsNetwork(mockWebServer.url("/")).topStories();
        final Stories stories = topStoriesCall.execute().body();
        assertNotNull(stories);
        assertEquals(2, stories.size());
    }

    @Test
    public void item() throws IOException{
        hackerNewsNetworkTestResponses.minimalItem(mockWebServer);

        final Call<Story> itemCall = new HackerNewsNetwork(mockWebServer.url("/")).item(111111);
    }
}
