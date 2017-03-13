package com.quantityandconversion.utils;

public final class Strings {

    private Strings(){}

    public static final String Empty = "";

    public static boolean isNullOrEmpty(final String s){
        return s == null || Empty.equals(s);
    }
}
