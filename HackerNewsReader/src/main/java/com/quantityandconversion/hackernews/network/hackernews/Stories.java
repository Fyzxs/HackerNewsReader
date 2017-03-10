package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.ItemId;
import com.quantityandconversion.utils.log.FyzLog;

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
    private final Map<ItemId, Story> storyMap = new HashMap<>();
    private final ItemId[] itemIds;

    public Stories(final ItemId[] itemIds) {
        if(itemIds == null) { throw new IllegalArgumentException("itemIds cannot be null"); }

        this.itemIds = itemIds;
        importStoryIds();
    }

    private void importStoryIds() {
        for (final ItemId itemId : itemIds) {
            if(itemId == null){ throw new IllegalArgumentException("itemId cannot be null");}
            storyMap.put(itemId, NullStory);
        }
    }

    public int size() {
        return storyMap.size();
    }

    public boolean contains(final ItemId itemId) {
        return storyMap.get(itemId) != null;
    }

    public Story storyAt(final int index, final StoryRefreshCallback storyRefreshCallback) {
        final ItemId itemId = itemIds[index];
        final Story story = storyMap.get(itemId);
        if(!shouldRefreshStory(story, storyRefreshCallback)){
            return story;
        }

        refreshStory(index, storyRefreshCallback, itemId);

        return story;
    }

    private void refreshStory(final int index, final StoryRefreshCallback storyRefreshCallback, ItemId itemId) {
        new HackerNewsAccess().story(itemId, new Callback<Story>() {
            @Override
            public void onResponse(final Call<Story> call, final Response<Story> response) {
                final Story story = response.body();
                storyMap.put(story.storyId(), story);
                storyRefreshCallback.storyRefreshed(index);
            }

            @Override
            public void onFailure(final Call<Story> call, final Throwable t) {
                FyzLog.d(call.request().url().toString());
                FyzLog.d(t.getMessage());
                throw new UnsupportedOperationException("onFailure not implemented");
            }
        });
    }

    private boolean shouldRefreshStory( final Story story, final StoryRefreshCallback storyRefreshCallback) {
        return !isValidStory(story) && storyRefreshCallback != null;
    }
    private boolean isValidStory(final Story story){
        return story != null && !NullStory.equals(story);
    }

}
