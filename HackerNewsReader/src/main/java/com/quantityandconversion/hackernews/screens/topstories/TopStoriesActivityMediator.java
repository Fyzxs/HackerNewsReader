package com.quantityandconversion.hackernews.screens.topstories;

import com.quantityandconversion.hackernews.network.hackernews.HackerNewsAccess;
import com.quantityandconversion.hackernews.network.hackernews.Stories;
import com.quantityandconversion.hackernews.network.hackernews.Story;
import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;

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
        return topStories.size();
    }

    /* package */ Story storyAt(final int index) {
        return topStories.itemIdAt(index);
    }

    private void doUpdate(final StoryId storyId){
        new HackerNewsAccess().story(storyId, new Callback<Story>() {
            @Override
            public void onResponse(final Call<Story> call, final Response<Story> response) {

            }

            @Override
            public void onFailure(final Call<Story> call, final Throwable t) {
                throw new UnsupportedOperationException("Not Yet Implemented");
            }
        });
    }

    /* package */ void loadTopStoriesData() {
        new HackerNewsAccess().topStories(new Callback<Stories>() {
            @Override
            public void onResponse(final Call<Stories> call, final Response<Stories> response) {
                topStories = response.body();
                topStoriesActivityBridge.notifyTopStoriesChanged();
            }

            @Override
            public void onFailure(final Call<Stories> call, final Throwable t) {
                throw new UnsupportedOperationException("Not Yet Implemented");
            }
        });
    }
}
