package com.quantityandconversion.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;

public class AlertDialogBuilder<T extends Dialog> implements DialogBuilder<Dialog> {

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
        return ActiveDialogBuilder.setTitle(titleId);
    }

    @Override
    public DialogBuilder setMessage(@StringRes final int messageId) {
        return ActiveDialogBuilder.setMessage(messageId);
    }

    @Override
    public DialogBuilder setView(final View view) {
        return ActiveDialogBuilder.setView(view);
    }

    @Override
    public DialogBuilder setSingleChoiceItems(final CharSequence[] items, final int checkedItem, final DialogInterface.OnClickListener listener) {
        return ActiveDialogBuilder.setSingleChoiceItems(items, checkedItem, listener);
    }

    @Override
    public DialogBuilder setNegativeButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return ActiveDialogBuilder.setNegativeButton(textId, listener);
    }

    @Override
    public DialogBuilder setPositiveButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return ActiveDialogBuilder.setPositiveButton(textId, listener);
    }

    @Override
    public DialogBuilder setNeutralButton(@StringRes final int textId, final DialogInterface.OnClickListener listener) {
        return ActiveDialogBuilder.setNeutralButton(textId, listener);
    }

    @Override
    public Dialog create() {
        return ActiveDialogBuilder.create();
    }

    @Override
    public Dialog show() {
        return ActiveDialogBuilder.show();
    }
}
