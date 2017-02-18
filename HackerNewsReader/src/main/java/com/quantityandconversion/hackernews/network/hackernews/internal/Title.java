package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.ood.SimpleWrapper;
import com.quantityandconversion.utils.Strings;

public class Title extends SimpleWrapper<String>{
    public final static Title NullTitle = new Title("[ Loading Title ]");

    /* package */ Title(final String title) {
        super(title);
    }

    @Override
    protected void validate(final String input) {
        if(Strings.isNullOrEmpty(input)) { throw new IllegalArgumentException("title can not be null or empty"); }
    }

    public void title(final SetText item) {
        if(item == null){ return; }
        item.setText(value());
    }
}
