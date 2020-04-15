package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Represents an order based on a bot.
 *
 * @see BotOrders
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
final class BotOrder implements Order {

    @SuppressWarnings("PMD.UnusedPrivateField")
    private final WebDriver webDriver;
    private final WebElement webElement;

    /**
     * Create a BotOrder using specified driver and web element.
     *
     * @param webDriver a driver
     * @param webElement a web element
     */
    BotOrder(WebDriver webDriver, WebElement webElement) {
        this.webDriver = webDriver;
        this.webElement = webElement;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public String symbol() {
        WebElement subel = webElement.findElement(
            By.xpath("td/a[contains(@class, 'description-link')]")
        );
        String isin = subel.getAttribute("instrid");
        return isin;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean pending() {
        String rawStatus = rawStatus();
        return "Immesso".equalsIgnoreCase(rawStatus) ||
                "Accodato".equalsIgnoreCase(rawStatus);
    }

    @Override
    public boolean executed() {
        return "Eseguito".equalsIgnoreCase(rawStatus());
    }

    @Override
    public boolean refused() {
        return "Rifiutato".equalsIgnoreCase(rawStatus());
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private String rawStatus() {
        WebElement subel = webElement.findElement(
            By.xpath("td/a[contains(@class, 'titolo-stato')]")
        );
        return subel.getText();
    }

}
