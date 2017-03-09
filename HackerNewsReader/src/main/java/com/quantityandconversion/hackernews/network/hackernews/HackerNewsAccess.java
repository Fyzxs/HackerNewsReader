package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetwork;
import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;

import retrofit2.Callback;


public class HackerNewsAccess {

    public void topStories(final Callback<Stories> callback) {
        new HackerNewsNetwork().topStories().enqueue(callback);
    }

    public void story(final ItemId itemId, final Callback<Story> callback) {
        new HackerNewsNetwork().story(itemId).enqueue(callback);
    }
}
