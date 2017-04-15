package com.quantityandconversion.utils.toast;

import android.content.Context;
import android.widget.Toast;

import com.quantityandconversion.test.QacTestClass;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static junit.framework.Assert.fail;

public class ToasterTests extends QacTestClass {

    @Mock
    Context mockContext;

    @Test
    public void makeToastSetsValues(){
        final Toast mockToast = Mockito.mock(Toast.class);
        Toaster.setReplacementToast(mockToast);
        final Toaster toast = new Toaster();
        toast.makeToast(mockContext, "SomeText", Toast.LENGTH_LONG);
        toast.show();

        Mockito.verify(mockToast).setText("SomeText");
        Mockito.verify(mockToast).setDuration(Toast.LENGTH_LONG);
    }

    @Test
    public void showShows(){

        final Toast mockToast = Mockito.mock(Toast.class);
        Toaster.setReplacementToast(mockToast);
        final Toaster toast = new Toaster();
        toast.makeToast(mockContext, "SomeText", Toast.LENGTH_LONG);
        toast.show();

        Mockito.verify(mockToast).show();
    }
}
