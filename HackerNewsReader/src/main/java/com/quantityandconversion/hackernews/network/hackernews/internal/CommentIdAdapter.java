package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class CommentIdAdapter {
    @FromJson
    /* package */ CommentId commentIdFromJson(final long commentId) {
        return new CommentId(commentId);
    }

    @ToJson
    /* package */ long commentIdToJson(final CommentId commentId) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
