package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.ood.SimpleWrapper;

public class StoryScore extends SimpleWrapper<Long> {

    public final static StoryScore NullStoryScore = new StoryScore(0);

    /* package */ StoryScore(final long commentId) {
        super(commentId);
    }

    public void scoreInto(final SetText item){
        if(item == null){return;}
        item.setText(Long.toString(value()));
    }
}
