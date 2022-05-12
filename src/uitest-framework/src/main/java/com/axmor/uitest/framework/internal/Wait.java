package com.axmor.uitest.framework.internal;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class Wait {
    private final WebDriverWait wait;

    public Wait(WebDriver driver, int waitSeconds) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(waitSeconds));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoreAll(List.of(
                NoSuchElementException.class,
                // ElementNotVisibleException.class,
                StaleElementReferenceException.class));
    }

    public boolean until(Predicate<WebDriver> condition) {
        return wait.until(condition::test);
    }

    public WebElement untilElement(Function<WebDriver, WebElement> condition) {
        return wait.until(condition);
    }
}
