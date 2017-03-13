package com.quantityandconversion.hackernews.network.hackernews.internal;


import com.quantityandconversion.test.AsyncFakeSetText;
import com.quantityandconversion.test.utils.RandomValues;
import com.quantityandconversion.utils.Strings;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ItemScoreTests {

    @Test
    public void equals(){
        final ItemScore same = new ItemScore(1L);
        assertThat(same).isEqualTo(same);
        assertThat(new ItemScore(1L)).isEqualTo(new ItemScore(1L));
        assertThat(new ItemScore(1L)).isNotEqualTo(new ItemScore(2L));
    }

    @Test
    public void hash(){
        assertThat(new ItemScore(1L).hashCode()).isEqualTo(new ItemScore(1L).hashCode());
        assertThat(new ItemScore(1L).hashCode()).isNotEqualTo(new ItemScore(2L).hashCode());
    }

    @Test
    public void scoreIntoSetsValue() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        final long rand = RandomValues.nextLong();
        new ItemScore(rand).scoreInto(fakeSetText);

        assertThat(fakeSetText.getText()).isEqualTo(Long.toString(rand));
    }
    @Test
    public void scoreIntoSetsValueForNullObject() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        ItemScore.NullItemScore.scoreInto(fakeSetText);

        assertThat(fakeSetText.getText()).isEqualTo(Strings.Empty);
    }

    @Test
    public void scoreDoesNotExplodeGivenNull() throws InterruptedException {
        ItemScore.NullItemScore.scoreInto(null);
    }
}
