package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;

public class Stories {

    public interface StoryRefreshCallback{
        void storyRefreshed(final int index);
    }
    private final Map<StoryId, Story> storyMap = new HashMap<>();
    private final StoryId[] storyIds;

    public Stories(final StoryId[] storyIds) {
        if(storyIds == null) { throw new IllegalArgumentException("storyIds cannot be null"); }

        this.storyIds = storyIds;
        importStoryIds();
    }

    private void importStoryIds() {
        for (final StoryId storyId : storyIds) {
            if(storyId == null){ throw new IllegalArgumentException("storyId cannot be null");}
            storyMap.put(storyId, NullStory);
        }
    }

    public int size() {
        return storyMap.size();
    }

    public boolean contains(final StoryId storyId) {
        return storyMap.get(storyId) != null;
    }

    public Story storyAt(final int index, final StoryRefreshCallback storyRefreshCallback) {
        return storyRefresh(index, storyRefreshCallback);
    }

    private Story storyRefresh(final int index, final StoryRefreshCallback storyRefreshCallback){
        final Story story = getStory(index);
        if(!shouldRefreshStory(story, storyRefreshCallback)){ return story; }

        new HackerNewsAccess().story(story.storyId(), new Callback<Story>() {
            @Override
            public void onResponse(final Call<Story> call, final Response<Story> response) {
                final Story story = response.body();
                storyMap.put(story.storyId(), story);
                storyRefreshCallback.storyRefreshed(index);
            }

            @Override
            public void onFailure(final Call<Story> call, final Throwable t) {
                throw new UnsupportedOperationException("onFailure not implemented");
            }
        });

        return story;
    }

    private Story getStory(final int index) {
        final Story story = storyMap.get(storyIds[index]);
        return story == null ? NullStory : story;
    }

    private boolean shouldRefreshStory( final Story story, final StoryRefreshCallback storyRefreshCallback) {
        return !isValidStory(story) && storyRefreshCallback != null;
    }
    private boolean isValidStory(final Story story){
        return story != null && !NullStory.equals(story);
    }

}
