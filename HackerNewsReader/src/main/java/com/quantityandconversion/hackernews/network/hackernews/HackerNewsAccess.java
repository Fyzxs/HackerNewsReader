package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetwork;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;

import retrofit2.Callback;


public class HackerNewsAccess {

    public void topStories(final Callback<Items> callback) {
        new HackerNewsNetwork().topStories().enqueue(callback);
    }

    public void item(final ItemId itemId, final Callback<Item> callback) {
        new HackerNewsNetwork().item(itemId).enqueue(callback);
    }
}
