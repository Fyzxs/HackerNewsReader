package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;

/*
 * Two Id's of different "Types" will be equal. The Slug is a helper; not decider.
 */
public class ItemId extends SimpleWrapper<Long> {
    public final static ItemId NULL_ITEM_ID = new ItemId(-1L, null);
    private final static Object ITEM_ID = new Object();
    private final static Object STORY_ID = new Object();
    private final static Object JOB_ID = new Object();

    private final Object slug;

    private ItemId(final long id, final Object type) {
        super(id);
        slug = type;
    }

    public static ItemId createStoryId(final long id){
        return new ItemId(id, STORY_ID);
    }

    /* package */ long idAsLong(){
        return value();
    }

    public boolean isStoryId() {
        return slug == STORY_ID;
    }

    public static ItemId createJobId(final long id) {
        return new ItemId(id, JOB_ID);
    }

    public static ItemId createUnknownId(final long id) {
        return new ItemId(id, ITEM_ID);
    }

    public boolean isJobId() {
        return slug == JOB_ID;
    }
    public boolean isUnknownId() {
        return slug == ITEM_ID;
    }
}
