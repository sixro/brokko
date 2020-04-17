package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Position;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class BotPosition implements Position {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private final WebDriver webDriver;
    private final WebElement webElement;

    BotPosition(WebDriver webDriver, WebElement webElement) {
        this.webDriver = webDriver;
        this.webElement = webElement;
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String symbol() {
        return webElement.getAttribute("instrid");
    }

}
