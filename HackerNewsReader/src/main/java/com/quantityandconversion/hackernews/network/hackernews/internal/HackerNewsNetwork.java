package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.VisibleForTesting;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.utils.network.LoggingInterceptor;
import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
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


    public Call<Story> story(final ItemId itemId) {
        return hackerNewsApiStory().story(itemId.idAsLong());
    }

    private HackerNewsApi hackerNewsApiTopStories() {
        return hackerNewsApi(new ItemsAdapter(), new ItemIdAdapter());
    }

    private HackerNewsApi hackerNewsApiStory() {
        return hackerNewsApi(new StoryAdapter());
    }

    private HackerNewsApi hackerNewsApi(final Object... adapters){
        final Moshi.Builder moshiBuilder = new Moshi.Builder();
        for(Object adapter : adapters){
            moshiBuilder.add(adapter);
        }
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .client(new OkHttpClient().newBuilder().addInterceptor(new LoggingInterceptor()).build())
                .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
                .build()
                .create(HackerNewsApi.class);
    }

    public Call<Job> job(ItemId jobId) {
        return hackerNewsApiJob().job(jobId.idAsLong());
    }
    private HackerNewsApi hackerNewsApiJob() {
        return hackerNewsApi(new JobAdapter());
    }
}
