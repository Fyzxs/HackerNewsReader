package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.QacTestClass;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PostTimeTests extends QacTestClass{

    @Test
    public void ctor(){
        assertThatThrownBy(() -> new PostTime(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("unixTime can not be less than zero");
    }

    @Test
    public void postTimeIntoMinutesAgo() throws InterruptedException {
        final PostTime postTime = new PostTime(RandomValues.nextLongAbs());
        AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        postTime.postTimeInto(fakeSetText);
        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
        assertThat(fakeSetText.getText()).endsWith(" seconds ago - only");
    }
}
