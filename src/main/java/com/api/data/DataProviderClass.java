package com.api.data;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "InvalidAuth")
    public Object[] invalidAuth(){
        return new Object[]{
                "b1f2345",
                ""
        };
    }

    @DataProvider(name = "InvalidId")
    public Object[] invalidId(){
        return new Object[]{
                "123456",
                " "
        };
    }
}
