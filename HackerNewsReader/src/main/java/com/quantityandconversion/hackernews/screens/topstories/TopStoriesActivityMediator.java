package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.HackerNewsAccess;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* package */ class TopStoriesActivityMediator {
    private final TopStoriesActivityBridge topStoriesActivityBridge;
    private Stories topStories;

    /* package */ TopStoriesActivityMediator(final TopStoriesActivityBridge topStoriesActivityBridge) {
        if(topStoriesActivityBridge == null) { throw new IllegalArgumentException("topStoriesActivityBridge can not be null"); }
        this.topStoriesActivityBridge = topStoriesActivityBridge;
    }

    /* package */ int topStoriesSize() {
        return topStories == null ? 0 : topStories.size();
    }

    /* package */ Story storyAt(final int index) {
        if (topStories == null) { return Story.NullStory; }
        return topStories.storyAt(index, topStoriesActivityBridge::notifyTopStoryChanged);
    }

    /* package */ void loadTopStoriesData() {
        new HackerNewsAccess().topStories(new Callback<Stories>() {
            @Override
            public void onResponse(final Call<Stories> call, final Response<Stories> response) {
                topStories = response.body();
                dataLoadStrategyFactory(response).run(topStoriesActivityBridge);
            }

            @Override
            public void onFailure(final Call<Stories> call, final Throwable t) {
                dataLoadStrategyFactory(null).run(topStoriesActivityBridge);
            }
        });
    }

    private TopStoriesActivityBridge.DataLoadStrategy dataLoadStrategyFactory(final Response<Stories> response){
        return response == null || !response.isSuccessful()
                ? TopStoriesActivityBridge.DataLoadStrategy.DataError
                : TopStoriesActivityBridge.DataLoadStrategy.DataChanged;
    }
}
