package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;

//ID Wrappers will need to collapse?
public class StoryId extends SimpleWrapper<Long> {
    public final static StoryId NullStoryId = new StoryId(-1L);

    public StoryId(final long id) {
        super(id);
    }

    /* package */ long idAsLong(){
        return value();
    }

}
