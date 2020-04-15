package com.github.sixro.brokko.finecobank;

import com.github.sixro.brokko.Order;
import com.github.sixro.brokko.Orders;

import java.util.Iterator;

/**
 * Represents a decorator of orders able to ensure we are logged in.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
final class AssuredLoginOrders implements Orders {

    private final LoggedIn loggedIn;
    private final Orders delegate;

    /**
     * Create this object using specified logged in impl and a
     * delegate for orders.
     *
     * @param loggedIn a logged in
     * @param delegate a delegate orders
     */
    AssuredLoginOrders(LoggedIn loggedIn, Orders delegate) {
        this.loggedIn = loggedIn;
        this.delegate = delegate;
    }

    @Override
    public Iterator<Order> iterator() {
        loggedIn.assure();
        return delegate.iterator();
    }
}
