package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.hackernews.network.hackernews.internal.StoryId;

import java.util.ArrayList;
import java.util.List;

public class Stories {
    private final List<StoryId> storyIdList = new ArrayList<>();

    public Stories(StoryId[] storyIds) {
        if(storyIds == null) { throw new IllegalArgumentException("storyIds cannot be null"); }

        importItemIds(storyIds);
    }

    private void importItemIds(final StoryId[] storyIds) {
        for (final StoryId storyId : storyIds) {
            if(storyId == null){ continue; }
            storyIdList.add(storyId);
        }
    }

    public int size() {
        return storyIdList.size();
    }

    public boolean contains(final StoryId storyId) {
        return storyIdList.contains(storyId);
    }

    public StoryId itemIdAt(final int index) {
        return storyIdList.get(index);
    }
}
