package com.quantityandconversion.hackernews.network.item.internal;

import android.support.annotation.VisibleForTesting;

import com.quantityandconversion.hackernews.network.item.Items;
import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class ItemNetwork {

    private static HttpUrl serverUrl = null;

    public ItemNetwork() {
        if(serverUrl != null) return;
        serverUrl = HttpUrl.parse(ItemApi.URL);
    }

    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    public ItemNetwork(final HttpUrl url) {
        serverUrl = url;
    }

    public Call<Items> topStories() {
        return topStoriesItemApi().topStories();
    }

    private ItemApi topStoriesItemApi() {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder()
                        .add(new ItemsAdapter())
                        .add(new ItemAdapter())
                        .build()))
                .build()
                .create(ItemApi.class);
    }
}
