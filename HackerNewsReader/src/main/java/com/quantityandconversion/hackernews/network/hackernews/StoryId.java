package com.quantityandconversion.hackernews.network.hackernews;

public class StoryId {
    private long id;

    public StoryId(final long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Long.valueOf(id).hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof StoryId && this.equals((StoryId)other);
    }
    private boolean equals(final StoryId other){
        return this.id == other.id;
    }
}
