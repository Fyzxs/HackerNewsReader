package com.quantityandconversion.utils.date;

public class DateUtils {
    private static DateUtil ActiveDateUtil = DateUtil.Android;

    /* package */ static void useSecondsOnly() {
        ActiveDateUtil = DateUtil.SecondsOnly;
    }
    /* package */ static void useAndroid() {
        ActiveDateUtil = DateUtil.Android;
    }

    public CharSequence relativeTimeSpanString(final long startTime){
        return ActiveDateUtil.relativeTimeSpanString(startTime);
    }
}
