package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.utils.Strings;

public class ItemComments {
    public static final ItemComments NullItemComments = new ItemComments(0L);
    private final long commentCount;


    /* package */ ItemComments(final long commentCount) {
        if(commentCount < 0){ throw new IllegalArgumentException("commentCount can not be less than zero");}

        this.commentCount = commentCount;
    }

    public void commentCountInto(final SetText item) {
        if(item == null){return;}

        item.setText(this == NullItemComments ? Strings.Empty : Long.toString(commentCount) + " comments");//yea yea; localize
    }
}
