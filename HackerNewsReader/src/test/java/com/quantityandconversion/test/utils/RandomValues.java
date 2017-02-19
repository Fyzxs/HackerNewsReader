package com.quantityandconversion.test.utils;

import java.util.Random;

public class RandomValues {

    private static final Random random = new Random();

    private static final char[] symbols;

    static {
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'a'; ch <= 'z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
    }


    public static String alphaNumeric(final int length) {
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);

        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];

        return new String(buf);
    }

    public static int nextInt(final int excludedMax){
        return random.nextInt(excludedMax);
    }

    public static long nextLongAbs(){
        return Math.abs(random.nextLong());
    }
    public static long nextLong(){
        return random.nextLong();
    }
}
