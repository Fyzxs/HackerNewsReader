package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/* package */ interface HackerNewsApi {
    String URL = "https://hacker-news.firebaseio.com/v0/";

    @GET("topstories.json")
    Call<Stories> topStories();

    @GET("item/{storyId}.json")
    Call<Story> story(@Path("storyId") final long storyId);

    @GET("item/{jobId}.json")
    Call<Job> job(@Path("jobId") final long jobId);
}
