package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.utils.Strings;
import com.quantityandconversion.widget.SetText;

public class Title {
    public final static Title NullTitle = new Title("[ Loading Title ]");

    private final String title;

    /* package */ Title(final String title) {
        if(Strings.isNullOrEmpty(title)) { throw new IllegalArgumentException("title can not be null or empty"); }
        this.title = title;
    }



    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof Title && this.equals((Title)other);
    }
    private boolean equals(final Title other){
        return this.title.equals(other.title);
    }

    public void title(final SetText setText) {
        setText.setText(title);
    }
}
