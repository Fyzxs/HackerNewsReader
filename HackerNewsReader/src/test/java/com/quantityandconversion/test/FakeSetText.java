package com.quantityandconversion.test;

import com.quantityandconversion.widget.SetText;

import java.util.concurrent.CountDownLatch;

public class FakeSetText implements SetText{
    private final CountDownLatch latch;

    public FakeSetText(final CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void setText(CharSequence charSequence) {
        latch.countDown();
    }
}
