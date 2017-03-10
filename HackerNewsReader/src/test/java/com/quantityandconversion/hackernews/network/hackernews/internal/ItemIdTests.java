package com.quantityandconversion.hackernews.network.hackernews.internal;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ItemIdTests {
    @Test
    public void equalsShouldBeFalseForNull(){
        assertThat(ItemId.createStoryId(1L)).isNotEqualTo(null);
    }
    @Test
    public void equalsShouldBeEqualForSameInstance(){
        final ItemId same = ItemId.createStoryId(1L);
        assertThat(same).isEqualTo(same);
    }
    @Test
    public void equalsShouldBeEqualForSameValue(){
        assertThat(ItemId.createStoryId(1L)).isEqualTo(ItemId.createStoryId(1L));
    }
    @Test
    public void equalsShouldBeNotEqualForDifferentValue(){
        assertThat(ItemId.createStoryId(1L)).isNotEqualTo(ItemId.createStoryId(2L));
    }

    @Test
    public void hashShouldBeEqualForSameValue(){
        assertThat(ItemId.createStoryId(1L).hashCode()).isEqualTo(ItemId.createStoryId(1L).hashCode());
     }
    @Test
    public void hashShouldNotBeEqualForDiffValue(){
        assertThat(ItemId.createStoryId(1L).hashCode()).isNotEqualTo(ItemId.createStoryId(2L).hashCode());
    }

    @Test
    public void isStoryIdShouldReturnTrueGivenStoryId(){
        assertThat(ItemId.createStoryId(1L).isStoryId()).isTrue();
    }
    @Test
    public void isStoryIdShouldReturnFalseGivenNotStoryId(){
        assertThat(ItemId.NULL_ITEM_ID.isStoryId()).isFalse();
    }

    @Test
    public void isJobIdShouldReturnTrueGivenJobId(){
        assertThat(ItemId.createJobId(1L).isJobId()).isTrue();
    }
    @Test
    public void isJobIdShouldReturnFalseGivenNotJobId(){
        assertThat(ItemId.NULL_ITEM_ID.isJobId()).isFalse();
    }

    @Test
    public void isUnknownIdIdShouldReturnTrueGivenUnknownId(){
        assertThat(ItemId.createUnknownId(1L).isUnknownId()).isTrue();
    }

    @Test
    public void isUnknownIdIdShouldReturnFalseGivenNotUnknownId(){
        assertThat(ItemId.NULL_ITEM_ID.isUnknownId()).isFalse();
    }

    @Test
    public void isAdLong(){
        assertThat(ItemId.createStoryId(1L).idAsLong()).isEqualTo(1L);
    }
}
