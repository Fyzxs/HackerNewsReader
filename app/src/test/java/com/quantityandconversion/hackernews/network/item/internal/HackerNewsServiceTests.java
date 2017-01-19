package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.StoryId;

import org.junit.Test;

import java.util.ArrayList;

import retrofit2.Call;

public class HackerNewsServiceTests {

    @Test
    public void TopStoriesExistsOnInterface(){
        final ItemApi service = new ItemApi(){
            @Override
            public Call<ArrayList<StoryId>> topStories() {
                return null;
            }
        };
        final Call<ArrayList<StoryId>> topStoriesCall = service.topStories();
    }
}
