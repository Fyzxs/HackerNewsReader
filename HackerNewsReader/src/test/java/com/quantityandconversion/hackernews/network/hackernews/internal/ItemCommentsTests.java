package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.utils.RandomValues;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemCommentsTests {
    @Test
    public void ctorWorks(){
        new ItemComments(0L);
    }

    @Test
    public void ctorWorksThrowsOnSubZeroCommentCount(){
        assertThatThrownBy(() -> new ItemComments(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("commentCount can not be less than zero");
    }

    @Test
    public void commentCountIntoSetsSpecificForNullItemComment() throws InterruptedException {
        final AsyncFakeSetText item = new AsyncFakeSetText();
        ItemComments.NullItemComments.commentCountInto(item);

        assertThat(item.getText()).isEmpty();
    }
    @Test
    public void commentCountIntoSetsValue() throws InterruptedException {
        final AsyncFakeSetText item = new AsyncFakeSetText();
        final long rand = RandomValues.nextLongAbs();
        new ItemComments(rand).commentCountInto(item);

        assertThat(item.getText()).isEqualTo(rand + " comments");
    }
    @Test
    public void commentCountIntoDoesNotExplodeGivenNull() throws InterruptedException {
        ItemComments.NullItemComments.commentCountInto(null);
    }

}
