package com.quantityandconversion.hackernews.network.hackernews.internal;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Items;
import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class HackerNewsNetwork {

    private static HttpUrl serverUrl = null;
    private static Interceptor interceptorObj = null;

    public HackerNewsNetwork() {
        if(serverUrl != null) return;
        serverUrl = HttpUrl.parse(HackerNewsApi.URL);
    }


    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    /* package */ HackerNewsNetwork(final HttpUrl url, final Interceptor interceptor) {
        serverUrl = url;
        interceptorObj = interceptor;
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
                .client(getClient())
                .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
                .build()
                .create(HackerNewsApi.class);
    }

    @NonNull
    private OkHttpClient getClient() {
        return interceptorObj == null
                ? new OkHttpClient()
                : new OkHttpClient().newBuilder().addInterceptor(interceptorObj).build();
    }

}
