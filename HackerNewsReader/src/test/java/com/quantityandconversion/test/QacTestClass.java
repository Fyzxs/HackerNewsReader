package com.quantityandconversion.test;

import android.widget.Toast;

import com.quantityandconversion.utils.date.DateUtilsAccess;
import com.quantityandconversion.utils.log.FyzLogAccess;
import com.quantityandconversion.utils.toast.Toaster;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class QacTestClass {

    @Mock
    public Toast mockToast;

    @BeforeClass
    public static void beforeTests(){
        FyzLogAccess.writeToSystem();
        DateUtilsAccess.useSecondsOnly();
    }
    @Before
    public void before(){
        Toaster.setReplacementToast(mockToast);
    }
}
