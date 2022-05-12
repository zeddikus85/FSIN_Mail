package com.axmor.uitest;
import com.axmor.uitest.framework.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
public class temp {

    public static WebElement writeButton() { return Driver.findElement(By.xpath("//h4[text()=\"Написать\"]")); }

}
