package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;

import retrofit2.Call;
import retrofit2.http.GET;

/* package */ interface ItemApi {
    String URL = "https://hacker-news.firebaseio.com/v0/";

    @GET("topstories.json")
    Call<Items> topStories();
}
