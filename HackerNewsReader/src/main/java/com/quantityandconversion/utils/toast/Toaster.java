package com.quantityandconversion.utils.toast;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.widget.Toast;

public class Toaster {

    private static Toast replacementToast;
    private Toast currentToast;

    @VisibleForTesting(otherwise = VisibleForTesting.NONE)
    public static void setReplacementToast(final Toast toast){
        replacementToast = toast;
    }

    public Toaster makeToast(final Context context, final CharSequence text, final int duration) {
        currentToast = replacementToast == null ? new Toast(context) : replacementToast;

        currentToast.setText(text);
        currentToast.setDuration(duration);

        return this;
    }

    public void show() {
        currentToast.show();
    }
}
