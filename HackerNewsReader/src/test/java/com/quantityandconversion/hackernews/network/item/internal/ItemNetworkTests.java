package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;
import com.quantityandconversion.test.MockWebServerTestClass;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ItemNetworkTests extends MockWebServerTestClass {

    @Test
    public void topStories() throws IOException {
        itemNetworkTestResponses.simpleItemIdList(mockWebServer);

        final Call<Items> topStoriesCall = new ItemNetwork(mockWebServer.url("/")).topStories();
        final Items items = topStoriesCall.execute().body();
        assertNotNull(items);
        assertEquals(2, items.size());
    }
}
