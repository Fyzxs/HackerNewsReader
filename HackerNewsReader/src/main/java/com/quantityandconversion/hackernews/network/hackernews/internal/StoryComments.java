package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;

public class StoryComments {
    public static final StoryComments NullStoryComments = new StoryComments(StoryId.NullStoryId, 0L);
    private final long commentCount;
    private final StoryId storyId;


    /* package */ StoryComments(final StoryId storyId, final long commentCount) {
        if(storyId == null){ throw new IllegalArgumentException("storyId can not be null");}
        if(commentCount < 0){ throw new IllegalArgumentException("commentCount can not be less than zero");}

        this.storyId = storyId;
        this.commentCount = commentCount;
    }

    public void commentCountInto(final SetText item) {
        //yea yea; localize
        item.setText(Long.toString(commentCount) + " comments");
    }
}
