package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.hackernews.network.hackernews.Job;
import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

/* package */ class JobAdapter {
    @FromJson
    /* package */ Job jobFromJson(final JobJson jobJson) {
        if(jobJson == null) { throw new IllegalArgumentException("jobJson can not be null"); }
        if(!"job".equals(jobJson.type)) { throw new IllegalArgumentException("data type is not a job"); }
        final ItemId itemId = ItemId.createJobId(jobJson.id);
        return new Job(itemId,
                new Title(jobJson.title),
                new Author(jobJson.by),
                new PostTime(jobJson.time));
    }

    @ToJson
    /* package */ long jobToJson(final Job job) {
        throw new UnsupportedOperationException("serializing to json not supported");
    }
}
