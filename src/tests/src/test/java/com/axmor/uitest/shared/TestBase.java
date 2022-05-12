package com.axmor.uitest.shared;

import com.axmor.uitest.framework.core.TestCore;
import com.axmor.uitest.framework.Driver;


public abstract class TestBase extends TestCore {
    public static void loginAsUser() {
        login(Driver.appConfig().userLogin(), Driver.appConfig().userPassword());
    }

    public static void loginAsAdmin() {
        login(Driver.appConfig().adminLogin(), Driver.appConfig().adminPassword());
    }

    private static void login(String login, String password) {



    }
}
