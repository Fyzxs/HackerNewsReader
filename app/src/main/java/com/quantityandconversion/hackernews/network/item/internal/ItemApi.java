package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;

import retrofit2.Call;
import retrofit2.http.GET;

/* package */ interface ItemApi {
    @GET("/topstories.json")
    Call<Items> topStories();
}
