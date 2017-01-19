package com.quantityandconversion.hackernews.network;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

interface HackerNewsApi {
    @GET("/topstories.json")
    Call<ArrayList<StoryId>> topStories();
}
