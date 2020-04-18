package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Map;

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
    private final Map<String, Status> statusByText;

    /**
     * Create a BotOrder using specified driver and web element.
     *
     * @param webDriver a driver
     * @param webElement a web element
     */
    BotOrder(WebDriver webDriver, WebElement webElement) {
        this(webDriver, webElement, newMapOfStatus());
    }

    private static Map<String, Status> newMapOfStatus() {
        Map<String, Status> map = new HashMap<>();
        map.put("immesso", Status.PENDING);
        map.put("accodato", Status.PENDING);
        map.put("eseguito", Status.EXECUTED);
        map.put("rifiutato", Status.REFUSED);
        map.put("cancellato", Status.CANCELLED);
        return map;
    }

    BotOrder(WebDriver webDriver, WebElement webElement, Map<String,
        Status> statusByText) {
        this.webDriver = webDriver;
        this.webElement = webElement;
        this.statusByText = statusByText;
    }

    @Override
    public void cancel() {
        throw new UnsupportedOperationException();
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

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public Status status() {
        String rawStatus = rawStatus();
        return statusByText.get(rawStatus.toLowerCase());
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    private String rawStatus() {
        WebElement subel = webElement.findElement(
            By.xpath("td/a[contains(@class, 'titolo-stato')]")
        );
        return subel.getText();
    }

}
