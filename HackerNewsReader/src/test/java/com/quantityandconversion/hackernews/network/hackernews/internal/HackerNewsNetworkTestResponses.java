package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.network.LoggingInterceptor;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

public class HackerNewsNetworkTestResponses {

    public static class Builder{
        private long itemId = RandomValues.nextInt(Integer.MAX_VALUE);
    }

    public void emptyBodyDataError(final MockWebServer mockWebServer) {

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(""));

        new HackerNewsNetwork(mockWebServer.url("/"), new LoggingInterceptor());
    }

    public List<Long> simpleItemIdList(final MockWebServer mockWebServer) {
        return simpleItemIdList(mockWebServer, new Builder());
    }
    public List<Long> simpleItemIdList(final MockWebServer mockWebServer, final Builder builder) {
        final Builder other = new Builder();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("[" + builder.itemId + "," + other.itemId + "]"));

        new HackerNewsNetwork(mockWebServer.url("/"), new LoggingInterceptor());

        return new ArrayList<Long>() {{
            add(builder.itemId);
            add(other.itemId);
        }};
    }
    public Item simpleStory(final MockWebServer mockWebServer) {
        return simpleStory(mockWebServer, new Builder());
    }
    public Item simpleStory(final MockWebServer mockWebServer, final Builder builder){
        final StoryBuilder storyBuilder = new StoryBuilder();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(storyBuilder.setStoryId(builder.itemId).buildJson()));

        new HackerNewsNetwork(mockWebServer.url("/"), new LoggingInterceptor());

        return storyBuilder.buildStory();
    }

    public Item simpleJob(final MockWebServer mockWebServer) {
        return simpleJob(mockWebServer, new Builder());
    }
    public Item simpleJob(final MockWebServer mockWebServer, final Builder builder){
        final JobBuilder jobBuilder = new JobBuilder();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(jobBuilder.setItemId(builder.itemId).buildJson()));

        new HackerNewsNetwork(mockWebServer.url("/"), new LoggingInterceptor());

        return jobBuilder.buildJob();
    }

}
