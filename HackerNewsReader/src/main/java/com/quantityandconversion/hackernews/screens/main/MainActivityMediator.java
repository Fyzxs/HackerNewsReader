package com.quantityandconversion.hackernews.screens.main;

import com.quantityandconversion.hackernews.network.hackernews.HackerNewsAccess;
import com.quantityandconversion.hackernews.network.hackernews.Stories;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainActivityMediator {
    private MainActivityBridge mainActivityBridge;

    public MainActivityMediator(final MainActivityBridge mainActivityBridge) {
        if(mainActivityBridge == null) { throw new IllegalArgumentException("mainActivityBridge cannot be null"); }
        this.mainActivityBridge = mainActivityBridge;
    }

    public void loadItemData() {
        new HackerNewsAccess().topStories(new Callback<Stories>() {
            @Override
            public void onResponse(Call<Stories> call, Response<Stories> response) {
                mainActivityBridge.loadedItemData(response.body());
            }

            @Override
            public void onFailure(Call<Stories> call, Throwable t) {
                throw new UnsupportedOperationException("Not Yet Implemented");
            }
        });
    }
}
