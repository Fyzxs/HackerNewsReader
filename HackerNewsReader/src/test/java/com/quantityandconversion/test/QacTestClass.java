package com.quantityandconversion.test;

import com.quantityandconversion.utils.date.DateUtilsAccess;
import com.quantityandconversion.utils.log.FyzLogAccess;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public abstract class QacTestClass {

    @BeforeClass
    public static void beforeTests(){
        FyzLogAccess.writeToSystem();
        DateUtilsAccess.useSecondsOnly();
    }
}
