package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.test.utils.RandomValues;

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

        new HackerNewsNetwork(mockWebServer.url("/"));
    }

    public List<Long> simpleStoryIdList(final MockWebServer mockWebServer) {
        return simpleStoryIdList(mockWebServer, new Builder());
    }
    public List<Long> simpleStoryIdList(final MockWebServer mockWebServer, final Builder builder) {
        final Builder other = new Builder();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody("[" + builder.itemId + "," + other.itemId + "]"));

        new HackerNewsNetwork(mockWebServer.url("/"));

        return new ArrayList<Long>() {{
            add(builder.itemId);
            add(other.itemId);
        }};
    }
    public Story simpleStory(final MockWebServer mockWebServer) {
        return simpleStory(mockWebServer, new Builder());
    }
    public Story simpleStory(final MockWebServer mockWebServer, final Builder builder){
        final StoryBuilder storyBuilder = new StoryBuilder();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(storyBuilder.setStoryId(builder.itemId).buildJson()));

        new HackerNewsNetwork(mockWebServer.url("/"));

        return storyBuilder.buildStory();
    }

    public Job simpleJob(final MockWebServer mockWebServer) {
        return simpleJob(mockWebServer, new Builder());
    }
    public Job simpleJob(final MockWebServer mockWebServer, final Builder builder){
        final JobBuilder jobBuilder = new JobBuilder();

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(jobBuilder.setItemId(builder.itemId).buildJson()));

        new HackerNewsNetwork(mockWebServer.url("/"));

        return jobBuilder.buildJob();
    }

}
