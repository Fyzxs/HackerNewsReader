package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.VisibleForTesting;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Items;
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

    public Call<Items> topStories() {
        return hackerNewsApiTopStories().topStories();
    }


    public Call<Item> item(final ItemId itemId) {
        return hackerNewsApiItem().item(itemId.idAsLong());
    }

    private HackerNewsApi hackerNewsApiTopStories() {
        return hackerNewsApi(new ItemsAdapter(), new ItemIdAdapter());
    }

    private HackerNewsApi hackerNewsApiItem() {
        return hackerNewsApi(new ItemAdapter());
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

}
