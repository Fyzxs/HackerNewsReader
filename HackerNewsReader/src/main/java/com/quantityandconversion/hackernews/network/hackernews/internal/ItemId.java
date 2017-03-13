package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;

/*
 * Two Id's of different "Types" will be equal. The Slug is a helper; not decider.
 */
public class ItemId extends SimpleWrapper<Long> {
    public final static ItemId NullItemId = new ItemId(-1L, null);
    private final static Object ItemId = new Object();
    private final static Object StoryId = new Object();
    private final static Object JobId = new Object();

    private final Object slug;

    private ItemId(final long id, final Object type) {
        super(id);
        slug = type;
    }

    public static ItemId createStoryId(final long id){
        return new ItemId(id, StoryId);
    }

    /* package */ long idAsLong(){
        return value();
    }

    public boolean isStoryId() {
        return slug == StoryId;
    }

    public static ItemId createJobId(final long id) {
        return new ItemId(id, JobId);
    }

    public static ItemId createUnknownId(final long id) {
        return new ItemId(id, ItemId);
    }

    public boolean isJobId() {
        return slug == JobId;
    }
    public boolean isUnknownId() {
        return slug == ItemId;
    }
}
