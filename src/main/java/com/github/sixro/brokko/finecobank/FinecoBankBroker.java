package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Broker;
import com.github.sixro.brokko.EntryOrder;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.Positions;
import com.github.sixro.brokko.bot.selenium.SeleniumBot;
import org.openqa.selenium.WebDriver;

/**
 * Represents <a href="https://finecobank.com">FinecoBank S.p.a.</a> broker.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankBroker implements Broker {

    private final Orders orders;

    /**
     * Create this broker using a web driver and specified credentials.
     *
     * @param webDriver a web driver
     * @param credentials some credentials
     */
    public FinecoBankBroker(WebDriver webDriver, Credentials credentials) {
        this(
            new AssuredLoginOrders(
                new BotLoggedIn(new SeleniumBot(webDriver), credentials),
                new BotOrders(webDriver)
            )
        );
    }

    /**
     * Create this broker with specified orders.
     *
     * @param orders an orders implementation
     */
    public FinecoBankBroker(Orders orders) {
        this.orders = orders;
    }

    @Override
    public EntryOrder entryOrder() {
        return new DefaultEntryOrder();
    }

    @Override
    public Orders orders() {
        return orders;
    }

    @Override
    public Positions positions() {
        return null;
    }

}
