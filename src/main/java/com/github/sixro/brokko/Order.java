package com.github.sixro.brokko;

/**
 * Represents an order.
 *
 * @author <a href="mailto:me@sixro.net" >Sixro</a>
 * @since 1.0
 */
public interface Order {

    /**
     * Represents the status of an order.
     */
    enum Status {
        /**
         * The order has not been processed yet.
         */
        PENDING,
        /**
         * The order has been executed.
         */
        EXECUTED,
        /**
         * The order has been refused.
         */
        REFUSED,
        /**
         * The order has been cancelled.
         *
         * @see #cancel()
         */
        CANCELLED
    }

    /**
     * Cancel this order.
     */
    void cancel();

    // FIXME add Status and delete the object down.

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
