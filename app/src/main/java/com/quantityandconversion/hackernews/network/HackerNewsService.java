package com.quantityandconversion.hackernews.network;

import java.util.ArrayList;

import retrofit2.Call;

interface HackerNewsService {
    Call<ArrayList<StoryId>> topStories();
}
