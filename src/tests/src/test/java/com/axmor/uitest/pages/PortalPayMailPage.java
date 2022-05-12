package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PortalPayMailPage {
    public static WebElement goPayButton() { return Driver.findElement(By.xpath("//div[text()=\"Перейти к оплате\"]")); }

    public static WebElement textAboutSendMail() { return Driver.findElement(By.xpath(".//*[text()='Письмо принято к доставке']/.")); }

    public static void startPaymentBankCard() {
        System.out.println("Оплата банковской картой");
        System.out.println("Клик по кнопке Перейти к оплате");
        goPayButton().click();
    }

    // Проверка наличия текста об отправке письма
    public static void checkTextAboutSendMail () {
        System.out.println("Проверяем наличие текста - Письмо принято к доставке ");
        Driver.findElement(By.xpath(".//*[text()='Письмо принято к доставке']/."));
    }
}
