package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.ArrayList;

public class InvoiceboxPage {
    public static void closeInvoiceboxPage() {

        System.out.println("Закрываем вкладку Инвойсбокс");

        ArrayList<String> tabs2 = new ArrayList (Driver.current().getWindowHandles());
        Driver.current().switchTo().window(tabs2.get(1));
        Driver.current().close();
        Driver.current().switchTo().window(tabs2.get(0));

        System.out.println("Закрыли");
    }
}
