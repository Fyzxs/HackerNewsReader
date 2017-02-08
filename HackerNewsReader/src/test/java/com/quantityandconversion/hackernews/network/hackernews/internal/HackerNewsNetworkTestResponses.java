package com.quantityandconversion.hackernews.network.hackernews.internal;

import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class HackerNewsNetworkTestResponses {

    public void simpleStoryIdList(final MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("[10000,2]"));

        new HackerNewsNetwork(mockWebServer.url("/"));
    }

    public void simpleStory(final MockWebServer mockWebServer){
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"title\":\"this is a faked title\"}"));
        new HackerNewsNetwork(mockWebServer.url("/"));
    }
}
