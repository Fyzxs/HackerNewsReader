package com.quantityandconversion.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

public class AlertDialogBuilder<T extends Dialog> extends DialogBuilder<T> {

    private static DialogBuilder ActiveDialogBuilder = DialogBuilder.V7;

    /* package */ static void setActiveDialogBuilder(final DialogBuilder dialogBuilder){
        ActiveDialogBuilder = dialogBuilder;
    }


    @Override
    public DialogBuilder init(Context context) {
        return ActiveDialogBuilder.init(context);
    }

    @Override
    public DialogBuilder setTitle(int titleId) {
        return null;
    }

    @Override
    public DialogBuilder setMessage(@StringRes int messageId) {
        return null;
    }

    @Override
    public DialogBuilder setView(View view) {
        return null;
    }

    @Override
    public DialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setNegativeButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setPositiveButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
        return null;
    }

    @Override
    public DialogBuilder setNeutralButton(@StringRes int textId, DialogInterface.OnClickListener listener) {
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
