package com.quantityandconversion.utils.date;

/* package */ abstract class DateUtil {
    /* package */ static DateUtil Android = new DateUtil() {
        @Override
        public CharSequence relativeTimeSpanString(final long startTimeMs) {
            return android.text.format.DateUtils.getRelativeTimeSpanString(startTimeMs);
        }
    };
    /* package */ static DateUtil SecondsOnly = new DateUtil() {
        @Override
        public CharSequence relativeTimeSpanString(final long startTimeMs) {
            final long currentTime = System.currentTimeMillis();
            final long secAgo = startTimeMs - currentTime;
            return secAgo + " seconds ago - only";
        }
    };

    public abstract CharSequence relativeTimeSpanString(final long startTime);

}
