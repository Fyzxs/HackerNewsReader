package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;

import java.util.HashMap;
import java.util.Map;

import static com.quantityandconversion.hackernews.network.hackernews.Story.NullStory;

public class Stories {
    public interface UpdateStoryCallback{
        void requiresUpdate(final StoryId storyId);
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
            if(storyId == null){ continue; }
            storyMap.put(storyId, NullStory);
        }
    }

    public int size() {
        return storyMap.size();
    }

    public boolean contains(final StoryId storyId) {
        return storyMap.get(storyId) != null;
    }

    public Story itemIdAt(final int index) {
        final Story story = storyMap.get(storyIds[index]);
        return NullStory;//storyIdList.get(index);
    }
}
