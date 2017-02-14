package com.quantityandconversion.test;

import com.quantityandconversion.widget.interfaces.QacTextView;

import java.util.concurrent.CountDownLatch;

public class FakeQacTextView implements QacTextView {
    private final CountDownLatch latch;

    public FakeQacTextView(final CountDownLatch latch){
        this.latch = latch;
    }
    @Override
    public void setText(CharSequence charSequence) {
        latch.countDown();
    }
}
