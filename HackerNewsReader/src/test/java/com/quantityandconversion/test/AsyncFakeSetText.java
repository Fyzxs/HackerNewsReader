package com.quantityandconversion.test;

import com.quantityandconversion.ood.SetText;

import java.util.concurrent.CountDownLatch;

public class AsyncFakeSetText extends CountDownLatch implements SetText {
    public AsyncFakeSetText() {
        super(1);
    }

    @Override
    public void setText(final CharSequence charSequence) {
        this.countDown();
    }
}
