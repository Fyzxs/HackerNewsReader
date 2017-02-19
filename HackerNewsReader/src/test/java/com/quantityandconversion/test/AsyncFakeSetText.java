package com.quantityandconversion.test;

import com.quantityandconversion.ood.SetText;
import com.quantityandconversion.test.utils.RandomValues;

import java.util.concurrent.CountDownLatch;

public class AsyncFakeSetText extends CountDownLatch implements SetText {

    private final static CharSequence initValue = RandomValues.alphaNumeric(50);
    private CharSequence setText = initValue;
    public AsyncFakeSetText() {
        super(1);
    }

    @Override
    public void setText(final CharSequence charSequence) {
        setText = charSequence;
        this.countDown();
    }

    public CharSequence getText(){
        if(setText == initValue){ throw new IllegalArgumentException("Text Was Not Set"); }

        return setText;
    }
}
