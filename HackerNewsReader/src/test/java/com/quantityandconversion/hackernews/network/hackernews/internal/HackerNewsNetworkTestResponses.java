package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Story;

import java.net.HttpURLConnection;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class HackerNewsNetworkTestResponses {

    public static final Story SimpleStory = new Story(new StoryId(12345L), new Title("this is a faked title"), new Author("That_Guy"));

    public void simpleStoryIdList(final MockWebServer mockWebServer) {
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("[12345,2]"));

        new HackerNewsNetwork(mockWebServer.url("/"));
    }

    public void simpleStory(final MockWebServer mockWebServer){
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("{\"id\":12345,\"title\":\"this is a faked title\", \"type\":\"story\", \"by\":\"That_Guy\"}"));

        new HackerNewsNetwork(mockWebServer.url("/"));
    }
}
