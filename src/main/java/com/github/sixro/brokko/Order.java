package com.github.sixro.brokko;

/**
 * Represents an order.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Order {

    /**
     * Cancel this order.
     */
    void cancel();

    /**
     * Returns true if the order is pending yet.
     *
     * @return true if the order is pending yet, otherwise false
     */
    boolean pending();

    /**
     * Returns true if the order is executed.
     *
     * @return true if the order is executed, otherwise false
     */
    boolean executed();

    /**
     * Returns true if the order has been refused.
     *
     * @return true if the order has been refused, otherwise false
     */
    boolean refused();

    /**
     * Returns the symbol to which this order is related.
     *
     * @return a symbol
     */
    String symbol();

}
