package com.quantityandconversion.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringsTests {

    @Test
    public void isNullOrEmpty(){
        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(""));
        assertFalse(Strings.isNullOrEmpty(" \t\n "));
        assertFalse(Strings.isNullOrEmpty("Not Empty or WhiteSpace"));
    }


}
