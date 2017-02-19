package com.quantityandconversion.utils.date;

public class DateUtils {
    private static DateUtil ActiveDateUtil = DateUtil.Android;

    /* package */ static void useSecondsOnly() {
        ActiveDateUtil = DateUtil.SecondsOnly;
    }

    public CharSequence relativeTimeSpanString(final long startTime){
        return ActiveDateUtil.relativeTimeSpanString(startTime);
    }
}
