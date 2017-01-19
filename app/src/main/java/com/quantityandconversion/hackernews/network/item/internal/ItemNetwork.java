package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;
import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/* package */ class ItemNetwork {

    private final HttpUrl serverUrl;

    /* package */ ItemNetwork(final HttpUrl url) {
        this.serverUrl = url;
    }

    /* package */ Call<Items> topStories() {
        return new Retrofit.Builder()
                .baseUrl(serverUrl)
                .addConverterFactory(MoshiConverterFactory.create(new Moshi.Builder()
                        .add(new ItemsAdapter())
                        .add(new ItemAdapter())
                        .build()))
                .build()
                .create(ItemApi.class).topStories();
    }
}
