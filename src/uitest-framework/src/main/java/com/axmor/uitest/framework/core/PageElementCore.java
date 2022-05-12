package com.axmor.uitest.framework.core;

import org.openqa.selenium.*;

import java.util.List;

public class PageElementCore implements WebElement {
    private WebElement originElement;


    public PageElementCore(WebElement originElement) {
        this.originElement = originElement;

    }

    public void setModelValue(String value) {
        sendKeys(value);
    }

    public String getModelValue() {
        return getDomProperty("value");
    }



    @Override
    public void click() {
        originElement.click();
    }

    @Override
    public void submit() {
        originElement.submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        originElement.sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        originElement.clear();
    }

    @Override
    public String getTagName() {
        return originElement.getTagName();
    }

    @Override
    public String getDomProperty(String name) {
        return originElement.getDomProperty(name);
    }

    @Override
    public String getDomAttribute(String name) {
        return originElement.getDomAttribute(name);
    }

    @Override
    public String getAttribute(String name) {
        return originElement.getAttribute(name);
    }

    @Override
    public String getAriaRole() {
        return originElement.getAriaRole();
    }

    @Override
    public String getAccessibleName() {
        return originElement.getAccessibleName();
    }

    @Override
    public boolean isSelected() {
        return originElement.isSelected();
    }

    @Override
    public boolean isEnabled() {
        return originElement.isEnabled();
    }

    @Override
    public String getText() {
        return originElement.getText();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return originElement.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        return originElement.findElement(by);
    }

    @Override
    public SearchContext getShadowRoot() {
        return originElement.getShadowRoot();
    }

    @Override
    public boolean isDisplayed() {
        return originElement.isDisplayed();
    }

    @Override
    public Point getLocation() {
        return originElement.getLocation();
    }

    @Override
    public Dimension getSize() {
        return originElement.getSize();
    }

    @Override
    public Rectangle getRect() {
        return originElement.getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return originElement.getCssValue(propertyName);
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return originElement.getScreenshotAs(target);
    }
}
