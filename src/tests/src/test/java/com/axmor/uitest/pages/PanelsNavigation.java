package com.axmor.uitest.pages;

import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PanelsNavigation {

    // Верхняя панель навигации
    // Кнопка написать
    public static WebElement writeButton() { return Driver.findElement(By.xpath("//h4[text()=\"Написать\"]")); }

    public static void goToChoiceInstitute () {
        writeButton().click();
    }

    // Нижняя панель


}
