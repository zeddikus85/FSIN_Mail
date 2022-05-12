package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class PortalChoiceInstitutePage {
    public static WebElement regionField() {
        return Driver.findElement(By.id("select-region"));
    }
    public static WebElement instituteField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[2]/div[1]/div[2]/div/div"));
    }
    public static WebElement instituteFromList() { return Driver.findElement(By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[8]")); }

    public static WebElement continueButton() { return Driver.findElement(By.xpath(".//*[text()='Продолжить']/..")); }


    public static void choiceRegionAndInstituteAndGoNextStep() {
        System.out.println("Ввод Региона");
        regionField().sendKeys("УФСИН по СПб и ЛО");
        regionField().sendKeys(Keys.ENTER);

        System.out.println("Выбор Учреждения");
        instituteField().click();
        instituteFromList().click();

        System.out.println("Переход к следующему шагу - Ввод данных Получателя + Отправителя");
        continueButton().click();
    }

}
