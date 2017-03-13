package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Items;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/* package */ interface HackerNewsApi {
    String URL = "https://hacker-news.firebaseio.com/v0/";

    @GET("topstories.json")
    Call<Items> topStories();

    @GET("item/{itemId}.json")
    Call<Item> item(@Path("itemId") final long itemId);

}
