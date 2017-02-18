package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CommentIdTests {

    @Test
    public void equals(){
        final CommentId same = new CommentId(1L);
        assertThat(same).isEqualTo(same);
        assertThat(new CommentId(1L)).isEqualTo(new CommentId(1L));
        assertThat(new CommentId(1L)).isNotEqualTo(new CommentId(2L));
    }

    @Test
    public void hash(){
        assertThat(new CommentId(1L).hashCode()).isEqualTo(new CommentId(1L).hashCode());
        assertThat(new CommentId(1L).hashCode()).isNotEqualTo(new CommentId(2L).hashCode());
    }

    @Test
    public void isAdLong(){
        assertThat(new CommentId(1L).idAsLong()).isEqualTo(1L);
    }
}
