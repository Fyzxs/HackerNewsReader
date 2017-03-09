package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;

//ID Wrappers will need to collapse?
public class ItemId extends SimpleWrapper<Long> {
    public final static ItemId NULL_ITEM_ID = new ItemId(-1L, null);
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
}
