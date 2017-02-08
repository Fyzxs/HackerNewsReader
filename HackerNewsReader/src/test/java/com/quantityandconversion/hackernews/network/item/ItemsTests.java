package com.quantityandconversion.hackernews.network.item;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ItemsTests {


    @Test
    public void size(){
        assertThatThrownBy(() -> new Items(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("itemIds");
        assertThat(new Items(new ItemId[0]).size()).isEqualTo(0);
        assertThat(new Items(new ItemId[1]).size()).isEqualTo(0);
        assertThat(new Items(new ItemId[]{new ItemId(1L)}).size()).isEqualTo(1);
    }

    @Test
    public void itemIdAt(){
        assertThatThrownBy(() -> new Items(new ItemId[0]).itemIdAt(0))
                .isInstanceOf(IndexOutOfBoundsException.class);
        assertThat(new Items(new ItemId[]{new ItemId(1L)}).itemIdAt(0)).isEqualTo(new ItemId(1L));
    }
}