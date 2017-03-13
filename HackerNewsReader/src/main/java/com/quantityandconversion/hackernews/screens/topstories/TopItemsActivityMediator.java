package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.HackerNewsAccess;
import com.quantityandconversion.hackernews.network.hackernews.Item;
import com.quantityandconversion.hackernews.network.hackernews.Items;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* package */ class TopItemsActivityMediator {
    private final TopStoriesActivityBridge topStoriesActivityBridge;
    private Items topItems;

    /* package */ TopItemsActivityMediator(final TopStoriesActivityBridge topStoriesActivityBridge) {
        if(topStoriesActivityBridge == null) { throw new IllegalArgumentException("topStoriesActivityBridge can not be null"); }
        this.topStoriesActivityBridge = topStoriesActivityBridge;
    }

    /* package */ int topStoriesSize() {
        return topItems == null ? 0 : topItems.size();
    }

    /* package */ Item itemAt(final int index) {
        if (topItems == null) { return Item.NullItem; }
        return topItems.itemAt(index, topStoriesActivityBridge::notifyTopStoryChanged);
    }

    /* package */ void loadTopStoriesData() {
        new HackerNewsAccess().topStories(new Callback<Items>() {
            @Override
            public void onResponse(final Call<Items> call, final Response<Items> response) {
                topItems = response.body();
                dataLoadStrategyFactory(response).run(topStoriesActivityBridge);
            }

            @Override
            public void onFailure(final Call<Items> call, final Throwable t) {
                dataLoadStrategyFactory(null).run(topStoriesActivityBridge);
            }
        });
    }

    private TopStoriesActivityBridge.DataLoadStrategy dataLoadStrategyFactory(final Response<Items> response){
        return response == null || !response.isSuccessful()
                ? TopStoriesActivityBridge.DataLoadStrategy.DataError
                : TopStoriesActivityBridge.DataLoadStrategy.DataChanged;
    }
}
