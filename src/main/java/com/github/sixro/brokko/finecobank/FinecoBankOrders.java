package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import com.github.sixro.brokko.Orders;
import com.github.sixro.brokko.util.selenium.SeleniumBot;
import org.openqa.selenium.WebDriver;

import java.util.Iterator;

/**
 * Represents orders on <a href="https://finecobank.com">FinecoBank S.p.a.</a>.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public final class FinecoBankOrders implements Orders {

    private final LoggedIn loggedIn;
    private final Orders delegate;

    /**
     * Create this object using specified web driver and credentials.
     *
     * @param webDriver a web driver
     * @param credentials some credentials
     */
    @SuppressWarnings("unused")
    public FinecoBankOrders(WebDriver webDriver, Credentials credentials) {
        this(
            new BotLoggedIn(
                new SeleniumBot(webDriver), credentials
            ),
            new BotOrders(webDriver)
        );
    }

    /**
     * Create this object using specified logged in impl and a
     * delegate for orders.
     *
     * @param loggedIn a logged in
     * @param delegate a delegate orders
     */
    FinecoBankOrders(LoggedIn loggedIn, Orders delegate) {
        this.loggedIn = loggedIn;
        this.delegate = delegate;
    }

    @Override
    public Iterator<Order> iterator() {
        loggedIn.ensure();
        return delegate.iterator();
    }

}
