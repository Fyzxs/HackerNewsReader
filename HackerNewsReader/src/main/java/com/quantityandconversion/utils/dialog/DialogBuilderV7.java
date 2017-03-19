package com.quantityandconversion.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.View;

/* package */
@SuppressWarnings("unchecked")
class DialogBuilderV7 implements DialogBuilder<AlertDialog> {

    private android.support.v7.app.AlertDialog.Builder builder;

    /* package */ void setBuilder(final android.support.v7.app.AlertDialog.Builder builder){
        this.builder = builder;
    }

    @Override
    public DialogBuilder init(final Context context) {
        builder = new android.support.v7.app.AlertDialog.Builder(context);
        return this;
    }

    @Override
    public DialogBuilder setTitle(@StringRes final int titleId) {
        builder.setTitle(titleId);
        return this;
    }

    @Override
    public DialogBuilder setMessage(@StringRes final int messageId) {
        builder.setMessage(messageId);
        return this;
    }

    @Override
    public DialogBuilder setView(final View view) {
        builder.setView(view);
        return this;
    }

    @Override
    public DialogBuilder setSingleChoiceItems(final CharSequence[] items, final int checkedItem, final DialogInterface.OnClickListener listener) {
        builder.setSingleChoiceItems(items, checkedItem, listener);
        return this;
    }

    @Override
    public DialogBuilder setNegativeButton(@StringRes final int textId,
                                           final DialogInterface.OnClickListener listener) {
        builder.setNegativeButton(textId, listener);
        return this;
    }

    @Override
    public DialogBuilder setPositiveButton(@StringRes final int textId,
                                           final DialogInterface.OnClickListener listener) {
        builder.setPositiveButton(textId, listener);
        return this;
    }

    @Override
    public DialogBuilder setNeutralButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        builder.setNeutralButton(textId, listener);
        return this;
    }

    @Override
    public android.support.v7.app.AlertDialog create() {
        return builder.create();
    }

    @Override
    public android.support.v7.app.AlertDialog show() {
        return builder.show();
    }
}
