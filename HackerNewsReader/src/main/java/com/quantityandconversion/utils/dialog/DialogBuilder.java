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
package com.quantityandconversion.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ListView;

/**
 * Injectable alert dialog builder, allowing swapping between
 * {@link android.support.v7.app.AlertDialog.Builder} and {@link android.app.AlertDialog.Builder}
 * @param <T> type of created alert dialog, extends from {@link Dialog}
 */
public interface DialogBuilder<T extends Dialog> {


    /**
     * Construct the wrapped dialog builder object. This must be called before any other methods.
     * @param context activity context
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder init(Context context);

    /**
     * Set the title using the given resource id.
     *
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setTitle(int titleId);

    /**
     * Set the message to display using the given resource id.
     *
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setMessage(@StringRes int messageId);

    /**
     * Sets a custom view to be the contents of the alert dialog.
     * <p>
     * When using a pre-Holo theme, if the supplied view is an instance of
     * a {@link ListView} then the light background will be used.
     * <p>
     * <strong>Note:</strong> To ensure consistent styling, the custom view
     * should be inflated or constructed using the alert dialog's themed
     * context
     *
     * @param view the view to use as the contents of the alert dialog
     * @return this Builder object to allow for chaining of calls to set
     *         methods
     */
    DialogBuilder setView(View view);

    /**
     * Set a list of items to be displayed in the dialog as the content, you will be notified
     * of
     * the selected item via the supplied listener. The list will have a check mark displayed
     * to
     * the right of the text for the checked item. Clicking on an item in the list will not
     * dismiss the dialog. Clicking on a button will dismiss the dialog.
     *
     * @param items       the items to be displayed.
     * @param checkedItem specifies which item is checked. If -1 no items are checked.
     * @param listener    notified when an item on the list is clicked. The dialog will not be
     *                    dismissed when an item is clicked. It will only be dismissed if
     *                    clicked on a
     *                    button, if no buttons are supplied it's up to the user to dismiss the
     *                    dialog.
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem,
                                       final DialogInterface.OnClickListener listener);

    /**
     * Set a listener to be invoked when the negative button of the dialog is pressed.
     *
     * @param textId   The resource id of the text to display in the negative button
     * @param listener The {@link DialogInterface.OnClickListener} to use.
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setNegativeButton(@StringRes int textId, DialogInterface.OnClickListener listener);

    /**
     * Set a listener to be invoked when the positive button of the dialog is pressed.
     *
     * @param textId   The resource id of the text to display in the positive button
     * @param listener The {@link DialogInterface.OnClickListener} to use.
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setPositiveButton(@StringRes int textId, DialogInterface.OnClickListener listener);

    /**
     * Set a listener to be invoked when the neutral button of the dialog is pressed.
     *
     * @param textId   The resource id of the text to display in the neutral button
     * @param listener The {@link DialogInterface.OnClickListener} to use.
     * @return This Builder object to allow for chaining of calls to set methods
     */
    DialogBuilder setNeutralButton(@StringRes int textId, DialogInterface.OnClickListener listener);

    /**
     * Creates a {@link Dialog} with the arguments supplied to this builder. It does not
     * {@link Dialog#show()} the dialog. This allows the user to do any extra processing
     * before displaying the dialog. Use {@link #show()} if you don't have any other processing
     * to do and want this to be created and displayed.
     */
    T create();

    /**
     * Creates a {@link Dialog} with the arguments supplied to this builder and
     * {@link Dialog#show()}'s the dialog.
     */
    T show();
}