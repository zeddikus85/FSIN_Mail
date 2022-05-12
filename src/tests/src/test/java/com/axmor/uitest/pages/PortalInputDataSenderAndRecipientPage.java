package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PortalInputDataSenderAndRecipientPage {
    // Поля для данных Получателя
    public static WebElement lastNameField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[4]/div/div[1]/div/div/input"));
    }
    public static WebElement nameField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[4]/div/div[2]/div/div/input"));
    }
    public static WebElement patronymicField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[4]/div/div[3]/div/div/input"));
    }
    public static WebElement yearBirthdayField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[4]/div/div[4]/div/div/input"));
    }

    // Поля данных Отправителя
    public static WebElement fioField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[6]/div/div[1]/div/div[1]/div/div/input"));
    }
    public static WebElement phoneField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[6]/div/div[1]/div/div[2]/div/div/input"));
    }
    public static WebElement mailField() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[6]/div/div[2]/div/div[1]/div/div/input"));
    }
    public static WebElement confirmationMailFild() {
        return Driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[2]/form/div/div[6]/div/div[2]/div/div[2]/div/div/input"));
    }

    // Кнопки
    public static WebElement continueButton() { return Driver.findElement(By.xpath(".//*[text()='Продолжить']/.")); }


    // Сценарии
    public static void inputDataSenderAndRecipientAndGoNextStep() {
        System.out.println("Ввод данных Получателя");
        lastNameField().sendKeys("Фамилия");
        nameField().sendKeys("Имя");
        patronymicField().sendKeys("Отчество");
        // год рождения
        yearBirthdayField().sendKeys("2000");

        System.out.println("Ввод данных Отправителя");
        fioField().sendKeys("Ф И О");
        phoneField().sendKeys("89529999999");
        mailField().sendKeys("nepibsb@cutradition.com");
        confirmationMailFild().sendKeys("nepibsb@cutradition.com");

        System.out.println("Переход к следующему шагу - Написать письмо");
        continueButton().click();
    }

}
