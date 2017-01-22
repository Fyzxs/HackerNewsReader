package com.quantityandconversion.hackernews.network.item;

import com.quantityandconversion.hackernews.network.item.internal.ItemNetwork;

import retrofit2.Callback;


public class ItemAccess {

    public void topStories(Callback<Items> callback) {
        new ItemNetwork().topStories().enqueue(callback);
    }
}
