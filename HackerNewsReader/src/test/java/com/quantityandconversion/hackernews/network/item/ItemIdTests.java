package com.quantityandconversion.hackernews.network.item;

import org.junit.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ItemIdTests {

    @Test
    public void equals(){
        assertThat(new ItemId(1L)).isEqualTo(new ItemId(1L));
        assertThat(new ItemId(1L)).isNotEqualTo(new ItemId(2L));
    }


    @Test
    public void hash(){
        assertThat(new ItemId(1L).hashCode()).isEqualTo(new ItemId(1L).hashCode());
        assertThat(new ItemId(1L).hashCode()).isNotEqualTo(new ItemId(2L).hashCode());
    }
}
