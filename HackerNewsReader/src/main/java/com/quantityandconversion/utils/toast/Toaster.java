package com.quantityandconversion.utils.toast;

import android.content.Context;
import android.widget.Toast;

public class Toaster {

    private static Toast ReplacementToast;
    private Toast currentToast;

    public static void setReplacementToast(final Toast toast){
        ReplacementToast = toast;
    }

    public Toaster makeToast(final Context context, final CharSequence text, final int duration) {
        if(ReplacementToast == null){//A static method on Toast, we can't fake it
            currentToast = Toast.makeText(context, text, duration);
        } else {
            currentToast = ReplacementToast;
        }

        return this;
    }

    public void show() {
        currentToast.show();
    }
}
