package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.HackerNewsNetwork;

import retrofit2.Callback;


public class HackerNewsAccess {

    public void topStories(final Callback<Stories> callback) {
        new HackerNewsNetwork().topStories().enqueue(callback);
    }
}
