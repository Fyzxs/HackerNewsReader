package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.test.AsyncFakeSetText;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StoryCommentsTests {
    @Test
    public void ctorWorks(){
        new StoryComments(0L);
    }

    @Test
    public void ctorWorksThrowsOnSubZeroCommentCount(){
        assertThatThrownBy(() -> new StoryComments(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("commentCount can not be less than zero");
    }

    @Test
    public void commentCountInto() throws InterruptedException {
        final AsyncFakeSetText item = new AsyncFakeSetText();
        StoryComments.NullStoryComments.commentCountInto(item);

        assertThat(item.await(1, TimeUnit.SECONDS)).isTrue();
    }

}
