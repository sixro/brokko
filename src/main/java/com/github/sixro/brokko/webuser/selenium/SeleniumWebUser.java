package com.github.sixro.brokko.webuser.selenium;

import com.github.sixro.brokko.webuser.WebUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * Represents a web user based on selenium.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
@SuppressWarnings({ "PMD.LawOfDemeter", "PMD.ExcessivePublicCount" })
public final class SeleniumWebUser implements WebUser {

    private final WebDriver driver;
    private final WebDriverWait wait;

    /**
     * Create a web user based on specified web driver and with the
     * specified default timeout.
     *
     * @param driver a driver
     * @param wait   default max time to wait for visibility of elements
     * @see #waitFor(By)
     */
    public SeleniumWebUser(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    @Override
    public void open(String url) {
        driver.navigate().to(url);
    }

    @Override
    public void waitFor(By xpath) {
        wait.until(visibilityOfElementLocated(xpath));
    }

    @Override
    public void waitFor(By xpath, Duration timeout) {
        WebDriverWait w = new WebDriverWait(driver, timeout.getSeconds());
        w.until(visibilityOfElementLocated(xpath));
    }

    @Override
    public void insert(String text, By xpath) {
        driver.findElement(xpath).sendKeys(text);
    }

    @Override
    public void click(By xpath) {
        driver.findElement(xpath).click();
    }

    @Override
    public boolean see(By xpath) {
        return driver.findElement(xpath).isDisplayed();
    }

    @Override
    public void leave() {
        driver.close();
    }

}
