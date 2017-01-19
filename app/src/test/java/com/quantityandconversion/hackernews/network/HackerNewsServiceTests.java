package com.quantityandconversion.hackernews.network;

import org.junit.Test;

import java.util.ArrayList;

import retrofit2.Call;

public class HackerNewsServiceTests {

    @Test
    public void TopStoriesExistsOnInterface(){
        final HackerNewsService service = new HackerNewsService(){
            @Override
            public Call<ArrayList<StoryId>> topStories() {
                return null;
            }
        };
        final Call<ArrayList<StoryId>> topStoriesCall = service.topStories();
    }
}
