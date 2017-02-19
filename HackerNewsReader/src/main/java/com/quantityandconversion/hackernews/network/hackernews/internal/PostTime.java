package com.quantityandconversion.hackernews.network.hackernews.internal;


import com.quantityandconversion.utils.date.DateUtils;

import com.quantityandconversion.ood.SetText;

/* package */ public class PostTime {
    public static final PostTime NullPostTime = new PostTime(0);

    private final long unixTime;

    public PostTime(final long unixTime) {
        if(unixTime < 0) { throw new IllegalArgumentException("unixTime can not be less than zero"); }
        this.unixTime = intoMillis(unixTime);
    }

    private long intoMillis(final long unixTime) {
        return unixTime * android.text.format.DateUtils.SECOND_IN_MILLIS;
    }

    public void postTimeInto(final SetText item) {
        item.setText(relativeTimeSpan());
    }

    public CharSequence relativeTimeSpan(){
        return new DateUtils().relativeTimeSpanString(unixTime);
    }
}
