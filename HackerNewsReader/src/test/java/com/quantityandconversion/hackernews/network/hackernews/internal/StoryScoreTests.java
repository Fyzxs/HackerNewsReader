package com.quantityandconversion.hackernews.network.hackernews.internal;


import com.quantityandconversion.test.FakeSetText;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
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
        final CountDownLatch latch = new CountDownLatch(1);
        final FakeSetText fakeSetText = new FakeSetText(latch);
        new StoryScore(1L).score(fakeSetText);

        assertThat(latch.await(1, TimeUnit.SECONDS)).isTrue();
    }
}
