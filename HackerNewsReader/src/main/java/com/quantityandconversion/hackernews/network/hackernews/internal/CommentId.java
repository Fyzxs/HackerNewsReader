package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;

/* package */ class CommentId extends SimpleWrapper<Long> {

    /* package */ CommentId(final long commentId) {
        super(commentId);
    }

    /* package */ long idAsLong() {
        return value();
    }
}
