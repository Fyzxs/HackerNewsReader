package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SimpleWrapper;
import com.quantityandconversion.utils.Strings;
import com.quantityandconversion.ood.SetText;

public class Author extends SimpleWrapper<String> {
    public final static Author NullAuthor = new Author("{{ Loading Author }}");

    public Author(final String author) {
        super(author);
    }

    @Override
    protected void validate(final String author) {
        if(Strings.isNullOrEmpty(author)){ throw new IllegalArgumentException("author can not be null");}
    }

    public void authorInto(final SetText item) {
        if(item == null){ return; }
        item.setText("Posted by: " + value());
    }
}
