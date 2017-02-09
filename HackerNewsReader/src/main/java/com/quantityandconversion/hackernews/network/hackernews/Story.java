package com.quantityandconversion.hackernews.network.hackernews;

import com.quantityandconversion.utils.Strings;

import java.util.Scanner;

public class Story {
    private String title;

    public Story(final String title) {
        if(Strings.isNullOrEmpty(title)) { throw new IllegalArgumentException("title can not be null"); }
        this.title = title;
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Story && this.equals((Story)other);
    }
    private boolean equals(final Story other){
        return this.title.equals(other.title);
    }

    public Scanner title() {
        return new Scanner(title);
    }
}
