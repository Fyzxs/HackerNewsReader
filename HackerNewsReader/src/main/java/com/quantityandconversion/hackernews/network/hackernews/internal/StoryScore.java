package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.ood.SimpleWrapper;

/* package */ class StoryScore extends SimpleWrapper<Long> {

    /* package */ StoryScore(final long commentId) {
        super(commentId);
    }

    public void scoreInto(final SetText item){
        if(item == null){return;}
        item.setText(Long.toString(value()));
    }
}
