package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import com.github.sixro.brokko.Orders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents an implementation of orders based on bot.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
final class BotOrders implements Orders {

    private static final int TEN_SECONDS = 10;
    private static final By ORDER_ROW = By.className("ordini-row");

    private final WebDriver webDriver;
    private final Wait<WebDriver> wait;

    /**
     * Create a BotOrders with specified driver.
     *
     * @param webDriver a web driver
     */
    BotOrders(WebDriver webDriver) {
        this(webDriver, new WebDriverWait(webDriver, TEN_SECONDS));
    }

    /**
     * Create a BotOrders with specified driver and wait.
     *
     * @param webDriver a web driver
     * @param wait a wait impl
     */
    BotOrders(WebDriver webDriver, Wait<WebDriver> wait) {
        this.webDriver = webDriver;
        this.wait = wait;
    }

    @SuppressWarnings("PMD.LawOfDemeter")
    @Override
    public Iterator<Order> iterator() {
        webDriver.navigate().to(
            "https://finecobank.com/portafoglio/monitor-ordini" +
                "/monitor-azioni-etf?tipo=&gg=30");

        wait.until(ExpectedConditions.visibilityOfElementLocated(ORDER_ROW));

        List<WebElement> elements = webDriver.findElements(ORDER_ROW);
        List<Order> list = new ArrayList<>(elements.size());
        for (WebElement el : elements) {
            list.add(new BotOrder(webDriver, el));
        }

        return list.iterator();
    }

}
