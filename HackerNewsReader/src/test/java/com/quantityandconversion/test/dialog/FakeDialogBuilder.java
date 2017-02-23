/*
 * Copyright (c) 2015 Ha Duy Trung
 * https://github.com/hidroh/materialistic/blob/2470015b5c8fb445c63975c5a0929a9b82f04472/app/src/main/java/io/github/hidroh/materialistic/AlertDialogBuilder.java
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.quantityandconversion.test.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

import com.quantityandconversion.utils.dialog.DialogBuilder;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;

public class FakeDialogBuilder implements DialogBuilder<Dialog> {
    private int titleId;
    private int messageId;
    private View view;
    private CharSequence[] singleChoiceItemsItems;
    private int singleChoiceItemsCheckedItem;
    private DialogInterface.OnClickListener singleChoiceItemslistener;
    private int negativeButtonTextId;
    private DialogInterface.OnClickListener negativeButtonListener;
    private int positiveButtonTextId;
    private DialogInterface.OnClickListener positiveButtonListener;
    private int neutralButtonTextId;
    private DialogInterface.OnClickListener neutralButtonListener;
    private int createCalled;
    private int showCalled;
    private final CountDownLatch latch;

    public FakeDialogBuilder(final CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public FakeDialogBuilder init(final Context context) {
        return this;
    }

    @Override
    public FakeDialogBuilder setTitle(final int titleId) {
        this.titleId = titleId;
        return this;
    }

    @Override
    public FakeDialogBuilder setMessage(@StringRes final int messageId) {
        this.messageId = messageId;
        return this;
    }

    @Override
    public FakeDialogBuilder setView(final View view) {
        this.view = view;
        return this;
    }

    @Override
    public FakeDialogBuilder setSingleChoiceItems(final CharSequence[] items,
                                                  final int checkedItem,
                                                  final DialogInterface.OnClickListener listener) {
        this.singleChoiceItemsItems = items;
        this.singleChoiceItemsCheckedItem = checkedItem;
        this.singleChoiceItemslistener = listener;
        return this;
    }

    @Override
    public FakeDialogBuilder setNegativeButton(@StringRes final int textId,
                                               final DialogInterface.OnClickListener listener) {
        this.negativeButtonTextId = textId;
        this.negativeButtonListener = listener;
        return this;
    }

    @Override
    public FakeDialogBuilder setPositiveButton(@StringRes final int textId,
                                               final DialogInterface.OnClickListener listener) {
        this.positiveButtonTextId = textId;
        this.positiveButtonListener = listener;
        return this;
    }

    @Override
    public FakeDialogBuilder setNeutralButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        this.neutralButtonTextId = textId;
        this.neutralButtonListener = listener;
        return this;
    }

    @Override
    public Dialog create() {
        this.createCalled += 1;
        return null;
    }

    @Override
    public Dialog show() {
        latch.countDown();
        this.showCalled += 1;
        return null;
    }

    public boolean isTitleId(final int titleId){
        return this.titleId == titleId;
    }
    public boolean isMessageId(final int messageId){
        return this.messageId == messageId;
    }
    public boolean isNegativeButtonTextId(final int negativeButtonTextId){
        return this.negativeButtonTextId == negativeButtonTextId;
    }
    public boolean isPositiveButtonTextId(final int positiveButtonTextId){
        return this.positiveButtonTextId == positiveButtonTextId;
    }
    public boolean isNeutralButtonTextId(final int neutralButtonTextId){
        return this.neutralButtonTextId == neutralButtonTextId;
    }
    public boolean wasCreateCalled(final int times){
        return this.createCalled == times;
    }
    public void assertShowCalled(final int times){
        assertThat(times).isEqualTo(times);
    }
}