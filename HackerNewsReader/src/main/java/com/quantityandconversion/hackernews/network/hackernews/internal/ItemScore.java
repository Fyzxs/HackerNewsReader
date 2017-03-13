package com.quantityandconversion.hackernews.network.hackernews.internal;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.ood.SimpleWrapper;
import com.quantityandconversion.utils.Strings;

public class ItemScore extends SimpleWrapper<Long> {

    public final static ItemScore NullItemScore = new ItemScore(0);

    /* package */ ItemScore(final long commentId) {
        super(commentId);
    }

    public void scoreInto(final SetText item){
        if(item == null){return;}

        item.setText(this == NullItemScore ? Strings.Empty : Long.toString(value()));
    }
}
