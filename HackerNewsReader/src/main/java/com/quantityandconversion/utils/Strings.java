package com.quantityandconversion.utils;

public final class Strings {
    public static final String EmptyString = "";
    public static boolean isNullOrEmpty(final String s){
        return s == null || EmptyString.equals(s);
    }
}
