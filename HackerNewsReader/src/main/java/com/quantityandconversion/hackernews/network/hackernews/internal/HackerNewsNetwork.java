package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.VisibleForTesting;

import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class HackerNewsNetwork {

    private static HttpUrl serverUrl = null;

    public HackerNewsNetwork() {
        if(serverUrl != null) return;
        serverUrl = HttpUrl.parse(HackerNewsApi.URL);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public HackerNewsNetwork(final HttpUrl url) {
        serverUrl = url;
    }

    public Call<Stories> topStories() {
        return hackerNewsApiTopStories().topStories();
    }


    private HackerNewsApi hackerNewsApiTopStories() {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder()
                        .add(new StoriesAdapter())
                        .add(new StoryIdAdapter())
                        .build()))
                .build()
                .create(HackerNewsApi.class);
    }

    private HackerNewsApi hackerNewsApiStory() {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder()
                        .add(new StoriesAdapter())
                        .add(new StoryIdAdapter())
                        .build()))
                .build()
                .create(HackerNewsApi.class);
    }
}
