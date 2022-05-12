package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PortalWriteMailPage {

    public static WebElement textMail() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/div/div[4]/div/div[1]/textarea[1]"));
    }

    public static WebElement continueButton() { return Driver.findElement(By.xpath(".//*[text()='Продолжить']/..")); }

    public static void writeMailAndGoNextStep() {
        System.out.println("Ввод ввод текста письма");
        textMail().sendKeys("Текст письма");

        System.out.println("Переход к следующему шагу - Оплата");
        continueButton().click();
    }
}
