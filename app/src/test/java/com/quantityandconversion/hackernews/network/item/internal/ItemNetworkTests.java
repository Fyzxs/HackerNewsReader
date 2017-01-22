package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;

import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemNetworkTests {

    @Rule
    public final MockWebServer mockWebServer = new MockWebServer();

    @Test
    public void topStories() throws IOException {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[10000,2]"));
        final Call<Items> topStoriesCall = new ItemNetwork(mockWebServer.url("/")).topStories();
        final Items items = topStoriesCall.execute().body();
        assertNotNull(items);
        assertEquals(2, items.size());
    }
}
