package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.StoryId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/* package */ interface ItemApi {
    @GET("/topstories.json")
    Call<ArrayList<StoryId>> topStories();
}
