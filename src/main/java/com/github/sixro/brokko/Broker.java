package com.github.sixro.brokko;

/**
 * Represents a trading broker.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Broker {

    /**
     * Returns a new entry order.
     *
     * @return a new entry order
     */
    EntryOrder entryOrder();

    /**
     * Returns all recent orders.
     *
     * @return orders
     */
    Orders orders();

    /**
     * Represents positions open at the moment.
     *
     * @return positions
     */
    Positions positions();

}
