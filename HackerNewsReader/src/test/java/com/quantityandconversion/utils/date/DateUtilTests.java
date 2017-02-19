package com.quantityandconversion.utils.date;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DateUtilTests {

    @Test
    public void android(){
        DateUtilsAccess.useAndroid();
        assertThatThrownBy(() ->  DateUtil.Android.relativeTimeSpanString(0))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Method getRelativeTimeSpanString in android.text.format.DateUtils not mocked. See http://g.co/androidstudio/not-mocked for details.");
    }
}
