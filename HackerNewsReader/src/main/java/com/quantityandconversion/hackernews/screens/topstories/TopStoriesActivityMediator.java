package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.HackerNewsAccess;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;

/* package */ class TopStoriesActivityMediator {
    private final TopStoriesActivityBridge topStoriesActivityBridge;

    /* package */ TopStoriesActivityMediator(final TopStoriesActivityBridge topStoriesActivityBridge) {
        if(topStoriesActivityBridge == null) { throw new IllegalArgumentException("topStoriesActivityBridge can not be null"); }
        this.topStoriesActivityBridge = topStoriesActivityBridge;
    }

    /* package */ int topStoriesSize() {
        return 0;
    }

    /* package */ Story storyAt(final int index) {
        return NullStory;
    }

    /* package */ void loadTopStoriesData() {
        new HackerNewsAccess().topStories(new Callback<Stories>() {
            @Override
            public void onResponse(Call<Stories> call, Response<Stories> response) {
                topStoriesActivityBridge.loadedTopStoriesData(response.body());
            }

            @Override
            public void onFailure(Call<Stories> call, Throwable t) {
                throw new UnsupportedOperationException("Not Yet Implemented");
            }
        });
    }
}
