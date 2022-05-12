package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;

public class PortalMainPage {
    public final static String Url = "/fsin-client/main";

    public static void open() {
        Driver.goToPage(Url);
    }

}


