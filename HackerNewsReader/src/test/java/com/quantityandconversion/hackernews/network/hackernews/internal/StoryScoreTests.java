package com.quantityandconversion.hackernews.network.hackernews.internal;


import com.quantityandconversion.test.AsyncFakeSetText;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class StoryScoreTests {

    @Test
    public void equals(){
        final StoryScore same = new StoryScore(1L);
        assertThat(same).isEqualTo(same);
        assertThat(new StoryScore(1L)).isEqualTo(new StoryScore(1L));
        assertThat(new StoryScore(1L)).isNotEqualTo(new StoryScore(2L));
    }

    @Test
    public void hash(){
        assertThat(new StoryScore(1L).hashCode()).isEqualTo(new StoryScore(1L).hashCode());
        assertThat(new StoryScore(1L).hashCode()).isNotEqualTo(new StoryScore(2L).hashCode());
    }

    @Test
    public void score() throws InterruptedException {
        final AsyncFakeSetText fakeSetText = new AsyncFakeSetText();
        new StoryScore(1L).scoreInto(fakeSetText);

        assertThat(fakeSetText.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
