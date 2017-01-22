package com.quantityandconversion.hackernews.network.item;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.junit.Assert.assertEquals;

public class ItemsTests {


    @Test
    public void size(){
        catchThrowable(new Items(null));
        assertEquals(0, new Items(null).size());
    }
}
