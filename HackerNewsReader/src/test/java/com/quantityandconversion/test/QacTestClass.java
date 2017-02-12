package com.quantityandconversion.test;

import com.quantityandconversion.utils.log.FyzLogAccess;

import org.junit.BeforeClass;

public class QacTestClass {

    @BeforeClass
    public static void beforeTests(){
        FyzLogAccess.writeToSystem();
    }
}
