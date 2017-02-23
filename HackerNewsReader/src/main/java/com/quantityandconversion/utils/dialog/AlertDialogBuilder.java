package com.quantityandconversion.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

public class AlertDialogBuilder<T extends Dialog> implements DialogBuilder<T> {

    private static DialogBuilder ActiveDialogBuilder = new DialogBuilderV7();

    /* package */ static void setActiveDialogBuilder(final DialogBuilder dialogBuilder){
        ActiveDialogBuilder = dialogBuilder;
    }

    @Override
    public DialogBuilder init(final Context context) {
        return ActiveDialogBuilder.init(context);
    }

    @Override
    public DialogBuilder setTitle(final int titleId) {
        return null;
    }

    @Override
    public DialogBuilder setMessage(@StringRes final int messageId) {
        return null;
    }

    @Override
    public DialogBuilder setView(final View view) {
        return null;
    }

    @Override
    public DialogBuilder setSingleChoiceItems(final CharSequence[] items, final int checkedItem, final DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setNegativeButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setPositiveButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setNeutralButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public T create() {
        return null;
    }

    @Override
    public T show() {
        return null;
    }
}
