package com.quantityandconversion.hackernews.network.item.internal;

import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class ItemNetworkTestResponses {

    public void simpleItemIdList(final MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_OK).setBody("[10000,2]"));

        new ItemNetwork(mockWebServer.url("/"));//For The Tests!
    }
}
