package com.quantityandconversion.hackernews.network.item.internal;

import com.quantityandconversion.hackernews.network.item.Items;

import org.junit.Test;

import retrofit2.Call;

public class ItemApiTests {

    @Test
    public void TopStoriesExistsOnInterface(){
        final ItemApi service = new ItemApi(){
            @Override
            public Call<Items> topStories() {
                return null;
            }
        };
        final Call<Items> topStoriesCall = service.topStories();
    }
}
